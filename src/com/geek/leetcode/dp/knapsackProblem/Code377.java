package com.geek.leetcode.dp.knapsackProblem;

/**
 * @author G.E.E.K.
 * @create 2022-05-23 20:14
 * 377. 组合总和 Ⅳ
 * https://leetcode.cn/problems/combination-sum-iv/
 *
 * 思路：回溯、dp；排列问题
 *
 */
public class Code377 {

}

class Solution377 {
    public int combinationSum4(int[] nums, int target) {
        // 状态：
        // dp[i] 总和为i的元素排列的个数
        int[] dp = new int[target + 1];
        // 初始化：
        // 总和为0的元素组合个数为1，啥也不放
        dp[0] = 1;

        // 遍历顺序
        for (int i = 0; i <= target; i++) {             // 遍历背包
            for (int j = 0; j < nums.length; j++) {     // 遍历物品
                if (i >= nums[j]) dp[i] += dp[i - nums[j]];
            }
        }

        return dp[target];
    }
}