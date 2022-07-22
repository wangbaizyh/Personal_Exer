package com.geek.jianzhi.math;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 14:03
 * 剑指 Offer 66. 构建乘积数组
 * https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/
 *
 * 思路：
 *
 */
public class Offer66 {

}

class Solution66 {
    public int[] constructArr(int[] a) {
        int len = a.length;
        if (len == 0 ) return new int[0];

        int[] res = new int[len];

        // A[i]左边的乘积(不包含A[i])
        int[] resLeft = new int[len];
        resLeft[0] = 1;

        for (int i = 1; i < len; i++) {
            resLeft[i] = resLeft[i - 1] * a[i - 1];
        }

        // A[i]右边的乘积(不包含A[i])
        int[] resRight = new int[len];
        resRight[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            resRight[i] = resRight[i + 1] * a[i + 1];
        }

        for (int i = 0; i < len; i++) {
            res[i] = resLeft[i] * resRight[i];
        }

        return res;
    }
}
