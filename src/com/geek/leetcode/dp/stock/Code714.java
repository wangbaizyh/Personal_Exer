package com.geek.leetcode.dp.stock;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-26 22:43
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
            if (prices[i] >= minPrice && prices[i] <= minPrice + fee) continue;

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

// dp
class Solution714_01 {
    public int maxProfit(int[] prices, int fee) {
        // 状态：
        // dp[i][0] 持有股票
        // dp[i][1] 未持有股票
        int[][] dp = new int[prices.length][2];
        // 初始化
        dp[0][0] = -prices[0];

        // 遍历顺序
        for (int i = 1; i < prices.length; i++) {
            // 状态转移方程：
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i] - fee);
        }

        return dp[prices.length - 1][1];
    }
}

// 状态压缩
class Solution714_02 {
    public int maxProfit(int[] prices, int fee) {
        // 状态：
        // dp[i][0] 持有股票
        // dp[i][1] 未持有股票
        int[] dp = new int[2];
        // 初始化
        dp[0] = -prices[0];

        int[] pre;

        // 遍历顺序
        for (int i = 1; i < prices.length; i++) {
            pre = Arrays.copyOf(dp, 2);
            // 状态转移方程：
            dp[0] = Math.max(pre[0], pre[1] - prices[i]);
            dp[1] = Math.max(pre[1], pre[0] + prices[i] - fee);
        }

        return dp[1];
    }
}
