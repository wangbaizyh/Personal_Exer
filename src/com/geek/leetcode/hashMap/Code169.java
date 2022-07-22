package com.geek.leetcode.hashMap;

import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-07-07 17:06
 * 169. 多数元素
 * https://leetcode.cn/problems/majority-element/
 *
 * 思路：哈希统计法 摩尔投票法 （快速排序）
 */
public class Code169 {

}

// 摩尔投票法
class Solution169 {
    public int majorityElement(int[] nums) {
        // 投票数字 票数
        int x = 0, votes = 0;
        for (int num : nums) {
            // 票数为0的时候 更改投票数字
            if (votes == 0) x = num;
            // 票数 ：当前数字等于投票数字 + 1 ； 不等于 - 1
            votes += num == x ? 1 : -1;
        }

        return x;
    }
}

// 哈希表统计法
class Solution169_01 {
    public int majorityElement(int[] nums) {
        // 统计数字出现的次数
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            // 如果当前数字次数大于数组长度的一半，就是多数元素
            if (map.get(nums[i]) > len / 2) return nums[i];
        }

        return -1;
    }
}