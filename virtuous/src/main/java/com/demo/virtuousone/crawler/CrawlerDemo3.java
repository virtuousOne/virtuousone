package com.demo.virtuousone.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Author: 吴宸煊
 * Date: 2020/3/15 16:09
 * Description: POST请求爬虫
 */
public class CrawlerDemo3 {
    public static void main(String[] args) throws Exception {
        //1.打开浏览器-----创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址---发请求Get，创建HttpGet对象
        HttpPost httpPost = new HttpPost("https://www.zhipin.com/wuhan/");
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
