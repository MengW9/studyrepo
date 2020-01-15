package com.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * @program: algorithm_demo
 * @description: Jsoup例子
 * @author: mengw9
 * @create: 2019-10-22 17:18
 **/
public class JsoupDemo01 {


    private static void test() throws IOException {

        Document getDocument = Jsoup.connect("http://www.baidu.com").get();

        Document postDocument = Jsoup.connect("http://exmple.com")
                .data("query", "java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();

        Elements elementsByAttribute = postDocument.getElementsByAttribute("");

        String val = getDocument.select("#su").val();

        String su = getDocument.body().getElementById("su").val();


        String title = getDocument.title();
        System.out.println(getDocument.select("#su").val());
        System.out.println(getDocument.body().getElementById("su").val());
//        System.out.println(title);
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
