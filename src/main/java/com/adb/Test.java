package com.adb;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @program: algorithm_demo
 * @description:
 * @author: mengw9
 * @create: 2019-11-19 14:36
 **/
public class Test {

    public static void main123() throws Exception {
//        AdbRequest adbRequest = new AdbRequest();
//        adbRequest.Request("adb devices -l", true);
        Device device = new Device();
        System.out.println("获取设备信息：");
        JSONObject devicesJSONObject = device.getDevicesJSONObject();
        System.out.println(devicesJSONObject);
        System.out.println("获取设备列表：");
        ArrayList<Device> device1 = device.getDevice();
        device1.forEach(f -> System.out.println(f.DEVICEID + "===" + f.DEVICENAME));
//        System.out.println(device1.toString());
        System.out.println("设备版本：");
//        TestAdb();


    }

    public static void main(String[] args) throws Exception {
//        File file = new File("此电脑\\HUAWEI M6\\内部存储\\123.txt");
//        long length = file.length();
//        System.out.println(length);

        /**
         * 文件的目录
         * /data/data/storage/sdcard0/Android/data/uni.UNI11D28C7/apps/__UNI__11D28C7/doc
         * /data/data/storage/sdcard0/Android/data/io.dcloud.HBuilder/apps/HBuilder/doc
         *
         */
//        main123();

//        String cmd=AdbHome.AdbHome+"adb version";

//        String cmd=AdbHome.AdbHome+"adb mkdir /data/data/storage/sdcard0/Android/data/io.dcloud.HBuilder/apps/HBuilder/doc/images";
//        TestAdb(cmd);

        //测试是否存在设备
//        String cmd12 = AdbHome.AdbHome + "adb devices | findstr \"\\<device\\>\"";
        String cmd = AdbHome.AdbHome + "adb devices";
        String string = TestAdb(cmd).replaceAll("List of devices attached", "").replaceAll("\n", "");
        if (!string.isEmpty()) {
            System.out.println("已连接设备，设备名为：" + string);
        } else {
            System.out.println("未连接设备");
        }
//        String cmd123=AdbHome.AdbHome+"adb push D:\\Desktop\\胖菊1.gif /storage/sdcard0/Android/data/io.dcloud.HBuilder/apps/HBuilder/doc/images/胖菊1.gif";
//        TestAdb(cmd123);
        //判断是否有这个文件
//        String cmd = AdbHome.AdbHome + "adb shell ls /storage/sdcard0/Android/data/uni.UNI11D28C7/apps/__UNI__11D28C7/doc/local.db";
//        String s = TestAdb(cmd);
//        if (s.contains("/storage/sdcard0/Android/data/uni.UNI11D28C7/apps/__UNI__11D28C7/doc/local.db")){
//            System.out.println("存在此文件");
//        }else {
//            System.out.println("没有此文件");
//        }
//        System.out.println(s);

//        String cmd = AdbHome.AdbHome + "adb shell pm list packages";
//        String s = TestAdb(cmd);
//        if (s.contains("uni.UNI11D28C7")){
//            System.out.println("存在此文件");
//        }else {
//            System.out.println("没有此文件");
//        }
//        System.out.println(s);


    }


    private static String TestAdb(String cmd) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
            String string = InputStream2String(process.getInputStream());
            System.out.print(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String InputStream2String(InputStream inputStream) {
        String result = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result += temp + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
