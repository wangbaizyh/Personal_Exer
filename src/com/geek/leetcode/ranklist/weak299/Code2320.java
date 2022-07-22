package com.geek.leetcode.ranklist.weak299;

/**
 * @author G.E.E.K.
 * @create 2022-06-26 11:23
 * 2320. 统计放置房子的方式数
 * https://leetcode.cn/problems/count-number-of-ways-to-place-houses/
 *
 * 思路：dp -- 类似于爬楼梯
 *
 */
public class Code2320 {

}

// dp
class Solution2320 {
    private static final int MOD = (int) 1e9 + 7;

    public int countHousePlacements(int n) {
        // 状态定义：
        // dp[i] 表示前i个地块放房子的方案数
        int[] dp = new int[n + 1];
        dp[0] = 1;      // 空
        dp[1] = 2;      // 放与不放
        for (int i = 2; i <= n; i++) {
            // 转移方程：
            // 不放房子 + 放房子
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        return  (int) ((long) dp[n] * dp[n] % MOD);
    }
}