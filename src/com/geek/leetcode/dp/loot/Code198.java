package com.geek.leetcode.dp.loot;

/**
 * @author G.E.E.K.
 * @create 2022-05-25 11:37
 * 198. 打家劫舍
 * https://leetcode.cn/problems/house-robber/
 *
 * 思路：dp
 *
 */
public class Code198 {

}

class Solution198 {
    public int rob(int[] nums) {
        // 状态：
        // dp[i] 考虑下标i（包括i）以内的房屋，最多可以偷窃的金额为dp[i]。
        // 状态转移方程：
        // dp[i] = max(dp[i - 2] + nums[i], dp[i - 1])
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int[] dp = new int[nums.length];
        // 初始化：
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 遍历顺序
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }
}