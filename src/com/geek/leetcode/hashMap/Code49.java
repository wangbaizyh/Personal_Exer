package com.geek.leetcode.hashMap;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 19:47
 * 49. 字母异位词分组
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * 哈希表
 *
 */
public class Code49 {

}


// 方法一：排序
/*
由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
故可以将排序之后的字符串作为哈希表的键。
key: 排序后的字符串 value: 排序前的字符串
时间复杂度：nk*log k
 */
class Solution49_01 {
    public List<List<String>> groupAnagrams(String[] strs) {
        // HashMap  key:排序后单词 values:原始单词序列
        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            // 对单词进行排序
            Arrays.sort(chars);
            String key = new String(chars);
            // 从map中取出排序单词对应的List，不存在则返回一个新的ArrayList对象
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            // 添加List
            list.add(str);
            // 加入map
            map.put(key,list);
        }

        // 返回map值的List
        return new ArrayList<>(map.values());
    }
}

// 方法二：计数
/*
使用数组记录记录每个字符出现的次数
字符+数组出现的次数作为HashMap的键
 */
class Solution49_02 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 存储每个字符出现的次数
            int[] counts = new int[26];
            for (char c : str.toCharArray()) {
                counts[c - 'a']++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0)  {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }

            String key = sb.toString();
            // 添加键对应的字符串列表（新建）
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }
}

// Java中数组是引用地址，不是真实的地址，应该避免用数组作为键
class Solution49_03 {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<int[], List<String>> map = new HashMap<>();
        for (String str : strs) {
            // 存储每个字符出现的次数
            int[] counts = new int[26];
            for (char c : str.toCharArray()) {
                counts[c - 'a']++;
            }

            // 添加键对应的字符串列表（新建）
            List<String> list = map.getOrDefault(counts, new ArrayList<>());
            list.add(str);
            map.put(counts, list);
        }

        return new ArrayList<>(map.values());
    }
}

