package com.geek.leetcode.string;

/**
 * @author G.E.E.K.
 * @create 2022-04-24 16:12
 * 344. 反转字符串
 * https://leetcode-cn.com/problems/reverse-string/
 *
 * 双指针法：左右指针法
 *
 */
public class Code344 {

}

class Solution344_01 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        char tmp;

        while (left < right) {
            tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;

            left++;
            right--;
        }
    }
}

class Solution344_02 {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            s[left] ^= s[right];
            s[right] ^= s[left];
            s[left] ^= s[right];

            left++;
            right--;
        }
    }
}