package com.geek.leetcode.dp.subsequence;

/**
 * @author G.E.E.K.
 * @create 2022-05-28 17:21
 * 72. 编辑距离
 * https://leetcode.cn/problems/edit-distance/
 */
public class Code72 {

}

class Solution72 {
    public int minDistance(String word1, String word2) {
        // 状态：
        // dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // 初始化：
        for (int i = 0; i <= word1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= word2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                // 状态转移方程：
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 换 增 删
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}

