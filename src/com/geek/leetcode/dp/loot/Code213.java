package com.geek.leetcode.dp.loot;

/**
 * @author G.E.E.K.
 * @create 2022-05-25 14:34
 * 213. 打家劫舍 II
 * https://leetcode.cn/problems/house-robber-ii/
 *
 * 思路：考虑环的问题
 *      - 分两种情况：
 *          考虑首元素
 *          考虑尾元素
 *
 */
public class Code213 {

}

class Solution213 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int first = robRange(nums, 0, nums.length - 2);
        int end = robRange(nums, 1, nums.length - 1);
        return Math.max(first, end);
    }

    private int robRange(int[] nums, int start, int end) {
        // 只有一个元素
        if (start == end) return nums[start];
        int[] dp = new int[nums.length];
        // 初始化：
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        // 状态转移方程：
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }
}
