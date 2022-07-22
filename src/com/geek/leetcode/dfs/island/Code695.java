package com.geek.leetcode.dfs.island;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 23:05
 * 695. 岛屿的最大面积
 * https://leetcode.cn/problems/max-area-of-island/
 *
 * 思路：DFS
 *
 */
public class Code695 {

}

class Solution695 {
    // 方向数组
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0} ,{-1, 0}};
    int m, n;

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;

        // 遍历图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 标记为已访问的陆地
                    grid[i][j] = 2;
                    int area = dfs(grid, i, j);
                    ans = Math.max(ans, area);
                }
            }
        }

        return ans;
    }

    private int dfs(int[][] grid, int x, int y) {
        // 面积
        int ans = 1;

        // 遍历四个方向
        for (int[] dir : dirs) {
            // 下一步
            int nextX = x + dir[0], nextY = y + dir[1];
            // 不符合情况
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] != 1) {
                continue;
            }
            // 标记为已访问的陆地
            grid[nextX][nextY] = 2;
            ans += dfs(grid, nextX, nextY);
        }

        return ans;
    }
}