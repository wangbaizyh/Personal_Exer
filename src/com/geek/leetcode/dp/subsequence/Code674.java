package com.geek.leetcode.dp.subsequence;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-27 19:45
 * 674. 最长连续递增序列
 * https://leetcode.cn/problems/longest-continuous-increasing-subsequence/
 *
 */
public class Code674 {

}

// 贪心算法
class Solution674 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int len = 1;
        int result = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                len++;
            } else {
                len = 1;
            }

            result = Math.max(result, len);
        }

        return result;
    }
}

class Solution674_01 {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length <= 1) return nums.length;
        // 状态：
        // dp[i]：以下标i为结尾的数组的连续递增的子序列长度为dp[i]。
        int[] dp = new int[nums.length];
        // 初始化：
        Arrays.fill(dp, 1);
        int result = 1;

        // 遍历顺序
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }

            if (dp[i] > result) result = dp[i];
        }

        return result;
    }
}