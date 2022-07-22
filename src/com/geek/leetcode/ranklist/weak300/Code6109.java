package com.geek.leetcode.ranklist.weak300;

/**
 * @author G.E.E.K.
 * @create 2022-07-03 11:30
 * 6109. 知道秘密的人数
 * https://leetcode.cn/problems/number-of-people-aware-of-a-secret/submissions/
 *
 * 思路：正向DP
 *
 */
public class Code6109 {

}

// 刷表
// 正向递推
class Solution6109 {
    private static final int MOD = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 状态定义：
        // dp[i][0] 第i天 知道秘密，不能分享秘密的人数
        // dp[i][1] 第i天 知道秘密，可以分享秘密的人数
        int[][] dp = new int[n + 1][2];
        // 初始化状态：
        // 第1天知道秘密可以分享的人数
        dp[1][1] = 1;

        // 正推第i天对之后的贡献
        // 遍历顺序
        for (int i = 1; i < n + 1; i++) {
            // 这天刚知道，不能分享，因而j从i开始
            // 第j天不能分享秘密的人数，处于禁言期
            for (int j = i; j < Math.min(i + delay, n + 1); j++) {
                dp[j][0] = (dp[j][0] + dp[i][1]) % MOD;
            }
            // 第j天可以分享秘密的人数，处于分享期
            for (int j = i + delay; j < Math.min(i + forget, n + 1); j++) {
                dp[j][1] = (dp[j][1] + dp[i][1]) % MOD;
            }
        }

        return (dp[n][0] + dp[n][1]) % MOD;
    }
}

// 正推
// 状态定义：第i天 知道秘密，可以分享秘密的人数
class Solution6109_1 {
    private static final int MOD = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 状态定义：
        // dp[i] 第i天 知道秘密，可以分享秘密的人数
        int[] dp = new int[n + 1];
        // 初始化状态：
        // 第1天知道秘密可以分享的人数
        dp[1] = 1;
        // 知道秘密, 不能分享的人数
        int silent = 0;

        // 遍历顺序
        for (int i = 1; i < n + 1; i++) {
            // 第n天不能分享秘密的人数，处于禁言期
            if (i + delay > n) {
                silent = (silent + dp[i]) % MOD;
            }
            // 第i天可以分享秘密的人数，处于分享期
            for (int j = i + delay; j < Math.min(i + forget, n + 1); j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }

        return (silent + dp[n]) % MOD;
    }
}

// 正推
// 状态定义：dp[i] 第i天新增知道秘密的人数（刚知道秘密）
class Solution6109_2 {
    private static final int MOD = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        // 状态定义：
        // dp[i] 第i天新增的知道秘密的人数（刚知道秘密）
        int[] dp = new int[n + 1];
        // 初始化：
        dp[1] = 1;

        // 遍历顺序
        // dp[i]能影响到dp[i + delay]到dp[i + forget]的人数
        for (int i = 1; i < n; i++) {
            // 状态转移方程：
            for (int j = i + delay; j < Math.min(i + forget, n + 1); j++) {
                dp[j] = (dp[j] + dp[i]) % MOD;
            }
        }

        // 计算在第n天时还知道秘密的人数
        int res = 0;
        for (int i = 1; i < n + 1; i++) {
            // 第n天还记得秘密，还没有忘
            if (i + forget > n) {
                res = (res + dp[i]) % MOD;
            }
        }

        return res;
    }
}