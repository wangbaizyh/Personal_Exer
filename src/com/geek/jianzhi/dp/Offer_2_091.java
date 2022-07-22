package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-06-30 14:35
 * 剑指 Offer II 091. 粉刷房子
 * https://leetcode.cn/problems/JEj789/
 *
 * 思路：状态机dp
 * 某些状态只能由规则限定的状态所转移
 *
 */
public class Offer_2_091 {

}

// dp
class Solution_2_091 {
    public int minCost(int[][] costs) {
        // 状态定义：
        // dp[i][j] 考虑下标 i 以内的房子，且最后一间房子颜色为 j 时的最小成本。
        int[][] dp = new int[costs.length][3];
        // 初始化：
        for (int i = 0; i < 3; i++) {
            dp[0][i] = costs[0][i];
        }

        // 遍历顺序
        for (int i = 1; i < costs.length; i++) {
            // 递推公式：
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }

        return Math.min(dp[costs.length - 1][0], Math.min(dp[costs.length - 1][1], dp[costs.length - 1][2]));
    }
}