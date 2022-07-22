package com.geek.jianzhi.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 14:29
 * 剑指 Offer 57. 和为s的两个数字
 * https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/
 *
 * 思路：双指针
 *
 */
public class Offer57_1 {

}

class Solution57_1 {
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }

        return new int[]{-1, -1};
    }
}