package com.fileservice;

import javax.swing.filechooser.FileSystemView;
import java.io.File;

/**
 * @program: algorithm_demo
 * @description: 查找盘符
 * @author: WangMengWei
 * @create: 2019-11-19 09:57
 **/
public class SearchDrive {

    /**
     * @Description： 查找
     * @Param： []
     * @return： void
     * @Author： WangMengWei
     * @Date： 2019-11-19
     * @Time： 09:58
     */
    public void Search(){

        while (true) {

        }

    }

    public static void main(String[] args) {

        FileSystemView sys = FileSystemView.getFileSystemView();

        File[] files = File.listRoots();

        for(int i = 0; i < files.length; i++) {

            System.out.println(files[i] + " -- " + sys.getSystemTypeDescription(files[i]));
        }

//        System.out.println("=====");
//        File file = new File("此电脑\\honor10\\内部存储");
//        File [] filesList = file.listFiles();
//        for (File file1 : filesList) {
//            System.out.println(file1);
//        }
    }
}
