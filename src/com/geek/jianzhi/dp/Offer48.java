package com.geek.jianzhi.dp;

import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 14:09
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 * https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 *
 * 思路：动态规划 + 哈希表
 *
 */
public class Offer48 {

}

// 动态规划 + 哈希表
class Solution48 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        // 各字符最后一次出现的索引位置
        HashMap<Character, Integer> map = new HashMap<>();
        // 状态：
        // dp[j] 以下标j为结尾的字符的最长不重复子字符串的长度
        int[] dp = new int[s.length()];
        // 初始化
        dp[0] = 1;
        map.put(s.charAt(0), 0);
        int res = 1;

        for (int i = 1; i < s.length(); i++) {
            // 获取索引
            int index = map.getOrDefault(s.charAt(i), -1);
            // 更新索引
            map.put(s.charAt(i), i);
            // 状态转移方程：
            if (dp[i - 1] < i - index) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = i - index;
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }
}

// 滑动窗口
// 双指针 + 哈希表
class Solution48_01 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        // 各字符最后一次出现的索引位置
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        int left = -1;

        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                // 更新左指针
                left = Math.max(left, map.get(s.charAt(right)));
            }
            // 更新索引
            map.put(s.charAt(right), right);
            res = Math.max(res, right - left);
        }

        return res;
    }
}