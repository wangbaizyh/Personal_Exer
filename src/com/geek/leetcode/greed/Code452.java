package com.geek.leetcode.greed;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author G.E.E.K.
 * @create 2022-05-17 21:35
 * 452. 用最少数量的箭引爆气球
 * https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/
 *
 */
public class Code452 {
    @Test
    public void test() {
        int ans = new Solution452().findMinArrowShots(new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}});
        System.out.println(ans);
        ans = new Solution452().findMinArrowShots(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}});
        System.out.println(ans);
    }
}

class Solution452 {
    public int findMinArrowShots(int[][] points) {
        // 比较函数最好用大于
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

        int count = 1;
        int top = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= top) {
                top = Math.min(top, points[i][1]);
            } else {
                count++;
                top = points[i][1];
            }
        }

        return count;
    }
}

class Solution452_01 {
    public int findMinArrowShots(int[][] points) {
        // 比较函数最好用大于
        Arrays.sort(points, Comparator.comparingInt(o -> o[0]));

        int count = 1;

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= points[i - 1][1]) {
                points[i][1] = Math.min(points[i - 1][1], points[i][1]);
            } else {
                count++;
            }
        }

        return count;
    }
}

