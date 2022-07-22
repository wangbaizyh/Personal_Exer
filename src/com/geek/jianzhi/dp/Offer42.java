package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-06-22 21:30
 * 剑指 Offer 42. 连续子数组的最大和
 * https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/
 *
 * 思路：dp
 *
 */
public class Offer42 {

}

class Solution42 {
    public int maxSubArray(int[] nums) {
        // 状态：
        // dp[i] 以i为结尾的子数组的最大值
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        int ans = dp[0];

        // 遍历顺序
        for (int i = 1; i < nums.length; i++) {
            // 状态转移方程：
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}

// 使用原数组做dp数组
class Solution42_01 {
    public int maxSubArray(int[] nums) {
        // 状态：
        // nums[i] 以i为结尾的子数组的最大值
        int ans = nums[0];
        // 遍历顺序
        for (int i = 1; i < nums.length; i++) {
            // 状态转移方程：
            nums[i] = Math.max(nums[i], nums[i - 1] + nums[i]);
            ans = Math.max(ans, nums[i]);
        }

        return ans;
    }
}