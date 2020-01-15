package com.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: algorithm_demo
 * @description: map学习
 * @author: mengw9
 * @create: 2019-10-10 11:12
 **/
public class TestMap {

    public static void main(String[] args) {
        Map<String, String> stringMap = new HashMap<>();
        Map<String, String> stringMap2 = new HashMap<>();
        stringMap.put("1","1value");
        stringMap.put("2","2value");
        stringMap.put("3","3value");
        stringMap.put("4","4value");
        stringMap2.put("1","21value");
        stringMap2.put("2","22value");
        stringMap2.put("3","23value");
        stringMap2.put("4","24value");

        stringMap.forEach((k,v)->{
            System.out.println(k+"==="+v);
            System.out.println(stringMap2.get(k)+"===");
        });

    }
}
