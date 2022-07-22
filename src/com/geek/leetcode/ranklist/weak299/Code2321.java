package com.geek.leetcode.ranklist.weak299;

/**
 * @author G.E.E.K.
 * @create 2022-06-26 11:32
 * 2321. 拼接数组的最大分数
 * https://leetcode.cn/problems/maximum-score-of-spliced-array/
 *
 * 思路：转换成最大子数组和
 * 可以参考 Code53
 *
 */
public class Code2321 {

}

class Solution2321 {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(maxScore(nums1, nums2), maxScore(nums2, nums1));
    }

    // 滚动数组
    private int maxScore(int[] nums1, int[] nums2) {
        int total = 0, maxSum = 0, diff = 0;
        for (int i = 0; i <nums1.length; i++) {
            // 求和
            total += nums1[i];
            // 求差值数组的最大子数组和
            diff = Math.max(diff + nums2[i] - nums1[i], Math.max(nums2[i] - nums1[i], 0));
            // 取最大子数组和
            maxSum = Math.max(maxSum, diff);
        }

        return total + maxSum;
    }
}

// dp解法
class Solution2321_1 {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        return Math.max(maxScore(nums1, nums2), maxScore(nums2, nums1));
    }

    private int maxScore(int[] nums1, int[] nums2) {
        // 差值数组
        int[] diff = new int[nums1.length];
        // 总和
        int total = 0;
        for (int i = 0; i < nums1.length; i++) {
            diff[i] = nums2[i] - nums1[i];
            total += nums1[i];
        }

        // 状态定义：
        // dp[i] 表示包括i之前的最大连续子序列和
        int[] dp = new int[nums1.length];
        // 初始化：
        dp[0] = Math.max(diff[0], 0);
        int maxSum = 0;
        for (int i = 1; i < nums1.length; i++) {
            // 状态转移：
            dp[i] = Math.max(dp[i - 1] + diff[i], Math.max(diff[i], 0));
            maxSum = Math.max(maxSum, dp[i]);
        }

        return total + maxSum;
    }
}