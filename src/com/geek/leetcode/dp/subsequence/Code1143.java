package com.geek.leetcode.dp.subsequence;

/**
 * @author G.E.E.K.
 * @create 2022-05-27 20:32
 * 1143. 最长公共子序列
 * https://leetcode.cn/problems/longest-common-subsequence/
 */
public class Code1143 {

}

class Solution1143 {
    public int longestCommonSubsequence(String text1, String text2) {
        // 状态：
        // dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                // 状态转移方程：
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }
}