package com.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection1 {

    /**
     * 只发送请求   不带参数
     * @Description： 请求连接   请求类型
     * @Param： [urlParam, requestType]
     * @return： java.lang.String
     * @Author： WangMengWei
     * @Date： 2019-08-06
     * @Time： 9:07
     */
    public static String sendRequest(String urlParam, String requestType) {
        HttpURLConnection con = null;

        BufferedReader buffer = null;
        StringBuffer resultBuffer = null;

        try {
            URL url = new URL(urlParam);
            //得到连接对象
            con = (HttpURLConnection) url.openConnection();
            //设置请求类型
            con.setRequestMethod(requestType);
            //设置请求需要返回的数据类型和字符集类型
            con.setRequestProperty("Content-Type", "application/json;charset=GBK");
            con.setRequestProperty("Cookie", "80692dd3-1994-4cc0-adb3-722c0747de0c");
            //允许写出
            con.setDoOutput(true);
            //允许读入
            con.setDoInput(true);
            //不使用缓存
            con.setUseCaches(false);
            //得到响应码
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                //得到响应流
                InputStream inputStream = con.getInputStream();
                //将响应流转换成字符串
                resultBuffer = new StringBuffer();
                String line;
                buffer = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                while ((line = buffer.readLine()) != null) {
                    resultBuffer.append(line);
                }
                buffer.close();
                con.disconnect();
                return resultBuffer.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) {
//        String url ="https://ip.taobao.com/service/getIpInfo.php?ip=63.223.108.42";
        String url ="http://192.168.8.227:8899/api/v1/comment/query?caseId=341";
        System.out.println(sendRequest(url,"POST"));
    }
}
