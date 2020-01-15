package com.adb;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * @program: algorithm_demo
 * @description: 实体类
 * @author: mengw9
 * @create: 2019-11-19 14:30
 **/
public class Device {

    String DEVICENAME = null;
    String DEVICEID = null;

    /**
     * 设置设备名称
     *
     * @param DeviceName
     */
    public void setDeviceName(String DeviceName) {
        DEVICENAME = DeviceName;
    }

    /**
     * 获取设备名称
     *
     * @return
     */
    public String getDeviceName() {
        return DEVICENAME;
    }

    /**
     * 设置设备唯一标示码
     *
     * @param DeviceId
     */
    public void setDeviceId(String DeviceId) {
        DEVICEID = DeviceId;
    }

    /**
     * 获取设备唯一标示码
     *
     * @return
     */
    public String getDeviceId() {
        return DEVICEID;
    }


    /**
     * 获取设备列表
     *
     * @return
     */
    public ArrayList<Device> getDevice() {
        ArrayList<Device> devices = new ArrayList<Device>();
        JSONObject DEVJson = new Device().getDevicesJSONObject();
        if (DEVJson.get("ret").equals("0")) {
            JSONArray DEV = DEVJson.getJSONArray("device");
            for (int i = 0; i < DEV.size(); i++) {
                Device device = new Device();
                device.setDeviceName(DEV.getJSONObject(i).getString("Name"));
                device.setDeviceId(DEV.getJSONObject(i).getString("Id"));
                devices.add(device);
            }
//			RunTest();
        } else {
            System.err.println(DEVJson.get("message"));
        }
        return devices;
    }

    /**
     * 获取json格式设备信息
     *
     * @return devices
     * @author
     */
    public JSONObject getDevicesJSONObject() {
        System.out.println("getDevice...");
        JSONObject Dwevices = new JSONObject();
        ArrayList<String> Request = new AdbRequest().Request(AdbHome.AdbHome+"adb devices -l", true);
        if (Request.size() > 2) {
            Dwevices.put("ret", "0");
            JSONObject phone = new JSONObject();
            JSONArray phones = new JSONArray();
            for (int i = 1; i < Request.size() - 1; i++) {
                String linestr = Request.get(i);
                String name = linestr.substring(linestr.indexOf("device:") + 7, linestr.length()).trim();
                String Id = linestr.substring(0, linestr.indexOf("device")).trim();
                phone.put("Name", name);
                phone.put("Id", Id);
                phones.add(phone);
//				devices.add(Request.get(i).substring(0,23).trim());
            }
            Dwevices.put("device", phones);
        } else {
            Dwevices.put("ret", "1");
            Dwevices.put("message", "List of devices attached");
        }

//		System.out.println(new JsonTools().format(Dwevices.toString()));//打印设备信息
        return Dwevices;
    }

}
