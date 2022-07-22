package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-06-03 22:50
 * 剑指 Offer 14- I. 剪绳子
 * https://leetcode.cn/problems/jian-sheng-zi-lcof/
 *
 * 思路：动态规划
 *      推导：拆分成两个相乘，或者多个相乘（dp）
 *
 */
public class Offer14_1 {

}

// dp
class Solution14_1_01 {
    public int cuttingRope(int n) {
        // 状态：
        // dp[i] 长度为i的绳子的分割最大乘积
        int[] dp = new int[n + 1];
        // 初始化：
        dp[2] = 1;

        // 遍历顺序
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                // 状态转移方程：
                // 子任务拆分：拆分成两个相乘，拆分成多个相乘
                // i - j 最小为2， 因此 j < i - 1
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
            }
        }

        return dp[n];
    }
}