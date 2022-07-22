package com.geek.leetcode.dp.stock;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-26 15:13
 * 188. 买卖股票的最佳时机 IV
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/
 *
 * 思路：类似于最多可以完成两笔交易
 * 本题和 123.买卖股票的最佳时机III 最大的区别就是这里要类比j为奇数是买，偶数是卖的状态。
 *
 */
public class Code188 {

}

class Solution188 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        // 状态数目
        int m = 1 + k * 2;
        // 状态：
        int[][] dp = new int[n][m];
        // 初始化：
        // 奇数代表买
        for (int i = 1; i < m; i += 2) {
            dp[0][i] = -prices[0];
        }

        // 遍历顺序：
        for (int i = 1; i < prices.length; i++) {
            // 状态转移方程：
            // 没有股票
            dp[i][0] = dp[i - 1][0];
            for (int j = 1; j < m; j++) {
                // 奇数：买 偶数：卖
                if (j % 2 == 1) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] - prices[i]);
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + prices[i]);
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}

// 状态压缩
class Solution188_01 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int m = 1 + k * 2;
        int[] dp = new int[m];
        for (int i = 0; i < m; i++) {
            if (i % 2 == 1) dp[i] = -prices[0];
        }

        int[] pre;

        for (int i = 1; i < n; i++) {
            pre = Arrays.copyOf(dp, m);
            dp[0] = pre[0];

            for (int j = 1; j < m; j++) {
                if (j % 2 == 1) {
                    dp[j] = Math.max(pre[j], pre[j - 1] - prices[i]);
                } else {
                    dp[j] = Math.max(pre[j], pre[j - 1] + prices[i]);
                }
            }
        }

        return dp[m - 1];
    }
}