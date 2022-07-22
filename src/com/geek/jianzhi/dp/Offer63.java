package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-06-28 21:59
 * 剑指 Offer 63. 股票的最大利润
 * https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/
 *
 * 思路：dp
 *
 */
public class Offer63 {

}

// dp
class Solution63 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        // 1.状态定义：
        // dp[i][0] 表示第i天持有股票所得最多现金
        // dp[i][1] 表示第i天不持有股票所得最多现金
        // 2.递推公式：
        // dp[i][0] = max(dp[i - 1][0], -prices[i])
        // dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0])
        int[][] dp = new int[prices.length][2];
        // 初始化：
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        // 3.遍历顺序
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }

        return dp[prices.length - 1][1];
    }
}

// 贪心
class Solution63_1 {
    public int maxProfit(int[] prices) {
        // 找到一个最小的购入点
        int low = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            low = Math.min(low, prices[i]);                 // 取最左最小价格
            result = Math.max(result, prices[i] - low);     // 直接取最大区间利润
        }

        return result;
    }
}