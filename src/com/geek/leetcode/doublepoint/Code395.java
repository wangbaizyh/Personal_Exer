package com.geek.leetcode.doublepoint;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-07-17 22:31
 * 395. 至少有 K 个重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-with-at-least-k-repeating-characters/description/%20/
 *
 * 思路：
 *
 */
public class Code395 {

}

// 分治思想
class Solution395 {
    public int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;
        // 记录字符的个数
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        // 遍历所有的字符
        for (char key: map.keySet()) {
            // 当有字符个数小于k的时候，不满足条件
            // 划分为子问题，求不含该字符的子串的最长子串
            if (map.get(key) < k) {
                int result = 0;
                // 划分子串
                for (String str : s.split(String.valueOf(key))) {
                    int subLen = longestSubstring(str, k);
                    result = Math.max(result, subLen);
                }
                // 返回子串的最长值
                return result;
            }
        }

        return s.length();
    }
}

/**
 * 枚举 + 双指针
 * 滑动窗口
 * 「当确定了窗口内所包含的字符数量时，区间重新具有了二段性质」。
 * 这是本题的滑动窗口解法和迄今为止做的滑动窗口题目的最大不同，本题需要手动增加限制，即限制窗口内字符种类。
 *
 */
class Solution395_1 {
    public int longestSubstring(String s, int k) {
        int ans = 0;
        // 记录字符的个数
        int[] count = new int[26];

        for (int curKind = 1; curKind < 26; curKind++) {
            // 对于限定的字符数量的条件下进行滑动窗口
            Arrays.fill(count, 0);
            // 左指针
            int left = 0;
            // totalKind:窗口内所有字符类型数量，sumKind:窗口内满足出现次数不少于k的字符类型数量
            int totalKind = 0, sumKind = 0;
            // 右指针
            for (int right = 0; right < s.length(); right++) {
                // 右指针扩展
                int rightIndex = s.charAt(right) - 'a';
                count[rightIndex]++;
                // 增加字符种类
                if (count[rightIndex] == 1) totalKind++;
                // 若满足k个，则增加符合条件类型个数
                if (count[rightIndex] == k) sumKind++;
                // 当总字符种类数量不满足限定的字符种类数量，需要被迫移动左指针来减少总字符种类数量
                while (totalKind > curKind) {
                    // 左指针不断收缩
                    int leftIndex = s.charAt(left) - 'a';
                    // 减少种类数
                    if (count[leftIndex] == 1) totalKind--;
                    // 减少符合条件类型个数
                    if (count[leftIndex] == k) sumKind--;
                    count[leftIndex]--;
                    left++;
                }
                // 当窗口内的所有种类字符都符合条件的时候，计数窗口大小
                if (totalKind == sumKind) {
                    ans = Math.max(ans, right - left + 1);
                }
            }
        }

        return ans;
    }
}