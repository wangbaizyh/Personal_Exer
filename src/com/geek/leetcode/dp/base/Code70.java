package com.geek.leetcode.dp.base;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 14:08
 * 70. 爬楼梯
 * https://leetcode.cn/problems/climbing-stairs/
 *
 */
public class Code70 {

}

class Solution70 {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        // 状态：
        // dp[i] 达到第i个台阶的方法数
        // 初始化：
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 状态转移方程：
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}

// 空间优化
class Solution70_01 {
    public int fib(int n) {
        if (n <= 1) return n;
        int dp[] = new int[2];
        // 初始化：
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }

        return dp[1];
    }
}

// 完全背包问题
class Solution70_02 {
    public int climbStairs(int n, int m) {
        int[] dp = new int[n + 1];
        // 状态：
        // dp[i] 达到第i个台阶的方法数
        // 初始化：
        dp[0] = 1;

        // 遍历顺序
        for (int i = 1; i <= n; i++) {          // 遍历背包
            for (int j = 1; j <= m; j++) {      // 遍历物品
                if (i - j >= 0) {
                    // 状态转移方程：
                    dp[i] += dp[i - j];
                }
            }
        }

        return dp[n];
    }
}