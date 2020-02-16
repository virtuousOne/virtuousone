package com.demo.virtuousone.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Author: 吴宸煊
 * Date: 2020/2/16 16:39
 * Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "itcast", type = "user", createIndex = false)
public class ElasticSearchUser {
    @Id
    private Long id;
    @Field(store = true)
    private String name;
    @Field
    private Integer age;
    @Field(store = true)
    private String hobby;
}
