package com.geek.leetcode.dp.subsequence;

/**
 * @author G.E.E.K.
 * @create 2022-05-28 14:12
 * 115. 不同的子序列
 * https://leetcode.cn/problems/distinct-subsequences/
 *
 */
public class Code115 {

}

class Solution115 {
    public int numDistinct(String s, String t) {
        // 状态
        // dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        // 初始化：
        // 那么dp[i][0]一定都是1，因为也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1。
        for (int i = 0; i <= s.length(); i++) dp[i][0] = 1;
        // dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数。
        // 那么dp[0][j]一定都是0，s如论如何也变成不了t。
        for (int j = 1; j <= t.length(); j++) dp[0][j] = 0;

        // 遍历顺序：
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[s.length()][t.length()];
    }
}