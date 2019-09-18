package com.selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @program: algorithm_demo
 * @description: selenium工具类
 * @author: WangMengWei
 * @create: 2019-09-18 15:59
 **/
public class SeleniumUtils {

    /**
     * 判断某个元素是否存在
     * /html/body/div[6]/div/div[1]/div/a
     */
    public boolean isJudgingElement(WebDriver webDriver, By by) {
        try {
            webDriver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println("不存在此元素");
            return false;
        }
    }

}
