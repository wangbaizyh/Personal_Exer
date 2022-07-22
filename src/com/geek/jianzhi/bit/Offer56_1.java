package com.geek.jianzhi.bit;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 13:39
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 *
 * 思路：位运算 异或运算
 * 难点：有两个只出现一次，不能只使用异或来解决
 *
 */
public class Offer56_1 {

}

class Solution56_1 {
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, n = 0, m = 1;
        // 1. 遍历异或
        for (int num : nums) {
            n ^= num;
        }
        // 2. 循环左移，计算 m
        while ((n & m) == 0) {
            m <<= 1;
        }
        // 3. 遍历 nums 分组
        for (int num : nums) {
            // 4. 当 num & m != 0
            if ((num & m) == 0) x ^= num;
            // 4. 当 num & m == 0
            else y ^= num;
        }

        return new int[]{x, y};
    }
}