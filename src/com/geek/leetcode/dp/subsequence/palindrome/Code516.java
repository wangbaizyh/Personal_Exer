package com.geek.leetcode.dp.subsequence.palindrome;

/**
 * @author G.E.E.K.
 * @create 2022-05-28 21:50
 * 516. 最长回文子序列
 * https://leetcode.cn/problems/longest-palindromic-subsequence/
 *
 * 思路：回文子序列、不连续
 *
 */
public class Code516 {

}

class Solution516 {
    public int longestPalindromeSubseq(String s) {
        // 状态：
        // dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]。
        int[][] dp = new int[s.length()][s.length()];
        // 初始化：
        // i和j相同的情况
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 1;
        }

        // 遍历顺序：从下到上，从左到右
        for (int i = s.length() - 1; i >= 0 ; i--) {    // 遍历顺序
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}