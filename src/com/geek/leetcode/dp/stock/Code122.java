package com.geek.leetcode.dp.stock;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-26 13:56
 * 122. 买卖股票的最佳时机 II
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 *
 * 思路：贪心、dp
 *
 */
public class Code122 {

}

// 贪心算法：只收集正利润
// 局部最优：收集每天的正利润，全局最优：求得最大利润。
class Solution122 {
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }

        return result;
    }
}

// dp
class Solution122_01 {
    public int maxProfit(int[] prices) {
        // 状态：
        // dp[i][0]第i天持有股票后的最多现金
        // dp[i][1]第i天持有的最多现金
        int[][] dp = new int[prices.length][2];
        int n = prices.length;
        // 初始状态：
        // 持股票
        dp[0][0] = -prices[0];
        // 不持股票
        dp[0][1] = 0;

        // 遍历顺序
        for (int i = 1; i < n; i++) {
            // 状态转移方程：
            // 第i天持股票所剩最多现金 = max(第i-1天持股票所剩现金, 第i-1天持现金-买第i天的股票)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 第i天持有最多现金 = max(第i-1天持有的最多现金，第i-1天持有股票的最多现金+第i天卖出股票)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[n - 1][1];
    }
}

// dp (状态压缩)
class Solution122_02 {
    public int maxProfit(int[] prices) {
        // 状态：
        // dp[0]第i天持有股票后的最多现金
        // dp[1]第i天持有的最多现金
        int[] dp = new int[2];
        int n = prices.length;
        // 初始状态：
        // 持股票
        dp[0] = -prices[0];
        int[] pre;

        // 遍历顺序
        for (int i = 1; i < n; i++) {
            pre = Arrays.copyOf(dp, 2);
            // 状态转移方程：
            // 第i天持股票所剩最多现金 = max(第i-1天持股票所剩现金, 第i-1天持现金-买第i天的股票)
            dp[0] = Math.max(pre[0], pre[1] - prices[i]);
            // 第i天持有最多现金 = max(第i-1天持有的最多现金，第i-1天持有股票的最多现金+第i天卖出股票)
            dp[1] = Math.max(pre[1], pre[0] + prices[i]);
        }

        return dp[1];
    }
}