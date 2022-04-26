package com.geek.leetcode.string;

/**
 * @author G.E.E.K.
 * @create 2022-04-25 21:06
 * 28. 实现 strStr()
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * KMP算法、前缀表
 *
 */
public class Code28 {

}

class Solution28 {
    public int strStr(String haystack, String needle) {
        // 模式串为空，直接返回0
        if (needle.length() == 0){
            return 0;
        }

        // next数组的长度为模式串的长度
        int[] next = new int[needle.length()];
        getNext(next,needle);
        // 前缀表的起始位置
        int j = 0;
        // 注意，此时是遍历文本串，所以起始位置为0
        for (int i = 0; i < haystack.length(); i++) {
            // 不匹配
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)){
                // 前缀表回退
                j = next[j - 1];
            }

            // 匹配，同时后移
            if (haystack.charAt(i) == needle.charAt(j)){
                j++;
            }

            // 文本串s里出现了模式串t
            if (j == needle.length()){
                return i - needle.length() + 1;
            }

        }

        return -1;
    }

    private void getNext(int[] next, String s) {
        // j为前缀末尾的位置
        int j = 0;
        next[0] = j;
        // i为后缀末尾的位置，根据定义，后缀不包含第一个字符，从1开始
        for (int i = 1; i < s.length(); i++) {
            // 不匹配
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                // 前缀向前回退
                j = next[j - 1];
            }

            // 匹配，同时后移
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            // 将j（前缀的长度）赋给next[i]
            next[i] = j;
        }
    }
}