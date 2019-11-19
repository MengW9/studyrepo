package com.adb;

import java.io.File;

/**
 * @program: algorithm_demo
 * @description: android手机操作
 * @author: WangMengWei
 * @create: 2019-11-19 14:29
 **/
public class App {

    String AppPackage=null;
    String AppActivity=null;
    String AppPath=null;

    /**
     * 设置App的包名
     * @param AppPackageName
     */
    AdbRequest request=new AdbRequest();
    public void setAppPackage(String AppPackageName){
        AppPackage=AppPackageName;
    }
    /**
     * 设置App的主Activity名称
     * @param AppActivityName
     */
    public void setAppActivity(String AppActivityName){
        AppActivity=AppActivityName;
    }
    /**
     * 设置App路径
     * @param AppPathName
     */
    public void setAppPath(String AppPathName){
        AppPath=new File(AppPathName).getAbsolutePath();
    }
    /**
     * 安装App
     */
    public void instaApp(){
        System.out.println(request.Request("adb install -r "+AppPath, true));
    }
    /**
     * 安装App
     */
    public void instaApp(Device device){
        request.Request("adb -s "+device.getDeviceId()+" install -r "+AppPath, true);
    }
    /**
     * 启动App
     */
    public void runApp(){
        request.Request("adb shell am start -n "+AppPackage+"/"+AppActivity, false);
    }
    /**
     * 判断是否当前App
     * @return
     */
    public boolean isApp(){
        boolean flag=true;
        request.Request("adb shell am start -n "+AppPackage+"/"+AppActivity, true);
        return flag;
    }
}
