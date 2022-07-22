package com.geek.leetcode.dp.subsequence;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-27 16:10
 * 300. 最长递增子序列
 * https://leetcode.cn/problems/longest-increasing-subsequence/
 *
 * 思路：区间dp
 *
 */
public class Code300 {

}

class Solution300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        // 状态：
        // dp[i] 表示i之前包括i的以nums[i]结尾最长上升子序列的长度
        int[] dp = new int[nums.length];
        // 状态转移方程：
        // if (nums[i] > nums[j]) dp[i] = max(dp[i], dp[j] + 1)
        // 初始化：
        Arrays.fill(dp, 1);
        int result = 0;

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }

            if (dp[i] > result) result = dp[i];
        }

        return result;
    }
}