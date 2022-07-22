package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-18 20:47
 * 714. 买卖股票的最佳时机含手续费
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 *
 */
public class Code714 {

}

// 贪心算法
class Solution714 {
    public int maxProfit(int[] prices, int fee) {
        int result = 0;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // 情况二：相当于买入
            if (prices[i] < minPrice) minPrice = prices[i];

            // 情况三：保持原有状态（因为此时买则不便宜，卖则亏本）
            if (prices[i] >= minPrice && prices[i] <= minPrice + fee) {
                continue;
            }

            // 计算利润，可能有多次计算利润，最后一次计算利润才是真正意义的卖出
            if (prices[i] > minPrice + fee) {
                result += prices[i] - minPrice - fee;
                // 情况一，这一步很关键
                minPrice = prices[i] - fee;
            }
        }

        return result;
    }
}

// 动态规划(二维dp)
class Solution714_01 {
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        // 状态：
        // dp[i][0] 未持股票的现金
        // dp[i][1] 持股票的现金
        // 初始状态：
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            // 状态转移方程：
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }

        return dp[prices.length - 1][0];
    }
}

// 动态规划(一维dp)
// 只跟前一个状态有关
class Solution714_02 {
    public int maxProfit(int[] prices, int fee) {
        // 状态：
        // 初始状态：
        int holdStock = -prices[0];
        int saleStock = 0;

        for (int i = 1; i < prices.length; i++) {
            int preSaleStock = saleStock;
            // 状态转移方程：
            saleStock = Math.max(saleStock, holdStock + prices[i] - fee);
            holdStock = Math.max(holdStock, preSaleStock - prices[i]);
        }

        return saleStock;
    }
}