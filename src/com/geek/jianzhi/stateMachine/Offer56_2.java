package com.geek.jianzhi.stateMachine;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 13:54
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 *
 * 思路：排序、哈希、有限状态自动机
 *
 */
public class Offer56_2 {

}

// 有限状态自动机
// 不理解
class Solution56_2_1 {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for (int num : nums) {
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }

        return ones;
    }
}

// 遍历统计
class Solution56_2_2 {
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                counts[i] += num & 1;
                num >>= 1;
            }
        }

        int res = 0, m = 3;
        for (int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }

        return res;
    }
}
