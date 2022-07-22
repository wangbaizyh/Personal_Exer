package com.geek.leetcode.dp.knapsackProblem;

/**
 * @author G.E.E.K.
 * @create 2022-05-23 21:18
 * 322. 零钱兑换
 * https://leetcode.cn/problems/coin-change/
 *
 * 思路：回溯、dp（完全背包）
 * 求最少的硬币个数，类似于求价值的问题
 *
 */
public class Code322 {

}

class Solution322 {
    public int coinChange(int[] coins, int amount) {
        // 状态：
        // dp[j] 凑足总额为j所需钱币的最少个数为dp[j]
        int[] dp = new int[amount + 1];
        // 初始化：
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        // 递推公式：
        // dp[j] = min(dp[j], dp[j - coins[i] + 1)
        // 遍历顺序
        for (int i = 0; i < coins.length; i++) {            // 遍历物品
            for (int j = coins[i]; j <= amount; j++) {      // 遍历背包
                if (dp[j - coins[i]] != Integer.MAX_VALUE){ // 如果dp[j - coins[i]]是初始值则跳过
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        // 结果处理
        if (dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }
}