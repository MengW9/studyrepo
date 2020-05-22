package com.guava.preconditions;

import com.google.common.base.Preconditions;

/**
 * @description:
 * @author: MengW9
 * @create: 2020-05-22 16:28
 **/
public class PreconditinosDemo {
    public static void main(String[] args) {
        boolean demo=5<0;
        Preconditions.checkArgument(demo,"%s_%s为%s","参数","不能",demo);
    }
}
