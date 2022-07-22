package com.geek.leetcode.dp.stock;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-26 15:40
 * 309. 最佳买卖股票时机含冷冻期
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 *
 * 思路：
 * 1、确定dp数组以及下标的含义
 * dp[i][j]，第i天状态为j，所剩的最多现金为dp[i][j]。
 * 具体可以区分出如下四个状态：
 *
 * 0 状态一：买入股票状态（今天买入股票，或者是之前就买入了股票然后没有操作）
 * 卖出股票状态，这里就有两种卖出股票状态
 *      1 状态二：两天前就卖出了股票，度过了冷冻期，一直没操作，今天保持卖出股票状态
 *      2 状态三：今天卖出了股票
 * 3 状态四：今天为冷冻期状态，但冷冻期状态不可持续，只有一天！
 *
 * 2.确定递推公式
 * 达到买入股票状态（状态一）即：dp[i][0]，有两个具体操作：
 * 操作一：前一天就是持有股票状态（状态一），dp[i][0] = dp[i - 1][0]
 * 操作二：今天买入了，有两种情况
 *      前一天是冷冻期（状态四），dp[i - 1][3] - prices[i]
 *      前一天是保持卖出股票状态（状态二），dp[i - 1][1] - prices[i]
 * 所以操作二取最大值，即：max(dp[i - 1][3], dp[i - 1][1]) - prices[i]
 * 那么dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
 *
 * 达到保持卖出股票状态（状态二）即：dp[i][1]，有两个具体操作：
 * 操作一：前一天就是状态二
 * 操作二：前一天是冷冻期（状态四）
 * dp[i][1] = max(dp[i - 1][1], dp[i - 1][3])
 *
 * 达到今天就卖出股票状态（状态三），即：dp[i][2] ，只有一个操作：
 * 操作一：昨天一定是买入股票状态（状态一），今天卖出
 * dp[i][2] = dp[i - 1][0] + prices[i]
 *
 * 达到冷冻期状态（状态四），即：dp[i][3]，只有一个操作：
 * 操作一：昨天卖出了股票（状态三）
 * dp[i][3] = dp[i - 1][2]
 *
 * 3.dp数组如何初始化
 * 如果是持有股票状态（状态一）那么：dp[0][0] = -prices[0]，买入股票所剩现金为负数。
 * 保持卖出股票状态（状态二），第0天没有卖出dp[0][1]初始化为0就行，
 * 今天卖出了股票（状态三），同样dp[0][2]初始化为0，因为最少收益就是0，绝不会是负数。
 * 同理dp[0][3]也初始为0。
 *
 * 4.确定遍历顺序
 * 从前向后遍历
 *
 * 5.举例推导dp数组
 */
public class Code309 {

}

class Solution309 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        // 状态：
        int[][] dp = new int[n][4];
        // 初始化：
        dp[0][0] = -prices[0];
        // 遍历顺序：
        for (int i = 1; i < n; i++) {
            // 状态转移方程：
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][1], dp[i - 1][3]) - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
        }

        return Math.max(dp[n - 1][1], Math.max(dp[n - 1][2], dp[n - 1][3]));
    }
}

// 状态压缩
class Solution309_01 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int[] dp = new int[4];
        dp[0] = -prices[0];
        int[] pre;

        for (int i = 1; i < n; i++) {
            pre = Arrays.copyOf(dp, 4);

            dp[0] = Math.max(pre[0], Math.max(pre[1], pre[3]) - prices[i]);
            dp[1] = Math.max(pre[1], pre[3]);
            dp[2] = pre[0] + prices[i];
            dp[3] = pre[2];
        }

        return Math.max(dp[1], Math.max(dp[2], dp[3]));
    }
}
