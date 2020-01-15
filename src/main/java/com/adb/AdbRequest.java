package com.adb;

import java.io.*;
import java.util.ArrayList;

/**
 * @program: algorithm_demo
 * @description: 发生adb请求
 * @author: mengw9
 * @create: 2019-11-19 14:28
 **/
public class AdbRequest {

    /**
     * adb命令发送
     *
     * @param param    发送的命令
     * @param isResult 是否有返回值
     * @return results 返回值
     * @author bony
     */
    @SuppressWarnings("unused")
    public ArrayList<String> Request(String param, boolean isResult) {
//		System.out.println("AdbRequest...");
        StringBuilder outresult = new StringBuilder();
        OutputStream oupt = null;
        BufferedWriter outputStream = null;
        BufferedReader inputStream = null, errorReader = null;
        String result;
        ArrayList<String> results = new ArrayList<String>();
        try {
            Process process = Runtime.getRuntime().exec(param);
            oupt = process.getOutputStream();
            outputStream = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((result = inputStream.readLine()) != null && isResult) {
                results.add(result.trim());
            }
            while ((result = errorReader.readLine()) != null && isResult) {
                results.add(result.trim());
            }
            process.waitFor();
            inputStream.close();
            errorReader.close();
        } catch (Exception e) {
            System.err.println("AdbRequest" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                    if (errorReader != null) {
                        errorReader.close();
                    }
                    outputStream.close();
                } catch (IOException e) {
                    System.err.println("AdbRequest" + e.getMessage());
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        return results;
    }

}
