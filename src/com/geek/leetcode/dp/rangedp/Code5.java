package com.geek.leetcode.dp.rangedp;

/**
 * @author G.E.E.K.
 * @create 2022-05-28 21:56
 * 5. 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/
 *
 */
public class Code5 {

}

// 双指针(中心扩散法)

/**
 * 「中心扩散法」基本思想：遍历每个下标，以这个下标为中心，利用「回文串」中心对称的特点，往两边扩散，看最多能扩散多远。
 *  枚举「中心位置」时间复杂度为 O(N)，从「中心位置」扩散得到「回文子串」的时间复杂度为 O(N)
 *  时间复杂度可以降到 O(N^2)
 */
class Solution5 {
    public String longestPalindrome(String s) {
        int result = 0;
        int[] index = new int[2];

        // 遍历字符串，中心扩散，寻找回文串
        for (int i = 0; i < s.length(); i++) {
            // 以i为中心扩散（奇数）
            int[] res1 = extend(s, i, i, s.length());
            int len1 = res1[1] - res1[0];
            // 以i和i+1为中心扩散（偶数）
            int[] res2 = extend(s, i, i + 1, s.length());
            int len2 = res2[1] - res2[0];
            // 更新最长回文串长度和下标
            if (len1 > len2 && len1 > result) {
                result = len1;
                index = res1;
            } else if (len2 > len1 && len2 > result){
                result = len2;
                index = res2;
            }
        }

        return s.substring(index[0], index[1] + 1);
    }

    // 计算子回文串的长度
    // 通过传入两个下标，可以实现中心点奇数个扩散和中心点偶数个扩散的回文串情况
    private int[] extend(String s, int i, int j, int n) {
        while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        // 返回回文串区间
        // 因为最后一次扩散导致不符合循环条件，退出循环，因此回退上次区间
        return new int[]{i + 1, j - 1};
    }
}

// 区间dp
// 区间dp都是从后往前遍历区间，保证区间都被访问过
class Solution5_01 {
    public String longestPalindrome(String s) {
        // 状态：
        // dp[i][j]：表示区间范围[i,j] （注意是左闭右闭）的子串是否是回文子串，如果是dp[i][j]为true，否则为false。
        boolean[][] dp = new boolean[s.length()][s.length()];
        int result = 0;
        int[] index = new int[2];
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

        // 遍历顺序：
        // 区间从后往前遍历
        for (int i = s.length() - 1; i >= 0 ; i--) {
            // 遍历区间
            for (int j = i; j < s.length(); j++) {
                // 状态转移方程：
                // i到j区间是回文： s[i] == s[j] &&（单个/两个字符 || i+1到j-1区间是回文
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    // 回文长度大于最长回文长度
                    if (j - i > result) {
                        // 更新长度和下标
                        result = j - i;
                        index[0] = i;
                        index[1] = j;
                    }

                    dp[i][j] = true;
                }
            }
        }

        return s.substring(index[0], index[1] + 1);
    }
}