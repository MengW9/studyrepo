package com.adb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @program: algorithm_demo
 * @description:
 * @author: WangMengWei
 * @create: 2019-11-19 14:36
 **/
public class Test {

    public static void main(String[] args) throws IOException {
//        AdbRequest adbRequest = new AdbRequest();
//        adbRequest.Request("adb devices -l", true);
//        Device device = new Device();
//        device.getDevicesJSONObject();


    }

    public static void TestAdb(){
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
