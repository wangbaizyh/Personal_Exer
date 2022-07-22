package com.geek.leetcode.dfs.island;

/**
 * @author G.E.E.K.
 * @create 2022-07-12 10:55
 * 463. 岛屿的周长
 * https://leetcode.cn/problems/island-perimeter/
 *
 * 思路：DFS
 *
 */
public class Code463 {

}

class Solution463 {
    // 方向数组
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0} ,{-1, 0}};
    int m, n;

    public int islandPerimeter(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // 遍历图
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 标记为已访问的陆地
                    grid[i][j] = 2;
                    return dfs(grid, i, j);
                }
            }
        }

        return 0;
    }

    private int dfs(int[][] grid, int x, int y) {
        // 周长
        int ans = 0;

        // 遍历四个方向
        for (int[] dir : dirs) {
            // 下一步
            int nextX = x + dir[0], nextY = y + dir[1];
            // 越界周长
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                ans++;
                continue;
            }
            // 海洋周长
            if (grid[nextX][nextY] == 0) {
                ans++;
                continue;
            }
            // 已访问陆地
            if (grid[nextX][nextY] == 2) {
                continue;
            }
            // 标记为已访问的陆地
            grid[nextX][nextY] = 2;
            ans += dfs(grid, nextX, nextY);
        }

        return ans;
    }
}