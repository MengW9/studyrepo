package com.sort;

/**
 * @program: algorithm_demo
 * @description: 传说中的睡眠排序
 * @author: mengw9
 * @create: 2019-09-17 11:37
 **/
public class ThreadSort {

    public static void main(String[] args) {
        //定义数组
        int[] nums = new int[]{12, 32, 56, 456, 13, 45, 132, 13};
        for (int num : nums) {
            new Thread(() -> {
                try {
                    Thread.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(num);
            }).start();
        }
    }
}
