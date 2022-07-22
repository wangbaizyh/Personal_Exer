package com.geek.leetcode.dfs.island;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 22:36
 * 200. 岛屿数量
 * https://leetcode.cn/problems/number-of-islands/
 *
 * 思路：DFS
 *
 */
public class Code200 {

}

class Solution200 {
    // 方向数组
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0} ,{-1, 0}};
    int m, n;

    public int numIslands(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;

        // 遍历图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    // 标记为已访问的陆地
                    grid[i][j] = '2';
                    dfs(grid, i, j);
                    // 加一个岛屿
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(char[][] grid, int x, int y) {
        // 遍历四个方向
        for (int[] dir : dirs) {
            // 下一步
            int nextX = x + dir[0], nextY = y + dir[1];
            // 不符合情况
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] != '1') {
                continue;
            }
            // 标记为已访问的陆地
            grid[nextX][nextY] = '2';
            dfs(grid, nextX, nextY);
        }
    }
}