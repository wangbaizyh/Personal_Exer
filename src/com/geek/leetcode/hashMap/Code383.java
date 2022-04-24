package com.geek.leetcode.hashMap;

/**
 * @author G.E.E.K.
 * @create 2022-04-23 19:52
 * 383. 赎金信
 * https://leetcode-cn.com/problems/ransom-note/
 *
 * 用数组的形式来实现：hashMap
 * 典型题目：字符形式的题目
 * 空间换时间的哈希策略
 *
 */
public class Code383 {
}


class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 小写字符
        int[] map = new int[26];
        for (int i = 0; i < magazine.length(); i++) {
            map[magazine.charAt(i)  - 'a']++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            if (map[ransomNote.charAt(i) - 'a'] == 0) {
                return false;
            } else {
                map[ransomNote.charAt(i) - 'a']--;
            }
        }

        return true;
    }
}