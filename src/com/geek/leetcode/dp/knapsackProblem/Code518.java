package com.geek.leetcode.dp.knapsackProblem;

/**
 * @author G.E.E.K.
 * @create 2022-05-23 16:38
 * 518. 零钱兑换 II
 * https://leetcode.cn/problems/coin-change-2/
 *
 * 思路：回溯、dp(完全背包)
 *
 * 总结：
 * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
 * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
 *
 */
public class Code518 {

}

class Solution518 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        // 状态：
        // dp[j] 凑成总金额j的货币组合数为dp[j]
        // 初始化：
        // 凑成总金额0的货币组合数为1
        dp[0] = 1;

        // 遍历顺序
        for (int i = 0; i < coins.length; i++) {            // 遍历物品
            for (int j = coins[i]; j <= amount; j++) {      // 遍历背包
                // 状态转移方程：
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }
}