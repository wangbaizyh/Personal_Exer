package com.geek.leetcode.string;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-04-24 17:32
 * 541. 反转字符串 II
 * https://leetcode-cn.com/problems/reverse-string-ii/
 *
 * 模拟题
 *
 *
 */
public class Code541 {
    public static void main(String[] args) {
        String s = new Solution541_01().reverseStr("abcdefg", 2);
        System.out.println(s);
    }
}

// 解法一：暴力模拟
class Solution541_01 {
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i = i + 2 * k) {

            // 最后小于k个字符的时候，全部反转
            if (chars.length - i < k) {
                swap(i,chars.length - 1, chars);
            } else {
                // 其它情况反转k个字符
                swap(i,i + k - 1, chars);
            }
        }

        // String得用new的形式新创建
        return new String(chars);
    }

    private void swap(int left,int right, char[] chars){
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
    }
}

// 解法二：使用StringBuilder来进行缓冲
class Solution541_02 {
    public String reverseStr(String s, int k) {
        // 字符串缓冲器
        StringBuilder res = new StringBuilder();
        int length = s.length();
        int start = 0;
        // 遍历整个字符串
        while (start < length) {
            // 缓冲反转的字符串
            StringBuilder tmp = new StringBuilder();
            int firstK = Math.min(start + k, length);
            int secondK = Math.min(start + (2 * k), length);

            tmp.append(s, start, firstK);
            res.append(tmp.reverse());

            if (firstK < secondK) {
                res.append(s, firstK, secondK);
            }

            start += 2 * k;
        }

        return res.toString();
    }
}