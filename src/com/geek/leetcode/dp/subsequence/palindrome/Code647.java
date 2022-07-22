package com.geek.leetcode.dp.subsequence.palindrome;

/**
 * @author G.E.E.K.
 * @create 2022-05-28 21:10
 * 647. 回文子串
 * https://leetcode.cn/problems/palindromic-substrings/
 *
 * 思路：连续
 *
 */
public class Code647 {

}

// dp
class Solution647 {
    public int countSubstrings(String s) {
        // 状态：
        // dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;
        // 确定递推公式
        // 在确定递推公式时，就要分析如下几种情况。
        // 整体上是两种，就是s[i]与s[j]相等，s[i]与s[j]不相等这两种。
        // 当s[i]与s[j]不相等，那没啥好说的了，dp[i][j]一定是false。
        // 当s[i]与s[j]相等时，这就复杂一些了，有如下三种情况
        //       情况一：下标i 与 j相同，同一个字符例如a，当然是回文子串
        //       情况二：下标i 与 j相差为1，例如aa，也是回文子串
        //       情况三：下标：i 与 j相差大于1的时候，例如cabac，
        //       此时s[i]与s[j]已经相同了，
        //       我们看i到j区间是不是回文子串就看aba是不是回文就可以了，
        //       那么aba的区间就是 i+1 与 j-1区间，
        //       这个区间是不是回文就看dp[i + 1][j - 1]是否为true。

        // 遍历顺序：从下到上，从左到右
        for (int i = s.length() - 1; i >= 0 ; i--) {    // 遍历顺序
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i <= 1) {               // 1 2
                        result++;
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]){   // 3
                        result++;
                        dp[i][j] = true;
                    }
                }
            }
        }

        return result;
    }
}

// 双指针(中心扩散法)
class Solution647_01 {
    public int countSubstrings(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            result += extend(s, i, i, s.length());          // 以i为中心
            result += extend(s, i, i + 1, s.length());    // 以i和i+1为中心
        }

        return result;
    }

    // 计算回文子串的个数
    private int extend(String s, int i, int j, int n) {
        int res = 0;
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
            res++;
        }

        return res;
    }
}
