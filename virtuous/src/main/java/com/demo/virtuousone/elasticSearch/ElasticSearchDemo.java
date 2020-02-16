package com.demo.virtuousone.elasticSearch;

import com.demo.virtuousone.po.ElasticSearchUser;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 吴宸煊
 * Date: 2020/2/16 16:36
 * Description:
 */
public class ElasticSearchDemo {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 保存
     */
    @Test
    public void save() {
        ElasticSearchUser user = new ElasticSearchUser();
        user.setId(1001L);
        user.setName("张三");
        user.setAge(20);
        user.setHobby("足球、篮球、听音乐");
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withObject(user).build();
        String index = this.elasticsearchTemplate.index(indexQuery);
        System.out.println(index);
    }


    /**
     * 批量新增
     */
    @Test
    public void testBulk() {
        List list = new ArrayList();
        for (int i = 0; i < 5000; i++) {
            ElasticSearchUser user = new ElasticSearchUser();
            user.setId(1001L + i);
            user.setAge(i % 50 + 10);
            user.setName("张三" + i);
            user.setHobby("足球、篮球、听音乐");
            IndexQuery indexQuery = new
                    IndexQueryBuilder().withObject(user).build();
            list.add(indexQuery);
        }
        Long start = System.currentTimeMillis();
        this.elasticsearchTemplate.bulkIndex(list);
        System.out.println("用时：" + (System.currentTimeMillis() - start)); //用时：7836

    }


    /**
     * 修改
     */
    @Test
    public void testUpdate() {
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.source("age", "30");

        UpdateQuery updateQuery = new UpdateQueryBuilder()
                .withId("1001")
                .withClass(ElasticSearchUser.class)
                .withIndexRequest(indexRequest).build();

        this.elasticsearchTemplate.update(updateQuery);
    }

    /**
     * 删除
     */
    @Test
    public void testDelete() {
        this.elasticsearchTemplate.delete(ElasticSearchUser.class, "1001");
    }


    /**
     * 分页查询
     */
    @Test
    public void testSearch() {
        PageRequest pageRequest = PageRequest.of(1, 10); //设置分页参数

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("name", "张三")) // match查询
                .withPageable(pageRequest)
                .build();

        AggregatedPage<ElasticSearchUser> users =
                this.elasticsearchTemplate.queryForPage(searchQuery, ElasticSearchUser.class);

        System.out.println("总页数：" + users.getTotalPages()); //获取总页数

        for (ElasticSearchUser user : users.getContent()) { // 获取搜索到的数据
            System.out.println(user);
        }
    }

}
