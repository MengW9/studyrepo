package com.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @program: algorithm_demo
 * @description: TODO
 * @author: mengw9
 * @create: 2019-08-08 16:42
 **/
public class First {

    public static void start() {
        //配置ChromeDiver
        System.setProperty("webdriver.chrome.driver", "E:\\selenium\\chromedriver_win32\\chromedriver.exe");
        //开启新webDriver进程
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        //设定网址  必须加协议  不然会有意想不到的错误  Unexpected error
        webDriver.get("https://www.baidu.com");
        try {
            sleep(1000);
            //全局隐式等待，等待
//            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取当前浏览器的信息
        webDriver.close();

    }

    public static void main(String[] args) {
        start();
    }
}
