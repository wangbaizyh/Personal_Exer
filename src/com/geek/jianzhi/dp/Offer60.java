package com.geek.jianzhi.dp;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-06-28 14:11
 * 剑指 Offer 60. n个骰子的点数
 * https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/
 *
 * 思路：dp --> 完全背包问题
 *
 */
public class Offer60 {

}

class Solution60_1 {
    public double[] dicesProbability(int n) {
        // 1.状态定义：
        // dp[i][j] 投掷i个骰子点数和为j的次数
        int[][] dp = new int[n + 1][6 * n + 1];
        // 结果概率数组
        double[] res = new double[n * 5 + 1];

        // 2.初始化（一颗骰子的情况）
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }

        // 3.遍历顺序
        // 遍历每个骰子
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= i * 6; j++) {
                // k的范围为1 <= k <= 6 && j - k >= i - 1
                for (int k = 1; k <= 6; k++) {
                    if (j - k < i - 1) break;
                    // 3.状态转移方程：
                    dp[i][j] += dp[i - 1][j - k];
                }
            }
        }

        // 总的次数为6^n
        // 可能出现的点数为5*n+1种
        double all = Math.pow(6, n);

        for (int i = n; i <= 6 * n; i++) {
            // 向左偏移n位输出
            res[i - n] = dp[n][i] / all;
        }

        return res;
    }
}

// 推荐解法
class Solution60_2 {
    public double[] dicesProbability(int n) {
        // 1.状态定义：
        // dp[i][j] 投掷i个骰子点数和为j的概率
        double[][] dp = new double[n + 1][6 * n + 1];
        // 结果概率数组
        double[] res = new double[n * 5 + 1];

        // 2.初始化（一颗骰子的情况）
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1.0 / 6;
        }

        // 3.遍历顺序
        for (int i = 2; i <= n; i++) {              // 遍历骰子
            for (int j = i; j <= i * 6; j++) {      // 遍历骰子的点数和可能
                // k的范围为1 <= k <= 6 && j - k >= i - 1
                for (int k = 1; k <= 6; k++) {      // 遍历当前骰子点数
                    if (j - k < i - 1) break;
                    // 3.状态转移方程：
                    dp[i][j] += dp[i - 1][j - k] / 6;
                }
            }
        }

        for (int i = n; i <= 6 * n; i++) {
            // 向左偏移n位输出
            res[i - n] = dp[n][i];
        }

        return res;
    }
}

// 正推
// 刷表法
class Solution60_3 {
    public double[] dicesProbability(int n) {
        // 1.状态定义：
        // dp[j] 表示n个骰子下每个结果的概率
        // 初始是1个骰子情况下的点数之和情况，就只有6个结果，所以用dp的初始化的size是6个
        double[] dp = new double[6];
        // 2.初始化（一颗骰子的情况）
        Arrays.fill(dp, 1.0 / 6);

        // 3.遍历顺序
        for (int i = 2; i <= n; i++) {              // 遍历骰子
            // 范围数组（滚动数组）
            double[] tmp = new double[5 * i + 1];
            // 遍历上一层骰子的概率，正推本层骰子的概率
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }

        return dp;
    }
}