package com.geek.leetcode.ranklist.weak300;

import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-07-03 11:08
 * 6108. 解密消息
 * https://leetcode.cn/contest/weekly-contest-300/problems/decode-the-message/
 *
 * 思路：模拟
 *
 */
public class Code6108 {
}

class Solution6108 {
    public String decodeMessage(String key, String message) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < key.length(); i++) {
            if (key.charAt(i) != ' ' && !map.containsKey(key.charAt(i))) {
                map.put(key.charAt(i), (char) ('a' + map.size()));
            }
        }

        char[] chars = message.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                chars[i] = map.get(chars[i]);
            }
        }

        return new String(chars);
    }
}