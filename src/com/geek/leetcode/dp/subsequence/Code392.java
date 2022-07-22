package com.geek.leetcode.dp.subsequence;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-05-28 13:34
 * 392. 判断子序列
 * https://leetcode.cn/problems/is-subsequence/
 *
 */
public class Code392 {
    @Test
    public void test() {
        boolean ans = new Solution392().isSubsequence("axc", "ahbgdc");
        System.out.println(ans);
    }
}

// 双指针
class Solution392 {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;
        int i = 0;

        for (int j = 0; j < t.length(); j++) {
            if (i == s.length()) return true;

            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }

        return i == s.length();
    }
}

// dp
class Solution392_01 {
    public boolean isSubsequence(String s, String t) {
        // 状态：
        // dp[i][j] 长度为[0, i - 1]的字符串s与长度为[0, j - 1]的字符串t的最长公共子序列为dp[i][j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        return dp[s.length()][t.length()] == s.length();
    }
}