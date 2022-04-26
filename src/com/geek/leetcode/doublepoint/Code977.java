package com.geek.leetcode.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 21:32
 * 977. 有序数组的平方
 * https://leetcode-cn.com/problems/squares-of-a-sorted-array/
 *
 * 双指针法：左右指针法
 *
 */
public class Code977 {

}

class Solution977 {
    public int[] sortedSquares(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int[] res = new int[nums.length];
        int len = nums.length - 1;

        while (left <= right) {
            if (nums[right]*nums[right] > nums[left]*nums[left]){
                res[len] = nums[right] * nums[right];
                right--;
            } else {
                res[len] = nums[left] * nums[left];
                left++;
            }

            len--;
        }

        return res;
    }
}
