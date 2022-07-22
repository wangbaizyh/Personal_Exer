package com.geek.leetcode.dp.knapsackProblem;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-20 15:48
 * 416. 分割等和子集
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 *
 * 思路：元素和为 sum / 2
 *
 * 背包的体积为sum / 2
 * 背包要放入的商品（集合里的元素）重量为 元素的数值，价值也为元素的数值
 * 背包如果正好装满，说明找到了总和为 sum / 2 的子集。
 * 背包中每一个元素是不可重复放入。
 *
 */
public class Code416 {

}

// 求背包是否正好装满
class Solution416 {
    public boolean canPartition(int[] nums) {
        // 状态：
        // dp[j]表示 背包总容量是j，最大可以凑成j的子集总和为dp[j]。
        // 递推公式：
        // dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);

        // 初始化：
        // 题目中说：每个数组中的元素不会超过 100，数组的大小不会超过 20
        // 总和不会大于20000，背包最大只需要其中一半，所以10001大小就可以了
        // int[] dp = new int[10001];
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        int[] dp = new int[target + 1];

        // 01背包
        for (int i = 0; i < nums.length; i++) {          // 遍历物品
            for (int j = target; j >= nums[i]; j--) {    // 每一个元素一定是不可重复放入，所以从大到小遍历
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        // 集合中的元素正好可以凑成总和target
        return dp[target] == target;
    }
}

// 二维dp
class Solution416_01 {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if (sum % 2 == 1) return false;
        int target = sum / 2;
        int[][] dp = new int[nums.length][target + 1];
        for (int j = nums[0]; j <= target; j++) {
            dp[0][j] = nums[0];
        }

        // 01背包
        for (int i = 1; i < nums.length; i++) {    // 遍历物品
            for (int j = 1; j <= target; j++) {    // 每一个元素一定是不可重复放入，所以从大到小遍历
                if (j >= nums[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i]] + nums[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // 集合中的元素正好可以凑成总和target
        return dp[nums.length - 1][target] == target;
    }
}