package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 13:38
 * 剑指 Offer 47. 礼物的最大价值
 * https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/
 *
 * 思路：dp
 *
 */
public class Offer47 {

}

class Solution47 {
    public int maxValue(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // 状态：
        // dp[i][j] 在位置i,j的最大价值
        int[][] dp = new int[n][m];
        // 初始化
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }

        // 遍历顺序
        for (int i = 1; i < n; i++) {           // 遍历行
            for (int j = 0; j < m; j++) {       // 遍历列
                if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else {
                    dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}