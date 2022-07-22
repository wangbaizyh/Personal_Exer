package com.geek.leetcode.dp.knapsackProblem;

/**
 * @author G.E.E.K.
 * @create 2022-05-20 11:18
 *
 * 01背包问题
 *
 */
public class Code01_package {
    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagSize = 4;
        package_01(weight, value, bagSize);
        package_02(weight, value, bagSize);
        package_03(weight, value, bagSize);
    }

    // 二维dp解决01背包问题
    private static void package_01(int[] weight, int[] value, int bagSize) {
        // 定义dp数组：dp[i][j]表示背包容量为j时，前i个物品能获得的最大价值
        int[][] dp = new int[weight.length][bagSize + 1];
        // 初始化：
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // 遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
            }
        }

        // 打印结果
        System.out.println(dp[weight.length - 1][bagSize]);
    }

    // 状态压缩 一维dp解决01背包问题
    private static void package_02(int[] weight, int[] value, int bagSize) {
        // 滚动数组
        // 需要满足的条件：上一层可以重复利用，直接拷贝到当前层。
        // 定义dp数组：dp[j]表示 容量为j的背包，所背的物品价值可以最大为dp[j]。
        int[] dp = new int[bagSize + 1];

        // 一层一层遍历
        // 遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 0; i < weight.length; i++) {           // 遍历物品
            for (int j = bagSize; j >= weight[i]; j--) {    // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        // 打印结果
        System.out.println(dp[bagSize]);
    }

    private static void package_03(int[] weight, int[] value, int bagSize) {
        int[] dp = new int[bagSize + 1];

        for (int i = 0; i < weight.length; i++) {           // 遍历物品
            for (int j = weight[i]; j <= bagSize; j++) {    // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        // 打印结果
        System.out.println(dp[bagSize]);
    }
}

