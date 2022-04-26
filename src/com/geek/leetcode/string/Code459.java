package com.geek.leetcode.string;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 16:05
 * 459. 重复的子字符串
 * https://leetcode-cn.com/problems/repeated-substring-pattern/
 *
 * KMP算法
 *
 * 数组长度 减去 最长相同前后缀的长度 相当于是 第一个周期的长度，也就是一个周期的长度，
 * 如果这个周期可以被整除，就说明整个数组就是这个周期的循环。
 *
 */
public class Code459 {

    @Test
    public void test() {

    }

}

class Solution459 {
    public boolean repeatedSubstringPattern(String s) {
        int[] next = new int[s.length()];
        getNext(next, s);
        // 第一个周期的长度
        int res = s.length() % (s.length() - next[s.length() - 1]);

        // 字符串有最长相同的前后缀
        return next[s.length() - 1] != 0 && res == 0;
    }

    private void getNext(int[] next, String s) {
        int j = 0;
        next[0] = j;

        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)){
                j = next[j - 1];
            }

            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }

            next[i] = j;
        }
    }
}