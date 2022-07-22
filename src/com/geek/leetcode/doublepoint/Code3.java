package com.geek.leetcode.doublepoint;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 20:11
 * 3. 无重复字符的最长子串
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 *
 * 思路：字节考题！！！代码不熟悉！！
 * 主要思路是滑动窗口，通过左右指针移动，使得窗口内的字符没有重复字符
 * 出现重复字符则把队列左边的元素移出，一直维持这样的队列
 * 找出队列出现最长长度的时候，就求出了题解。
 *
 */
public class Code3 {

    @Test
    public void test() {
        int ans = new Solution3_1().lengthOfLongestSubstring("abccc");
        System.out.println(ans);
    }

}

/**
 * 思路：字节考题！！！代码不熟悉！！
 * 主要思路是滑动窗口，通过左右指针移动，使得窗口内的字符没有重复字符
 * 出现重复字符则把队列左边的元素移出，一直维持这样的队列
 * 找出队列出现最长长度的时候，就求出了题解。
 */
// 正解
// 双指针 + 哈希表(存储字符最后一次出现的下标)
class Solution3 {
    public int lengthOfLongestSubstring(String s) {
        // 长度为0的情况
        if (s.length() == 0) return 0;
        // 各个字符最后一次出现的索引位置
        HashMap<Character, Integer> map = new HashMap<>();
        // 最长子串长度
        int res = 0;
        // 左指针位置为-1，因为之前没有重复的字符(存储上一次重复字符的位置)
        int left = -1;

        // 右指针滑动
        for (int right = 0; right < s.length(); right++) {
            // 出现过该字符
            if (map.containsKey(s.charAt(right))) {
                // 更新左指针位置为重复元素的上一次位置（移出队列头部的重复元素）
                left = Math.max(left, map.get(s.charAt(right)));
            }
            // 更新字符最后一次出现位置
            map.put(s.charAt(right), right);
            // 计算最长子串的长度（求队列长度）
            res = Math.max(res, right - left);
        }

        return res;
    }
}

// 双指针 + 哈希表（存储字符是否出现）
class Solution3_1 {
    public int lengthOfLongestSubstring(String s) {
        // 字符是否出现
        HashSet<Character> set = new HashSet<>();
        // 最长子串长度
        int res = 0;
        // 初始位置
        int left = 0;

        // 右指针滑动
        for (int right = 0; right < s.length(); right++) {
            // 出现过该字符
            while (set.contains(s.charAt(right))) {
                // 移动左指针到重复字符+1位置
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));
            // 计算最长子串的长度
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}

/**
 * 延申：如果允许可以有一对重复的元素呢？
 */
class Solution3_2 {
    public int lengthOfLongestSubstring(String s) {
        // 字符出现的次数(最多两次)
        HashMap<Character, Integer> map = new HashMap<>();
        // 最长子串长度
        int res = 0;
        // 初始位置
        int left = 0;

        // 右指针滑动
        for (int right = 0; right < s.length(); right++) {
            // 放入map
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) + 1);
            // 已经有两个重复
            // 移动左指针到删除一个重复元素
            while (map.get(s.charAt(right)) > 2) {
                // 移除map
                map.put(s.charAt(left), map.get(s.charAt(left)) - 1);
                left++;
            }

            // 计算最长子串的长度（求队列长度）
            res = Math.max(res, right - left + 1);
        }

        return res;
    }
}
