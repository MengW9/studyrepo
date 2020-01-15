package com.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @description: 发送http请求
 * @author: mengw9
 * @create: 2019-08-06 09:17
 **/
public class HttpConnection {

    /**
     * 发起http请求并获取结果  GET请求
     *
     * @param requestUrl 请求地址
     * @return json
     */
    public static JsonObject getXpath(String requestUrl) {
        String res = "";
        JsonObject object = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            System.out.println(urlCon.getResponseCode());
            if (200 == urlCon.getResponseCode()) {
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String str = null;
                while ((str = br.readLine()) != null) {
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                JsonParser parse = new JsonParser();
                object = (JsonObject) parse.parse(res);
            } else {
                throw new Exception("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static String get(String requestUrl) {
        String res = "";
        JsonObject object = null;
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            System.out.println(urlCon.getResponseCode());
            if (200 == urlCon.getResponseCode()) {
                InputStream is = urlCon.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String str = null;
                while ((str = br.readLine()) != null) {
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
            } else {
                throw new Exception("连接失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @Description： 发送POST请求
     * @Param： [path, post] path请求地址   post请求参数
     * @return： com.google.gson.JsonObject
     */
    public static JsonObject postDownloadJson(String requestUrl, String requestMethod) {
        URL url = null;
        try {
            url = new URL(requestUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // 提交模式
            httpURLConnection.setRequestMethod("POST");
            //连接超时 单位毫秒
            httpURLConnection.setConnectTimeout(10000);
            //读取超时 单位毫秒
            httpURLConnection.setReadTimeout(2000);
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
            // 发送请求参数
            //post的参数 xx=xx&yy=yy
            printWriter.write(requestMethod);
            // flush输出流的缓冲
            printWriter.flush();
            //开始获取数据
            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len;
            byte[] arr = new byte[1024];
            while ((len = bis.read(arr)) != -1) {
                bos.write(arr, 0, len);
                bos.flush();
            }
            bos.close();
            JsonParser parse = new JsonParser();
            return (JsonObject) parse.parse(bos.toString("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //测试
    public static void main(String args[]) {
        JsonObject res = null;
        //存储cookie
        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        res = postDownloadJson("http://192.168.21.23:8080/api/v1/login", "name=admin123&pwd=221367c291a8db838b755ac317e04230");
        System.out.println(res);
        res = postDownloadJson("http://192.168.21.23:8080/api/v1/cip/job/fuzzy","keyWords=1&page=1&size=5");
        System.out.println(res);

//        System.out.println(get("https://www.baidu.com"));
        //res = postDownloadJson("http://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42", "123");
        /*System.out.println(res.get("code"));
        System.out.println(res.get("data"));*/
    }

}
