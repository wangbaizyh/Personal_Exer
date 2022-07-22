package com.geek.leetcode.hashMap.staticHash;

/**
 * @author G.E.E.K.
 * @create 2022-07-19 12:11
 * 剑指 Offer 03. 数组中重复的数字
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * 思路：原地哈希
 * 原地哈希适用的问题：有明确范围的数字需要进行排序
 */
public class Offer03 {

}

class Solution3 {
    public int findRepeatNumber(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[nums[i]] != nums[i]) {
                swap(nums, nums[i], i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i) return nums[i];
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
