package com.geek.leetcode.dp.knapsackProblem;

/**
 * @author G.E.E.K.
 * @create 2022-05-23 21:41
 * 279. 完全平方数
 * https://leetcode.cn/problems/perfect-squares/
 *
 */
public class Code279 {

}

// 完全背包问题
class Solution279 {
    public int numSquares(int n) {
        int items = (int) Math.sqrt(n);
        // 状态：
        // dp[j] 整数j的完全平方数的最少数量
        int[] dp = new int[n + 1];
        // 初始化：
        // 和为0的完全平方数的最小数量，dp[0]一定是0。
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 遍历顺序
        for (int i = 1; i <= items; i++) {      // 遍历物品
            for (int j = i * i; j <= n; j++) {  // 遍历背包
                // 状态转移方程：
                // dp[j] = min(dp[j - i * i] + 1, dp[j])
                if (dp[j - i * i] != Integer.MAX_VALUE)
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        return dp[n];
    }
}

class Solution279_01 {
    public int numSquares(int n) {
        // 状态：
        // dp[j] 整数j的完全平方数的最少数量
        int[] dp = new int[n + 1];
        // 初始化：
        // 和为0的完全平方数的最小数量，dp[0]一定是0。
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        // 遍历顺序
        for (int i = 1; i * i <= n; i++) {      // 遍历物品
            for (int j = i * i; j <= n; j++) {  // 遍历背包
                // 状态转移方程：
                // dp[j] = min(dp[j - i * i] + 1, dp[j])
                if (dp[j - i * i] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }

        return dp[n];
    }
}