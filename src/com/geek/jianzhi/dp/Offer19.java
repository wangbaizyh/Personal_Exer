package com.geek.jianzhi.dp;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-06-10 19:19
 * 剑指 Offer 19. 正则表达式匹配
 * https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/
 *
 * 思路：dp （字符串匹配）
 * 动态规划解析：
 * 状态定义： 设动态规划矩阵 dp ， dp[i][j] 代表字符串 s 的前 i 个字符和 p 的前 j 个字符能否匹配。
 * 转移方程： 需要注意，由于 dp[0][0] 代表的是空字符的状态， 因此 dp[i][j] 对应的添加字符是 s[i - 1] 和 p[j - 1] 。
 * 当 p[j - 1] = '*' 时， dp[i][j] 在当以下任一情况为 true 时等于 true ：
 *      dp[i][j - 2]： 即将字符组合 p[j - 2] * 看作出现 0 次时，能否匹配；
 *      dp[i - 1][j] 且 s[i - 1] = p[j - 2]: 即让字符 p[j - 2] 多出现 1 次时，能否匹配；
 *      dp[i - 1][j] 且 p[j - 2] = '.': 即让字符 '.' 多出现 1 次时，能否匹配；
 * 当 p[j - 1] != '*' 时， dp[i][j] 在当以下任一情况为 true 时等于 true ：
 *      dp[i - 1][j - 1] 且 s[i - 1] = p[j - 1]： 即让字符 p[j - 1] 多出现一次时，能否匹配；
 *      dp[i - 1][j - 1] 且 p[j - 1] = '.'： 即将字符 . 看作字符 s[i - 1] 时，能否匹配；
 *
 * 初始化： 需要先初始化 dp 矩阵首行，以避免状态转移时索引越界。
 *      dp[0][0] = true： 代表两个空字符串能够匹配。
 *      dp[0][j] = dp[0][j - 2] 且 p[j - 1] = '*'： 首行 s 为空字符串，因此当 p 的偶数位为 * 时才能够匹配（即让 p 的奇数位出现 0 次，保持 p 是空字符串）。因此，循环遍历字符串 p ，步长为 2（即只看偶数位）。
 *
 */
public class Offer19 {
    @Test
    public void test() {
        boolean match = new Solution19().isMatch("aa", "a*");
        System.out.println(match);
    }
}

class Solution19 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // 状态：
        // dp[i][j] 代表字符串 s 的前 i 个字符和 p 的前 j 个字符能否匹配。
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 初始化
        dp[0][0] = true;
        // 首行初始化(偶数位为 *)
        for (int i = 2; i <= n; i += 2) {
            dp[0][i] = dp[0][i - 2] && p.charAt(i - 1) == '*';
        }

        // 遍历顺序
        for (int i = 1; i <= m; i++) {           // 遍历背包
            for (int j = 1; j <= n; j++) {       // 遍历物品
                // 情况一
                // p[j - 1]p[j - 2]出现0次数；s[i - 1] == p[j - 2](出现一次)；p[j - 2] == '.' 任意匹配s[i - 1]
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.');
                } else {
                    // 情况二
                    // s[i - 1] == p[j - 1]; p[j - 1] == '.' 任意匹配s[i - 1]
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }

        return dp[m][n];
    }
}