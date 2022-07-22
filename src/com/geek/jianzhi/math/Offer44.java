package com.geek.jianzhi.math;

/**
 * @author G.E.E.K.
 * @create 2022-06-23 12:03
 * 剑指 Offer 44. 数字序列中某一位的数字
 * https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
 *
 *
 */
public class Offer44 {

}

class Solution44 {
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;

        while (n > count) {
            n -= count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }

        long num = start + (n - 1) / digit;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}