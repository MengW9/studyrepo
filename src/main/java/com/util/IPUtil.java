package com.util;

/**
 * @program: algorithm_demo
 *
 * @description: 判断字符串是否是ip的方法
 *
 * @author: MengW9
 *
 * @create: 2020-08-05 17:03
 **/
public class IPUtil {


    //判断字符是否是IP
    public boolean isCorrectIp(String ipString) {
        //1、判断是否是7-15位之间（0.0.0.0-255.255.255.255.255）
        if (ipString.length()<7||ipString.length()>15) {
            return false;
        }
        //2、判断是否能以小数点分成四段
        String[] ipArray = ipString.split("\\.");
        if (ipArray.length != 4) {
            return false;
        }
        for (int i = 0; i < ipArray.length; i++) {
            //3、判断每段是否都是数字
            try {
                int number = Integer.parseInt(ipArray[i]);
                //4.判断每段数字是否都在0-255之间
                if (number <0||number>255) {
                    return false;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    //利用正则表达式判断字符是否为IP
    public boolean isCorrectIp2(String ipString) {
        String ipRegex = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";	//IP地址的正则表达式
        //如果前三项判断都满足，就判断每段数字是否都位于0-255之间
        if (ipString.matches(ipRegex)) {
            String[] ipArray = ipString.split("\\.");
            for (int i = 0; i < ipArray.length; i++) {
                int number = Integer.parseInt(ipArray[i]);
                //4.判断每段数字是否都在0-255之间
                if (number <0||number>255) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;	//如果与正则表达式不匹配，则返回false
        }
    }


}
