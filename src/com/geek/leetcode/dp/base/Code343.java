package com.geek.leetcode.dp.base;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 17:44
 * 343. 整数拆分
 * https://leetcode.cn/problems/integer-break/
 *
 */
public class Code343 {

}


// dp
class Solution343 {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // 状态：
        // dp[i] 为正整数 i 拆分后的结果的最大乘积
        // 初始化：
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i - 1; j++) {
                // 状态转移方程：
                // 子任务拆分：拆分成两个相乘，拆分成多个相乘
                // i - j 最小为2， 因此 j < i - 1
                dp[i] = Math.max(dp[i], Math.max((i - j) * j, dp[i - j] * j));
                // j * (i - j) 是单纯的把整数 i 拆分为两个数 也就是 i,i-j ，再相乘
                //而j * dp[i - j]是将 i 拆分成两个以及两个以上的个数,再相乘。
            }
        }
        return dp[n];
    }
}

// 贪心算法
class Solution343_01 {
    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;

        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }

        result *= n;
        return result;
    }
}