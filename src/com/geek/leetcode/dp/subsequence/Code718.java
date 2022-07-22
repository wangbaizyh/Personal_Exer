package com.geek.leetcode.dp.subsequence;

/**
 * @author G.E.E.K.
 * @create 2022-05-27 20:06
 * 718. 最长重复子数组
 * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
 *
 */
public class Code718 {

}

class Solution718 {
    public int findLength(int[] nums1, int[] nums2) {
        // 状态：
        // dp[i][j]：以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度为dp[i][j]
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int result = 0;

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                // 状态转移方程：
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                if (dp[i][j] > result) result = dp[i][j];
            }
        }

        return result;
    }
}

// 滚动数组
class Solution718_01 {
    public int findLength(int[] nums1, int[] nums2) {
        // 状态：
        // dp[i][j]：以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度为dp[i][j]
        int[] dp = new int[nums2.length + 1];
        int result = 0;

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                // 状态转移方程：
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else dp[j] = 0;

                if (dp[j] > result) result = dp[j];
            }
        }

        return result;
    }
}