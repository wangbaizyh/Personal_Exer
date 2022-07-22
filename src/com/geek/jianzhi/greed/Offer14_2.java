package com.geek.jianzhi.greed;

/**
 * @author G.E.E.K.
 * @create 2022-06-03 23:29
 * 剑指 Offer 14- II. 剪绳子 II
 * https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
 *
 * 思路：不适合用dp
 * 使用 贪心算法 + 快速幂
 * 大数越界情况下的求余问题：快速幂
 * 大数越界求余方案：循环求余（线性复杂度） 、快速幂求余（对数复杂度）（二分取余法）
 *
 *
 */
public class Offer14_2 {

}

// 贪心算法 (循环求余)
class Solution14_2 {
    private final static int mod = 1000000007;

    public int cuttingRope(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;

        long res = 1;
        // 余数为1的时候变成2*2
        while (n > 4) {
            res = res * 3 % mod;
            n -= 3;
        }

        res = res * n % mod;

        return (int) res;
    }
}

