package com.geek.leetcode.dp.knapsackProblem;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-20 18:17
 * 1049. 最后一块石头的重量 II
 * https://leetcode.cn/problems/last-stone-weight-ii/
 *
 * 思路：分成两堆重量近似相同的石头，类似于分割等和子集的问题，因此可以用dp来解决
 *      - 重量和价值是同一个数组
 *
 *      思想 ： 确定背包的体积、背包放入的物品、物品的价值 抽象出dp问题的本质
 *              确定最终的背包问题的结果处理方式
 *
 */
public class Code1049 {

}

// 求背包最多能装多少
class Solution1049 {
    public int lastStoneWeightII(int[] stones) {
        // 状态：
        // dp[j]表示容量（这里说容量更形象，其实就是重量）为j的背包，最多可以背dp[j]这么重的石头。
        // 递推公式：
        // dp[j] = max(dp[j], dp[j - stones[i]] + stones[i]);
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        int[] dp = new int[target + 1];

        for (int i = 0; i < stones.length; i++) {           // 遍历物品(石子)
            for (int j = target; j >= stones[i]; j--) {     // 遍历背包
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }

        // 相撞剩下的最小石子重量
        return sum - dp[target] - dp[target];
    }
}

// 二维dp
class Solution1049_01 {
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum / 2;
        // 状态：
        // dp[i][j]为可以放0-i物品，背包容量为j的情况下背包中的最大价值。
        int[][] dp = new int[stones.length][target + 1];
        // 递推公式：
        // dp[j] = max(dp[j], dp[j - stones[i]] + stones[i]);
        // 初始化：
        for (int j = stones[0]; j <= target; j++) {
            dp[0][j] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {   // 遍历物品(石子)
            for (int j = 1; j <= target; j++) {     // 遍历背包
                if (j >= stones[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 相撞剩下的最小石子重量
        return sum - 2 * dp[stones.length - 1][target];
    }
}