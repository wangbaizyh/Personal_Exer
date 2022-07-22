package com.geek.leetcode.dp.base;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 13:54
 * 509. 斐波那契数
 * https://leetcode.cn/problems/fibonacci-number/
 *
 */
public class Code509 {

}

class Solution509 {
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        int dp[] = new int[2];
        // 初始化：
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int sum = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = sum;
        }

        return dp[1];
    }
}

class Solution509_01 {
    public int fib(int n) {
        int dp[] = new int[n + 1];
        // 状态：
        // dp[i] 第i个数的斐波那契数值是dp[i]
        // 初始化：
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }
}