package com.compression;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: algorithm_demo
 * @description: TODO
 * @author: mengw9
 * @create: 2019-09-27 10:37
 **/
public class ZipUtil {

    static final int BUFFER = 8192;

    /**
     * 压缩一个文件
     * @param file 需要压缩的文件流
     * @param out 需要保存的zip包流
                ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File("D:\\Desktop\\test.zip")));
     * @param basedir 打包压缩包名
     */
    private void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        String filename = "";
        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            //判断 是否打包成单个文件，或者在文件夹里面
            if (basedir.isEmpty()) {
                filename = file.getName();
            } else {
                filename = basedir + "/" + file.getName();
            }
            ZipEntry entry = new ZipEntry(filename);
            out.putNextEntry(entry);
            int count;
            byte[] data = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
//            out.flush();
//            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void compress(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            System.out.println("压缩：" + basedir + file.getName());
            this.compressDirectory(file, out, basedir);
        } else {
            System.out.println("压缩：" + basedir + file.getName());
            this.compressFile(file, out, basedir);
        }
    }

    /** 压缩一个目录 */
    private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists()) {
            return;
        }

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compress(files[i], out, basedir + dir.getName() + "/");
        }
    }

    public static void main(String[] args) throws Exception {
        ZipUtil zipUtil = new ZipUtil();
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new File("D:\\Desktop\\test.zip")));
        //需要压缩文件路径
        String string = "E:\\project\\IdeaProjects\\algorithm_demo\\src\\main\\java\\com\\compression";
        //压缩一个文件
//        zipUtil.compressFile(new File(string), zipOutputStream, "test");

        zipUtil.compressDirectory(new File(string),zipOutputStream,"");

    }


}