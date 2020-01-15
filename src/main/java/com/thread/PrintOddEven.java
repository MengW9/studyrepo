package com.thread;

/**
 * @program: algorithm_demo
 * @description: 多线程交替打印100
 * @author: mengw9
 * @create: 2019-09-24 17:08
 **/
public class PrintOddEven {

    /**
     * 打印值
     */
    private int count = 0;
    /**
     * 锁
     */
    private final Object lock = new Object();

    public void turning() {
        /**
         * 打印偶数
         */
        Thread even = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    //只处理偶数
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        },"偶数");

        /**
         * 打印奇数
         */
        Thread odd = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    //只处理奇数
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        },"奇数");
        even.start();
        odd.start();
    }

    public static void main(String[] args) {
        PrintOddEven printOddEven = new PrintOddEven();
        printOddEven.turning();
    }
}
