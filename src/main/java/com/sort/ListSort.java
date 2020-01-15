package com.sort;

import java.util.*;

/**
 * @program: algorithm_demo
 * @description: List排序
 * @author: mengw9
 * @create: 2019-08-29 14:35
 **/
public class ListSort {

    /**
     * 生成随机 不重复的字符串 : number 生成字符串个数
     */
    public static List<String> generateString(int number) {
        // 用于存放返回值
        List<String> listString = new ArrayList<String>();
        // 字符串长度
        List<Integer> length = null;
        // 中间变量
        StringBuffer sb = new StringBuffer();
        // 控制个数
        int control = 0;
        String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z" };
        while (true) {
            // 控制结束
            if ( control==number ) {
                break;
            }
            // 生成随机数，生成36位的2aaab761-4341-4968-aceb-3861ee3824b2 UUID类型数据
            String uuid = UUID.randomUUID().toString().replace("-", "");
            sb.setLength(0);
            // 获得随机字符串长度，长度不为0
            do {
                length = getDiffNo(1, 11);
            } while ( length.get(0)==0 );
            // 拼凑字符串
            for (int i=0; i<length.get(0); i++) {
                String str = uuid.substring(i*3, (i*3+3));
                //将str字符串转换为16进制，获得其值
                int x = Integer.parseInt(str, 16);
                //取余：x % 0x3E--0x3E = 3*16 + 14 = 62, 其中chars有62个字符
                sb.append(chars[x % 0x3E]);
            }
            listString.add(sb.toString());
            control++;
        }
        return listString;
    }

    /**
     * 生成随机不重复的数字 :n生成个数  max生成范围
     */
    public static List<Integer> getDiffNo(int n, int max) {
        // 生成 [0-n] 个不重复的随机数
        // list 用来保存这些随机数
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        Integer k;
        for (int i=0; i<n; i++) {
            do {
                k = random.nextInt(max);
            } while (list.contains(k));
            list.add(k);
        }
        return list;
    }



    /**
     * fixme:对Integer泛型的List进行排序
     *
     * 1.通过Collections.sort()方法，对Integer泛型的List进行排序；
     * 创建一个Integer泛型的List，插入十个100以内的不重复随机整数， 调用Collections.sort()方法对其进行排序
     * 2.排序规则：先数字后字母，数字0-9，字母A-Z a-z的顺序
     */
    public void listIntegerSort() {
        // 插入十个100以内的不重复随机整数
        List<Integer> integerList = getDiffNo(10, 100);
        System.out.println("-------------排序前--------------");
        for (Integer integer : integerList) {
            System.out.println("元素：" + integer);
        }
        Collections.sort(integerList);
        System.out.println("----------------排序后-------------------");
        for (Integer integer : integerList) {
            System.out.println("元素：" + integer);
        }
    }

    /**
     * fixme:对String泛型的List进行排序
     *
     * 1.对String泛型的List进行排序； 创建String泛型的List，添加乱序的String元素，
     * 调用sort方法，再次输出排序后的顺序
     */
    public void listStringSort() {
        List<String> stringList = new ArrayList<String>();
        stringList.add("eipJlcx");
        stringList.add("WvQRufC");
        stringList.add("J");
        stringList.add("HdaU2G");
        stringList.add("M0WswHD3");
        System.out.println("------------排序前-------------");
        for (String string : stringList) {
            System.out.println("元素：" + string);
        }
        Collections.sort(stringList);
        System.out.println("--------------排序后---------------");
        for (String string : stringList) {
            System.out.println("元素：" + string);
        }
    }

    /**
     * 对String泛型的List进行排序，要求随机生成10个的不重复字符串，字符串的长度在10以内
     */
    public void listStringRandomSort() {
        // 生成随机字符串
        List<String> listString = generateString(10);
        System.out.println("--------------排序前---------------");
        for (String integer : listString) {
            System.out.println("元素：" + integer);
        }
        // 排序
        Collections.sort(listString);
        System.out.println("----------------排序后------------------");
        for (String integer : listString) {
            System.out.println("元素：" + integer);
        }
    }





}
