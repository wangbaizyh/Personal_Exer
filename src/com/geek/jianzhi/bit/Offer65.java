package com.geek.jianzhi.bit;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 11:56
 * 剑指 Offer 65. 不用加减乘除做加法
 * https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/
 *
 * 思路：位运算
 * 非进位和：异或运算
 * 进位：与运算 + 左移一位
 * （和 s ）=（非进位和 n ）+（进位 c ）
 */
public class Offer65 {

}

class Solution65 {
    public int add(int a, int b) {
        // 当进位为 0 时跳出
        while (b != 0) {
            // c = 进位
            int c = (a & b) << 1;
            // a = 非进位和
            a ^= b;
            // b = 进位
            b = c;
        }

        return a;
    }
}