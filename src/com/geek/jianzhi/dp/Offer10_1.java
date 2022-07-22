package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-05-31 14:28
 * 剑指 Offer 10- I. 斐波那契数列
 * https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/
 *
 */
public class Offer10_1 {

}

// 递归超时
class Solution10_1 {
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return  (fib(n - 1) + fib(n - 2)) % 1000000007;
    }
}

// dp
class Solution10_1_01 {
    private final static int mod = 1000000007;

    public int fib(int n) {
        // 临界条件：
        if (n == 0) return 0;
        // 状态：
        // dp[i] 第i个数的斐波那契数值是dp[i]
        int[] dp = new int[n + 1];
        // 初始化：
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 状态转移方程：
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }

        return dp[n];
    }
}