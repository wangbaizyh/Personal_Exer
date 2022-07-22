package com.geek.leetcode.dp.base;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 14:51
 * 746. 使用最小花费爬楼梯
 * https://leetcode.cn/problems/min-cost-climbing-stairs/
 *
 */
public class Code746 {

}

// 第一步不花费
class Solution746 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        // 状态：
        // dp[i] 爬到第i层的最低花费
        // 初始化:
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            // 状态转移方程：
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[n];
    }
}

// 第一步花费
class Solution746_01 {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n];
        // 状态：
        // dp[i] 爬到第i层的最低花费
        // 初始化:
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; i++) {
            // 状态转移方程：
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }

        return Math.min(dp[n - 1], dp[n - 2]);
    }
}
