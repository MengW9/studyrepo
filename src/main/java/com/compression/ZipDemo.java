package com.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: algorithm_demo
 * @description: zip压缩文件
 * @author: WangMengWei
 * @create: 2019-09-26 20:48
 **/
public class ZipDemo {

    private void zip(String zipFileName, File inputFile) throws Exception {
        // 创建ZipOutputStream类对象
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                zipFileName));
        // 调用方法
        zip(out, inputFile, "");
        // 输出信息
        System.out.println("压缩中…");
        out.close(); // 将流关闭
    }

    private void zip(ZipOutputStream out, File f, String base)
            throws Exception {
        // 方法重载
        // 测试此抽象路径名表示的文件是否是一个目录
        if (f.isDirectory()) {
            // 获取路径数组
            File[] fl = f.listFiles();
            // 写入此目录的entry
            out.putNextEntry(new ZipEntry(base + "/"));
            // 判断参数是否为空
            base = base.length() == 0 ? "" : base + "/";
            // 循环遍历数组中文件
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i]);
            }
        } else {
            // 创建新的进入点
            out.putNextEntry(new ZipEntry(base));
            // 创建FileInputStream对象
            FileInputStream in = new FileInputStream(f);
            int b; // 定义int型变量
            System.out.println(base);
            // 如果没有到达流的尾部
            while ((b = in.read()) != -1) {
                // 将字节写入当前ZIP条目
                out.write(b);
            }
            // 关闭流
            in.close();
        }
    }

    public static void main(String[] temp) {
        ZipDemo zipDemo = new ZipDemo();
        //输入路径
        String string = "src";
        try {
            // 调用方法，参数为压缩后文件与要压缩文件
            zipDemo.zip("test.zip", new File(string));
            // 输出信息
            System.out.println("压缩完成");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
