package com.geek.leetcode.greed;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-05-18 16:12
 * 56. 合并区间
 * https://leetcode.cn/problems/merge-intervals/
 *
 * 思路：贪心
 *
 */
public class Code56 {

}

class Solution56 {
    public int[][] merge(int[][] intervals) {
        // 按照左区间从小到大排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new ArrayList<>();
        // 初始区间
        int[] element = new int[2];
        element[0] = intervals[0][0];
        element[1] = intervals[0][1];
        if (intervals.length == 1) {
            result.add(element);
            return result.toArray(new int[][]{});
        }

        for (int i = 1; i < intervals.length; i++) {
            // 起始位置超过右区间
            if (intervals[i][0] > element[1]) {
                result.add(element);
                element = new int[2];
                element[0] = intervals[i][0];
            }

            // 取最大的右边界
            element[1] = Math.max(element[1], intervals[i][1]);

            if (i == intervals.length - 1) {
                result.add(element);
            }
        }

        return result.toArray(new int[][]{});
    }
}

// 贪心
class Solution56_1 {
    public int[][] merge(int[][] intervals) {
        // 按照左区间从小到大排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> result = new LinkedList<>();
        int start = intervals[0][0];

        for (int i = 1; i < intervals.length; i++) {
            // 起始位置超过右区间
            if (intervals[i][0] > intervals[i - 1][1]) {
                result.add(new int[]{start, intervals[i - 1][1]});
                start = intervals[i][0];
            }

            // 取最大的右边界
            intervals[i][1] = Math.max(intervals[i - 1][1], intervals[i][1]);
        }

        result.add(new int[]{start, intervals[intervals.length - 1][1]});
        return result.toArray(new int[result.size()][]);
    }
}