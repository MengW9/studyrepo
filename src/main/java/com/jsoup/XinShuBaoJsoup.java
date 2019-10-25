package com.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @program: algorithm_demo
 * @description: https://www.xinshubao.net小说
 * @author: WangMengWei
 * @create: 2019-10-25 15:27
 **/
public class XinShuBaoJsoup {

    public static void getUrl() throws Exception {

        Document getDocument = Jsoup.connect("https://www.xinshubao.net/9/9895/").get();
        Element list = getDocument.getElementById("list");
        Elements li = list.getElementsByTag("a");
        li.forEach(a -> {
            //标题
            String name = a.html();
            //链接
            String href = a.attr("href");


            System.out.println(href);
            System.out.println(name);

        });
//        System.out.println(li);
//        System.out.println(name);

    }

    private static String getText(String url) throws Exception {
        Document getDocument = Jsoup.connect(url).get();
        StringBuffer stringBuffer = new StringBuffer();
        Elements bottem1 = getDocument.getElementsByClass("bottem1");
        int i = 2;
        //第一页
        Element content = getDocument.getElementById("content");
        stringBuffer.append(content.html());
        //判断是否有下一页  有则取下一页文本
        if (isHive(bottem1)) {
            String[] split = url.split("\\.");
            url = split[0] + "." + split[1] + "." + split[2] + "_2." + split[3];
            System.out.println(split[0] + "." + split[1] + "." + split[2] + "_2." + split[3]);
        }

//        System.out.println(content);
        return "";
    }

    private static boolean isHive(Elements document) {
        return document.html().contains("下一页");
    }

    public static void main(String[] args) throws Exception {
//        getUrl();
        String url = "https://www.xinshubao.net/9/9895/25741537.html";
        getText(url);
    }

}
