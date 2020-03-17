package com.demo.virtuousone.crawler;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;


import java.net.URL;

/**
 * @Author: 吴宸煊
 * Date: 2020/3/16 20:19
 * Description:
 */
public class JsoupDemo {

    public static void main(String[] args) throws Exception {
        // 解析URL地址，第一个参数是URL，第二个参数是时间
        Document document = (Document) Jsoup.parse(new URL("http://www.itcast.cn/"), 1000);
        //使用标签选择器，获取title内容
        String content = document.getElementsByTag("title").first().text();
        System.out.println(content);
    }
}
