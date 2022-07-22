package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-14 16:06
 * 122. 买卖股票的最佳时机 II
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 *
 */
public class Code122 {

}

// 贪心
class Solution122 {
    public int maxProfit(int[] prices) {
        int buy = 0;
        int profit = 0;
        boolean hold = false;

        for (int i = 1; i < prices.length; i++) {
            // 买入状态：价格低时买入
            if (!hold && prices[i - 1] < prices[i]) {
                buy = i - 1;
                hold = true;
            }

            // 持有状态，遇到价格开始下降，马上卖出，得到获利
            if (hold && prices[i - 1] > prices[i]) {
                profit += prices[i - 1] - prices[buy];
                hold = false;
            }

            // 一直持有到最后一天，计算最后一次利润
            if (hold && i == prices.length - 1) {
                profit += prices[i] - prices[buy];
            }
        }

        return profit;
    }
}

// 贪心算法：只收集正利润
// 局部最优：收集每天的正利润，全局最优：求得最大利润。
class Solution122_01 {
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }

        return result;
    }
}

// 动态规划
class Solution122_02 {
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