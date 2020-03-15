package com.demo.virtuousone.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 吴宸煊
 * Date: 2020/3/15 16:13
 * Description: POST请求，带参数爬虫
 */
public class CrawlerDemo4 {
    public static void main(String[] args) throws Exception {
        //1.打开浏览器-----创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址---发请求Get，创建HttpPost对象  http://yun.itheima.com/search
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");
        // 声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("keys", "java"));
        //创建表单的Entity，第一个参数是封装好的，第二个参数是表单格式
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");
        // 设置表单的entity到post请求中
        httpPost.setEntity(formEntity);
        System.out.println(httpPost);
        //3.按回车键，发请求，返回响应-----使用HttpClient对象发起请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //4.解析响应，返回数据---正确的状态码是返回200
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf8");
            System.out.println(content);
        }
    }
}
