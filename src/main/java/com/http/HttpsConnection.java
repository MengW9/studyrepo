package com.http;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

/**
 * @program: yofc-pitp-root
 * @description: TODO
 * @author: WangMengWei
 * @create: 2019-08-06 11:54
 **/
public class HttpsConnection {

    /**
     * @Description： 处理https  GET/POST请求
     * @Param： [requestUrl, requestMethod, outputStr]  请求地址、请求方法、参数
     * @return： java.lang.String
     * @Author： WangMengWei
     * @Date： 2019-08-06
     * @Time： 11:56
     */
    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
        StringBuffer buffer = null;

        try {
            //创建SSLContext
            SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = {new MyX509TrustManager()};
            //初始化
            sslContext.init(null, tm, new java.security.SecureRandom());
            ;
            //获取SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod(requestMethod);
            //设置当前实例使用的SSLSoctetFactory
            conn.setSSLSocketFactory(ssf);
            conn.connect();
            //往服务器端写内容
            if (null != outputStr) {
                OutputStream os = conn.getOutputStream();
                os.write(outputStr.getBytes("utf-8"));
                os.close();
            }
            //读取服务器端返回的内容
            InputStream is=conn.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            buffer=new StringBuffer();
            String line=null;
            while((line=br.readLine())!=null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String s = httpsRequest("https://kyfw.12306.cn/otn/resources/login.html", "GET", null);
        System.out.println(s);
    }
}