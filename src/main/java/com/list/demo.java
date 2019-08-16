package com.list;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * @program: algorithm_demo
 * @description: TODO
 * @author: WangMengWei
 * @create: 2019-08-15 18:23
 **/
public class demo {

    public static void main(String[] args) {

        String str = "[{\"sourceId\":115750,\"showValue\":\"张思化\",\"dataType\":\"string\",\"searchValue\":\" 张思化\"},{\"sourceId\":115750,\"showValue\":\"马春亮\",\"dataType\":\"string\",\"searchValue\":\" 马春亮\"},{\"sourceId\":115750,\"showValue\":\"吴晋\",\"dataType\":\"string\",\"searchValue\":\" 吴 晋\"},{\"sourceId\":115750,\"showValue\":\"杜思远\",\"dataType\":\"string\",\"searchValue\":\" 杜思远\"}]";

        JSONArray objects = JSON.parseArray(str);

        for (int i = 0; i < objects.size(); i++) {

            System.out.println(objects.get(i));
            System.out.println(objects.getJSONObject(i));
            System.out.println(objects.getJSONObject(i).get("showValue"));

//            System.out.println(objects.get(i));
//            System.out.println(new JSONObject(objects.get(i)));
//            System.out.println(new JSONObject(objects.get(i)).get("showValue"));
            System.out.println("======");

        }
    }
}
