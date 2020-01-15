package com.word;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 对密码进行加密解密操作
 *
 * @author:mengw9
 * @date: 2019-07-04
 * @time: 15:44
 */
public class PasswordUtil {

    public static String encryptionPassword(String password) {
        try {
            //确定计算方法
            MessageDigest md5 = MessageDigest.getInstance("md5");
            //生成随机
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            String newstr = base64en.encode(md5.digest(password.getBytes("utf-8")));
            System.out.println("str==" + newstr);
            return newstr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String passwordAES(String password, String content) {
        try {
            // 创建AES的Key生产者
            KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
            kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
            // 128位的key生产者
            //加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
            SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
            byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

            Cipher cipher = Cipher.getInstance("AES");// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);// 加密
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        String passwordAES = passwordAES("123456", "123456");
        System.out.println("aes加密后"+passwordAES);
        String string = encryptionPassword(passwordAES);
        System.out.println("MD5对aes加密后密码加密(不可逆)：：" + string);
    }

}
