package com.file;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * @program: algorithm_demo
 * @description: 文件完整性校验
 * @author: mengw9
 * @create: 2020-03-17 11:26
 **/
public class FileIntegrity {

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\Desktop\\c4377fa444f35b861bc030c10414629e83025bebbf06ecebc262d7a988464b8495ff35e7d4a1c58ae15bb78bd3ea5228e77de970a8580549d30316227bc25c39.zip");
        FileInputStream fileInputStream = new FileInputStream(file);
        String hex = DigestUtils.sha512Hex(fileInputStream);
        System.out.println(hex);

        System.out.println("===========");
        File file1 = new File("D:\\测试打包.zip");
        FileInputStream fileInputStream1 = new FileInputStream(file1);
        String hex1 = DigestUtils.sha512Hex(fileInputStream1);
        System.out.println(hex1);
    }
}
