package com.sort;

import java.util.Random;

/**
 * @program: algorithm_demo
 * @description: 这是一个插入排序的demo
 * @author: WangMengWei
 * @create: 2019-07-19 14:29
 **/
public class FirstDemo {
    /**
     * @Description： 插入排序：直接插入排序，希尔排序
     * @Param： []
     * @return： void
     * @Author： WangMengWei
     * @Date： 2019-07-19
     * @Time： 15:26
     */
    public static void straighInsertionSort(double[] sorted) {
        int length = sorted.length;
        //数组的第0个下标没有放数据，从第二个数据开始
        for (int i = 2; i < length; i++) {

            if (sorted[i] < sorted[i - 1]) {
                //设置哨兵
                sorted[0] = sorted[i];
                //将数据前面那个数据后移一位
                sorted[i] = sorted[i - 1];

                int insert = 0;
                for (int j = i - 2; j >= 0; j--) {
                    if (sorted[j] > sorted[0]) {
                        //后移一位
                        sorted[j + 1] = sorted[j];
                    } else {
                        //插入的位置
                        insert = j + 1;
                        break;
                    }
                }
                sorted[insert] = sorted[0];
            }
        }
    }


    public static void main(String[] args) {
        Random random = new Random(6);
        int arraySize = 5;
        double[] sorted = new double[arraySize];

        System.out.println("排序前：");
        for (int i = 1; i < arraySize; i++) {
            sorted[i] = (int) (random.nextDouble() * 100);
            System.out.print((int) sorted[i] + " ");
        }
        System.out.println();

        FirstDemo.straighInsertionSort(sorted);
        System.out.println("排序后：");
        for (int i = 1; i < sorted.length; i++) {
            System.out.print((int) sorted[i] + " ");
        }
        System.out.println();


    }


}
