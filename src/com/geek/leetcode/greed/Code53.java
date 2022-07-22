package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-14 15:35
 * 53. 最大子数组和
 * https://leetcode.cn/problems/maximum-subarray/
 */
public class Code53 {

}

// 贪心算法
class Solution53 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count > result) {
                result = count;
            }
            // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            if (count <= 0) count = 0;
        }

        return result;
    }
}

// 动态规划（一维dp）
class Solution53_01 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        // 一维dp数组
        // 状态：dp[i]表示包括i之前的最大连续子序列和
        int[] dp = new int[nums.length];
        // 初始状态：
        dp[0] = nums[0];
        int result = dp[0];

        for (int i = 1; i < nums.length; i++) {
            // 状态转移方程:
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > result) result = dp[i];
        }

        return result;
    }
}