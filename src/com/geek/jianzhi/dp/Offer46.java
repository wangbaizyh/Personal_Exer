package com.geek.jianzhi.dp;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 12:59
 * 剑指 Offer 46. 把数字翻译成字符串
 * https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 *
 * 思路：dp
 */
public class Offer46 {

}

class Solution46 {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        // 特例
        if (len == 1) return 1;
        // 状态定义：
        // dp[i] 代表以i为结尾的数字的翻译方案数量。
        int[] dp = new int[len];
        // 初始化
        dp[0] = 1;
        String tmp = s.substring(0, 2);
        // 字符串比较
        dp[1] = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? 2 : 1;

        // 遍历顺序
        for (int i = 2; i < len; i++) {
            tmp = s.substring(i - 1, i + 1);
            // 状态转移方程
            if (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[len - 1];
    }
}

class Solution46_1 {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int len = s.length();
        // 特例
        if (len == 1) return 1;
        // 状态定义：
        // dp[i] 代表前i个数字的翻译方案数量。
        int[] dp = new int[len + 1];
        // 初始化
        dp[1] = 1;
        String tmp = s.substring(0, 2);
        // 字符串比较
        dp[2] = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? 2 : 1;

        // 遍历顺序
        for (int i = 3; i <= len; i++) {
            tmp = s.substring(i - 2, i);
            // 状态转移方程
            if (tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[len];
    }
}