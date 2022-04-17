package com.geek.leetcode.hashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 23:17
 * 438. 找到字符串中所有字母异位词
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 *
 * 哈希表、滑动窗口（双指针）
 *
 */
public class Code438 {
    public static void main(String[] args) {
        List<Integer> list = new Solution438().findAnagrams("abab", "ab");
        System.out.println(list);
    }

}

// 暴力法
class Solution438 {
    public List<Integer> findAnagrams(String s, String p) {
        int length = p.length();
        char[] array = s.toCharArray();
        char[] array1 = p.toCharArray();
        Arrays.sort(array1);
        String target = new String(array1);
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i <= array.length - length; i++) {
            char[] subStr = new char[length];
            // System.arraycopy(array, i, subStr, 0, length);
            for (int j = i; j < i + length; j++) {
                subStr[j - i] = array[j];
            }
            Arrays.sort(subStr);
            String str = new String(subStr);
            if (target.equals(str)) {
                result.add(i);
            }
        }

        return result;
    }
}

// 滑动窗口：方法一 滑动窗口+数组
class Solution438_01 {
    public List<Integer> findAnagrams(String s, String p) {
        // 记录字符串p的单词出现次数
        int[] pRecord = new int[26];
        for (char c : p.toCharArray()) {
            pRecord[c - 'a']++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        int i = 0,j = 0;
        int[] sRecord = new int[26];

        while (j < s.length()){
            sRecord[s.charAt(j) - 'a']++;
            // 慢指针移动
            if (j - i >= p.length()) {
                sRecord[s.charAt(i) - 'a']--;
                i++;
            }

            // 判断数组是否相等
            if (Arrays.equals(sRecord,pRecord)) {
                result.add(i);
            }

            j++;
        }


        return result;
    }
}

// 滑动窗口：方法二  计算不同字符的个数  变长窗口版本。(最优解法)
class Solution438_02 {
    public List<Integer> findAnagrams(String s, String p) {
        // 记录字符串p的单词出现次数
        int[] Record = new int[26];
        for (char c : p.toCharArray()) {
            Record[c - 'a']++;
        }

        ArrayList<Integer> result = new ArrayList<>();
        int i = 0,j = 0;

        while (j < s.length()){
            if (Record[s.charAt(j) - 'a'] > 0){
                // 遍历字符串s, 产生消耗 (技巧：当消费-生产=最初生产时 数组为空，也就是所要求的状态)
                Record[s.charAt(j) - 'a']--;
                j++;

                // 当长度为p的长度时，加入列表
                if (j - i == p.length()) {
                    result.add(i);
                }
            } else {
                // 生产
                Record[s.charAt(i) - 'a']++;
                i++;
            }

        }

        return result;
    }
}

// 滑动窗口：方法三  计算不同字符的个数
class Solution438_03 {
    public List<Integer> findAnagrams(String s, String p) {
        // 记录字符串p的单词出现次数
        int[] Record = new int[26];
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0,j = 0,diff = 0;

        for (char c : p.toCharArray()) {
            Record[c - 'a']--;
        }

        for (int item : Record) {
            if (item != 0) {
                diff++;
            }
        }

        while (j < s.length()){
            if (Record[s.charAt(j) - 'a'] == -1){       // 不同变相同
                diff--;
            } else if (Record[s.charAt(j) - 'a'] == 0) {    // 相同变不同
                diff++;
            }

            Record[s.charAt(j) - 'a']++;

            // 慢指针移动
            if (j - i >= p.length()) {
                if (Record[s.charAt(i) - 'a'] == 1) {   // 不同变相同
                    diff--;
                } else if (Record[s.charAt(i) - 'a'] == 0) {    // 相同变不同
                    diff++;
                }

                Record[s.charAt(i) - 'a']--;
                i++;
            }

            if (diff == 0) {
                result.add(i);
            }

            j++;
        }

        return result;
    }
}