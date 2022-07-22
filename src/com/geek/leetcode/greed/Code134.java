package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-15 15:35
 * 134. 加油站
 * https://leetcode.cn/problems/gas-station/
 */
public class Code134 {

}

// 从全局考虑
class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < gas.length; i++) {
            int rest = gas[i] - cost[i];
            curSum += rest;
            if (curSum < min) {
                min = curSum;
            }
        }

        if (curSum < 0) return -1;
        if (min >= 0)   return 0;

        for (int i = gas.length - 1; i >= 0; i--) {
            int rest = gas[i] - cost[i];
            min += rest;
            if (min >= 0) return i;
        }

        return -1;
    }
}

// 贪心算法
// 局部最优：当前累加rest[j]的和curSum一旦小于0，起始位置至少要是j+1，因为从j开始一定不行。
// 全局最优：找到可以跑一圈的起始位置。
class Solution134_01 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;

        for (int i = 0; i < gas.length; i++) {
            int rest = gas[i] - cost[i];
            curSum += rest;
            totalSum += rest;
            if (curSum < 0) {       // 当前累加rest[i]和 curSum 一旦小于0
                start = i + 1;      // 起始位置更新为i+1
                curSum = 0;         // curSum从0开始
            }
        }

        if (totalSum < 0) return -1;
        return start;
    }
}