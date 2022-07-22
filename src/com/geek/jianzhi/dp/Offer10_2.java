package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-05-31 14:44
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 * https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 *
 */
public class Offer10_2 {

}

// dp
class Solution10_2 {
    private final static int mod = 1000000007;

    public int numWays(int n) {
        // 临界条件
        if (n <= 1) return 1;
        // 状态：
        // dp[i] 跳上第i级台阶的跳法
        int[] dp = new int[n + 1];
        // 初始化：
        dp[1] = 1;
        dp[2] = 2;
        // 遍历顺序
        for (int i = 3; i <= n; i++) {
            // 状态转移方程：
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }

        return dp[n];
    }
}