package com.geek.jianzhi.hash;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 20:59
 * 剑指 Offer 50. 第一个只出现一次的字符
 * https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
 *
 * 思路：
 *
 */
public class Offer50 {

}

class Solution50 {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, false);
            } else {
                map.put(c, true);
            }
        }

        for (char c : chars) {
            if (map.get(c)) return c;
        }

        return ' ';
    }
}

// 有序哈希表
// 默认维护插入顺序 不删除元素
class Solution50_01 {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> map = new LinkedHashMap<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, false);
            } else {
                map.put(c, true);
            }
        }
        // 遍历字典
        for (Map.Entry<Character, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) return entry.getKey();
        }

        return ' ';
    }
}
