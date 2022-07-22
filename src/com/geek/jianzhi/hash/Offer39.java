package com.geek.jianzhi.hash;

import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-06-22 10:22
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * https://leetcode.cn/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 *
 * 思路：
 *
 */
public class Offer39 {

}

// 摩尔投票法
class Solution39 {
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }

        return x;
    }
}


// 哈希表统计法
class Solution39_01 {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > len / 2) return nums[i];
        }

        return -1;
    }
}

// 数组排序法 快排
// 耗时严重！！！
class Solution39_02 {
    public int majorityElement(int[] nums) {
        quicksort(nums);
        int len = nums.length / 2 + 1;
        return nums[len];
    }

    private static void subSort(int[] data, int begin, int end) {
        if (begin >= end) return;

        // 基准
        int base = data[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            // 找到小于base的数
            while (left < right && data[right] >= base) {
                right--;
            }
            // 覆盖到左边
            data[left] = data[right];

            // 找到大于等于base的数
            while (left < right && data[left] <= base) {
                left++;
            }
            // 覆盖到右边
            data[right] = data[left];
        }

        data[left] = base;
        subSort(data, begin, left - 1);
        subSort(data, left + 1, end);
    }

    public static void quicksort(int[] data) {
        subSort(data, 0, data.length - 1);
    }
}