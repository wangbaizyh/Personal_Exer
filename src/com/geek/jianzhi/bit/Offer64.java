package com.geek.jianzhi.bit;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 11:36
 * 剑指 Offer 64. 求1+2+…+n
 * https://leetcode.cn/problems/qiu-12n-lcof/
 *
 * 思路：迭代 + 位运算 逻辑符短路
 *
 */
public class Offer64 {

}

class Solution64 {
    int res = 0;
    public int sumNums(int n) {
        boolean x = n > 1 && sumNums(n - 1) > 0;
        res += n;
        return res;
    }
}