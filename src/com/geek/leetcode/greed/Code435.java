package com.geek.leetcode.greed;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author G.E.E.K.
 * @create 2022-05-18 15:29
 * 435. 无重叠区间
 * https://leetcode.cn/problems/non-overlapping-intervals/
 */
public class Code435 {

}

// 贪心算法
// 局部最优：优先选右边界小的区间，所以从左向右遍历，留给下一个区间的空间大一些，从而尽量避免交叉。
// 全局最优：选取最多的非交叉区间。
class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        // 按照区间右边界从小到大排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        // 记录非交叉区间的个数
        int count = 1;
        // 记录区间分割点
        int end = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (end <= intervals[i][0]) {
                end = intervals[i][1];
                count++;
            }
        }

        return intervals.length - count;
    }
}