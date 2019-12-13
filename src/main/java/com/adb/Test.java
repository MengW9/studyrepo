package com.adb;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * @program: algorithm_demo
 * @description:
 * @author: WangMengWei
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
        device1.forEach(f-> System.out.println(f.DEVICEID+"==="+f.DEVICENAME));
//        System.out.println(device1.toString());
        System.out.println("设备版本：");
        TestAdb();

    }

    public static void main(String[] args) throws Exception {
//        File file = new File("此电脑\\HUAWEI M6\\内部存储\\123.txt");
//        long length = file.length();
//        System.out.println(length);

        /**
         * 文件的目录
         * /data/data/storage/sdcard0/Android/data/uni.UNI11D28C7/apps/__UNI__11D28C7/doc
         */
        main123();
    }


    private static void TestAdb(){
        String cmd=AdbHome.AdbHome+"adb version";
        Process process;
        try {
            process=Runtime.getRuntime().exec(cmd);
            System.out.println(InputStream2String(process.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String InputStream2String(InputStream inputStream){
        String result="";
        BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
        try {
            String temp="";
            while ((temp=br.readLine())!=null){
                result+=temp+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
