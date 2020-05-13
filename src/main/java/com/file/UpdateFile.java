package com.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: algorithm_demo
 * @description: 操作文件或者文件夹
 * @author: mengw9
 * @create: 2020-03-17 21:47
 **/
public class UpdateFile {

    /**
     * 重命名文件
     * @param file
     * @param name
     * @return File
     */
    public static File renameFile(File file , String name ){
        String fileName = file.getParent()  + File.separator + name ;
        File dest = new File(fileName);
        file.renameTo(dest) ;
        return dest ;
    }

    /**
     * 重命名文件
     * @param fileName
     * @return
     */
    public static void renameFile(String filePath, String fileName) {
        SimpleDateFormat fmdate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String oldFileName = filePath+"/"+fileName;
        File oldFile = new File(oldFileName);
        String newFileName = filePath+"/"+fileName.split("\\.")[0]+fmdate.format(new Date())+"."+fileName.split("\\.")[1];
        File newFile = new File(newFileName);
        if (oldFile.exists() && oldFile.isFile()) {
            oldFile.renameTo(newFile);
        }
    }

    public static void main(String[] args) {
        renameFile(new File("D:\\Desktop\\456.zip"), "12323543223423523234532.zip");
//        renameFile("D:\\Desktop\\", "456.zip");
    }
}
