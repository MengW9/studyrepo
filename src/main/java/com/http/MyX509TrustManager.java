package com.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @description: 自定义信任管理器   都为空则表示所有链接都安全
 * @author: mengw9
 * @create: 2019-08-06 11:50
 **/
public class MyX509TrustManager implements X509TrustManager {


    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        //TODO
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        //TODO
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
