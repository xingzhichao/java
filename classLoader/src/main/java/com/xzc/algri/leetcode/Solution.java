package com.xzc.algri.leetcode;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author zhichao.xing
 * @Date 2021/7/10 18:57
 * @Version 1.0
 **/
class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] returnVal = new int[]{0, 0};
        Map<Integer, Integer> dataMap = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (!dataMap.containsKey(nums[i])) {
                dataMap.put(nums[i], i);
            }
            int one = nums[i];
            int two = target - one;
            if (dataMap.containsKey(two)) {
                if (i != dataMap.get(two)) {
                    returnVal[0] = dataMap.get(two);
                    returnVal[1] = i;
                    break;
                }
            }
        }
        return returnVal;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 3};
        int[] ints = Solution.twoSum(array, 6);
        System.out.println(ints[0]);
        System.out.println(ints[1]);
    }
}
