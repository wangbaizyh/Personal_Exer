package com.geek.leetcode.hashMap;

import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-04-21 22:09
 * 1. 两数之和
 * https://leetcode-cn.com/problems/two-sum/
 *
 * hashMap
 *
 */
public class Code1 {

}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                int num1 = map.get(target - nums[i]);
                return new int[]{i,num1};
            } else {
                map.put(nums[i], i);
            }
        }

        return new int[]{};
    }
}