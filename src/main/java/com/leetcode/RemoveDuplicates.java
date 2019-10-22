package com.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: algorithm_demo
 * @description: 从排序数组种删除重复项
 * @author: WangMengWei
 * @create: 2019-10-22 19:02
 **/
public class RemoveDuplicates {


    static int[] nums = {1, 1, 2, 2, 3, 3};

    /**
     * @Description： 能够实现，但是不符合题意
     * @Param： [nums]
     * @return： int
     * @Author： WangMengWei
     * @Date： 2019/10/22
     * @Time： 20:27
     */
    public static int removeDuplicates(int[] nums) {

        if (nums.length > 0) {
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).equals(list.get(i + 1))) {
                    list.remove(i);
                }
            }
            nums = list.stream().mapToInt(Integer::valueOf).toArray();
        }
        return nums.length;
    }

    public static int remove(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int number = 0;
        for (int i = 0; i < nums.length; i++) {
            //不相等时才会计数，并且调换位置
            if (nums[i] != nums[number]) {
                number++;
                nums[number] = nums[i];
            }
        }
        number += 1;
        return number;
    }

    public static void main(String[] args) {


        // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
//        int len = removeDuplicates(nums);
        int len = remove(nums);

        // 在函数里修改输入数组对于调用者是可见的。
        // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
        for (int i = 0; i < len; i++) {
            System.out.println(nums[i]);
        }

    }
}
