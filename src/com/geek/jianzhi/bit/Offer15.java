package com.geek.jianzhi.bit;

/**
 * @author G.E.E.K.
 * @create 2022-06-04 23:07
 * 剑指 Offer 15. 二进制中1的个数
 * https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 *
 * 思路：位运算
 *
 */
public class Offer15 {

}

class Solution15 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int num = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & 1) == 1) num++;
            n = n >> 1;
        }

        return num;
    }
}


