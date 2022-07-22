package com.geek.leetcode.ranklist.weak299;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-06-26 10:59
 * 2319. 判断矩阵是否是一个 X 矩阵
 * https://leetcode.cn/problems/check-if-matrix-is-x-matrix/
 *
 */
public class Code2319 {
    @Test
    public void test() {
        boolean ans = new Solution2319().checkXMatrix(new int[][]{{2, 0, 0, 1}, {0, 3, 1, 0}, {0, 5, 2, 0}, {4, 0, 0, 2}});
        System.out.println(ans);
    }
}

class Solution2319 {
    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0 && (i == j || i + j == grid.length - 1)) continue;
                if (grid[i][j] == 0 && !(i == j || i + j == grid.length - 1)) continue;
                return false;
            }
        }

        return true;
    }
}
