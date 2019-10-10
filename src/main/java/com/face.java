package com;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: algorithm_demo
 * @description: 面试题集锦
 * @author: WangMengWei
 * @create: 2019-09-24 23:19
 **/
public class face {

    /*public static void main(String[] args) {
        List<String> a = null;
        test(a);
        System.out.println(a.size());
    }

    private static void test(List<String> a) {
        a = new ArrayList<String>();
        a.add("abc");
    }*/

   /* public static void main(String[] args) {
        int x = 2;
        int y = 3;
        System.out.print(x | y);
        System.out.print(",");
        System.out.print(x & y);
        System.out.print(",");
        System.out.print(x ^ y);
        System.out.print(",");
        System.out.print(y << x);
        System.out.print(",");
        System.out.print(-y);

    }*/

    public static void main(String[] args) {

        int[] nums = new int[]{1, 1, 2};
        //存放不重复的数据
        List<Integer> temp = new ArrayList<Integer>();
        List<Integer> t = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (!temp.contains(nums[i])) {
                temp.add(nums[i]);
                t.add(i);
            }
        }

        System.out.println(temp.toString());
        //返回的值

        /*String str = "";

        Scanner scan = new Scanner(System.in);

        str = scan.nextLine();

        permutation(str.toCharArray(), 0);*/
    }

    public static void permutation(char[] str, int i) {
        if (i >= str.length) {
            return;
        }
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            for (int j = i; j < str.length; j++) {
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;

                permutation(str, i + 1);

                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }


}