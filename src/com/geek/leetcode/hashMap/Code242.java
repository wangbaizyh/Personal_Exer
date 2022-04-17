package com.geek.leetcode.hashMap;

import java.util.HashSet;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 19:19
 * 242. 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * 思路：数组是一个简单的哈希表
 *      - 字符映射到数组索引
 *
 */
public class Code242 {

}

class Solution242 {
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        for (char c: s.toCharArray()) {
            record[c - 'a'] += 1;
        }

        for (char c: t.toCharArray()) {
            record[c - 'a'] -= 1;
        }

        for (int i : record) {
            if (i != 0) {
                return false;
            }
        }

        return true;
    }
}