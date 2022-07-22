package com.geek.leetcode.dp.stock;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-25 16:10
 * 121. 买卖股票的最佳时机
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 *
 * 思路：股票只买卖一次
 * 股票问题有两种状态：持有股票和不持股票
 *
 */
public class Code121 {

}

// 贪心
class Solution121 {
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


// dp
class Solution121_01 {
    public int maxProfit(int[] prices) {
        // 状态：
        // dp[i][0] 表示第i天持有股票所得最多现金
        // dp[i][1] 表示第i天不持有股票所得最多现金
        // 递推公式：
        // dp[i][0] = max(dp[i - 1][0], -prices[i])
        // dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0])
        int[][] dp = new int[prices.length][2];
        // 初始化：
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        // 遍历顺序
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);                  // 最小价格买入
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);    // 最大利润
        }

        return dp[prices.length - 1][1];
    }
}

// 滚动数组（状态压缩）
class Solution121_02 {
    public int maxProfit(int[] prices) {
        // 状态：
        // dp[0] 表示第i天持有股票所得最多现金
        // dp[1] 表示第i天不持有股票所得最多现金
        // 递推公式：
        // dp[i][0] = max(dp[i - 1][0], -prices[i])
        // dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0])
        int[] dp = new int[2];
        // 初始化：
        dp[0] = -prices[0];

        int[] pre;

        // 遍历顺序
        for (int i = 1; i < prices.length; i++) {
            pre = Arrays.copyOf(dp, 2);

            dp[0] = Math.max(pre[0], -prices[i]);            // 最小价格买入
            dp[1] = Math.max(pre[1], prices[i] + pre[0]);    // 最大利润
        }

        return dp[1];
    }
}