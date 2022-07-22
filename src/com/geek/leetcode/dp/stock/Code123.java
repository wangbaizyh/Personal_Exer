package com.geek.leetcode.dp.stock;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-26 14:16
 * 123. 买卖股票的最佳时机 III
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 *
 * 分析：
 * 1.确定dp数组以及下标的含义
 * 一天一共就有五个状态:
 *      0 没有股票
 *      1 第一次买入
 *      2 第一次卖出
 *      3 第二次买入
 *      4 第二次卖出
 * dp[i][j]中 i表示第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金
 * 2.确定递推公式
 * dp[i][1]表示的是第i天持有一只股票的状态
 * 达到dp[i][1]状态，有两个具体操作：
 *      操作一：第i天买入股票了，那么dp[i][1] = dp[i-1][0] - prices[i]
 *      操作二：第i天没有操作，而是沿用前一天买入的状态，即：dp[i][1] = dp[i - 1][1]
 * dp[i][1] = max(dp[i-1][0] - prices[i], dp[i - 1][1])
 *
 * 同理dp[i][2]也有两个操作：
 *      操作一：第i天卖出股票了，那么dp[i][2] = dp[i - 1][1] + prices[i]
 *      操作二：第i天没有操作，沿用前一天卖出股票的状态，即：dp[i][2] = dp[i - 1][2]
 * dp[i][2] = max(dp[i - 1][1] + prices[i], dp[i - 1][2])
 *
 * 同理可推出剩下状态部分：
 * dp[i][3] = max(dp[i - 1][3], dp[i - 1][2] - prices[i])
 * dp[i][4] = max(dp[i - 1][4], dp[i - 1][3] + prices[i])
 *
 * 3.dp数组如何初始化
 * 第0天没有操作，这个最容易想到，就是0，即：dp[0][0] = 0;
 * 第0天做第一次买入的操作，dp[0][1] = -prices[0];
 * 第0天做第一次卖出的操作，这个初始值应该是多少呢？ dp[0][2] = 0;
 * 第0天第二次买入操作，初始值应该是多少呢？ 第二次买入依赖于第一次卖出的状态 dp[0][3] = -prices[0]
 * 同理第二次卖出初始化dp[0][4] = 0
 *
 * 4.确定遍历顺序
 * 从前向后遍历，因为dp[i]，依靠dp[i - 1]的数值
 *
 * 5.举例推导dp数组
 *
 * 最大的时候一定是卖出的状态，而两次卖出的状态现金最大一定是最后一次卖出
 * 所以最终最大利润是dp[n-1][4]
 */
public class Code123 {

}


// dp
class Solution123 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        // 状态定义：五种状态
        int[][] dp = new int[prices.length][5];
        // 初始化：
        dp[0][1] = -prices[0];
        dp[0][3] = -prices[0];
        // 遍历顺序
        for (int i = 1; i < prices.length; i++) {
            // 状态转移方程
            // 没有股票
            dp[i][0] = dp[i - 1][0];
            // 第一次买入
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            // 第一次卖出
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            // 第二次买入
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            // 第二次卖出
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }
        return dp[prices.length - 1][4];
    }
}

// 状态压缩
class Solution123_01 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int[] dp = new int[5];
        dp[1] = -prices[0];
        dp[3] = -prices[0];
        int[] pre;

        for (int i = 1; i < prices.length; i++) {
            pre = Arrays.copyOf(dp, 5);
            dp[0] = pre[0];
            dp[1] = Math.max(pre[1], pre[0] - prices[i]);
            dp[2] = Math.max(pre[2], pre[1] + prices[i]);
            dp[3] = Math.max(pre[3], pre[2] - prices[i]);
            dp[4] = Math.max(pre[4], pre[3] + prices[i]);
        }
        return dp[4];
    }
}