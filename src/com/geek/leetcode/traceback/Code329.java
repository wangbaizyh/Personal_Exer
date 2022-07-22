package com.geek.leetcode.traceback;

/**
 * @author G.E.E.K.
 * @create 2022-07-04 10:58
 * 329. 矩阵中的最长递增路径
 * https://leetcode.cn/problems/longest-increasing-path-in-a-matrix/
 *
 * 思路：记忆化搜索 (需要有返回值)
 *
 */
public class Code329 {

}

class Solution329 {
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] matrix, memory;
    int m, n;
    int ans;

    public int longestIncreasingPath(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
        this.memory = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }

        return ans;
    }

    // 记忆化搜索
    private int dfs(int x, int y) {
        // 搜索记忆
        if (memory[x][y] != 0) return memory[x][y];

        memory[x][y] = 1;

        for (int[] dir : dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            // 越界
            if (nextX >= m || nextX < 0 || nextY >= n || nextY < 0 || matrix[nextX][nextY] <= matrix[x][y]) {
                continue;
            }

            int nextLen = dfs(nextX, nextY);
            // 存储并更新记忆
            memory[x][y] = Math.max(memory[x][y], nextLen + 1);
        }

        return memory[x][y];
    }
}