package com.geek.jianzhi.binarySearch;

/**
 * @author G.E.E.K.
 * @create 2022-06-26 12:11
 * 剑指 Offer 53 - II. 0～n-1中缺失的数字
 * https://leetcode.cn/problems/que-shi-de-shu-zi-lcof/
 *
 * 思路：二分查找
 *
 */
public class Offer53_2 {

}

class Solution53_2 {
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == mid) left++;
            else right--;
        }

        return left;
    }
}