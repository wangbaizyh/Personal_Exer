package com.geek.leetcode.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 20:52
 * 283. 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * 双指针法：快慢指针法
 *
 */
public class Code283 {

}

class Solution283 {
    public void moveZeroes(int[] nums) {
        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {
            // 快指针为0的时候跳过
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}