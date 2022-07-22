package com.geek.leetcode.ranklist.weak298;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-06-19 11:34
 * 6099. 小于等于 K 的最长二进制子序列
 * https://leetcode.cn/contest/weekly-contest-298/problems/longest-binary-subsequence-less-than-or-equal-to-k/
 *
 * 思路：贪心
 */
public class Code2312 {
    @Test
    public void test() {
        int ans = new Solution2312().longestSubsequence("1001010", 5);
        System.out.println(ans);
    }

}

/**
 * 贪心
 *
 * 构造最长子序列
 *
 * 首先，最长子序列包含原二进制字符串中的所有的0，
 * 然后，再从 右(低位)向左(高位) 添加 1，
 * 使得 子序列 对应的 二进制 数字小于等于 k
 */
class Solution2312_01 {
    public int longestSubsequence(String s, int k) {
        int n = s.length();

        int res = 0;

        // 计算0的个数
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                res++;
            }
        }

        int num = 0;
        // 低位向高位添加1
        // 注意int的上界
        for (int i = n - 1; i >= Math.max(0, n - 31); i--) {
            if (s.charAt(i) == '1') {
                if (num + (1 << (n - 1 - i)) > k)  break;
                res++;
                num += 1 << (n - 1 - i);
            }
        }

        return res;
    }
}


// dp有问题 递归条件不成立
class Solution2312 {
    public int longestSubsequence(String s, int k) {
        // 状态定义：
        // dp[i][0]: 以i为结尾的当前十进制数字
        // dp[i][1]: 以i为结尾的当前数字个数
        int[][] dp = new int[s.length()][2];
        // 初始化
        for (int i = 0; i < s.length(); i++) {
            dp[i][0] = s.charAt(i) - '0';
            dp[i][1] = 1;
        }
        // 结果长度
        int result = 0;

        for (int i = 1; i < dp.length; i++) {           // 从第一个数字开始
            for (int j = 0; j < i; j++) {
                if ((dp[j][0] << 1) + s.charAt(i) - '0' <= k) {
                    if (dp[j][1] + 1 > dp[i][1]) {
                        dp[i][1] = dp[j][1] + 1;
                        dp[i][0] = (dp[j][0] << 1) + s.charAt(i) - '0';
                    }
                }

                if (dp[i][1] > result) result = dp[i][1];
            }
        }

        return result;
    }
}
