package com.geek.leetcode.dfs.island;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 23:11
 * 827. 最大人工岛
 * https://leetcode.cn/problems/making-a-large-island/
 *
 * 思路：DFS
 *
 */
public class Code827 {
    public static void main(String[] args) {
        System.out.println();
    }

}

class Solution827 {
    // 方向数组
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0} ,{-1, 0}};
    int m, n;
    HashMap<Integer, Integer> map;

    public int largestIsland(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int ans = 0;
        // 小岛标记
        int island = 2;
        // 存储小岛的面积
        map = new HashMap<>();

        // 遍历岛屿
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    // 标记为已访问的陆地
                    grid[i][j] = island;
                    int area = dfs(grid, i, j, island);
                    map.put(island, area);
                    island++;
                    ans = Math.max(ans, area);
                }
            }
        }

        // 遍历海洋
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    int area = countArea(grid, i, j);
                    ans = Math.max(ans, area);
                }
            }
        }

        return ans;
    }

    private int countArea(int[][] grid, int x, int y) {
        int area = 1;
        HashSet<Integer> set = new HashSet<>();

        // 遍历四个方向
        for (int[] dir : dirs) {
            // 下一步
            int nextX = x + dir[0], nextY = y + dir[1];
            // 不符合情况
            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] == 0) {
                continue;
            }

            if (set.contains(grid[nextX][nextY])) continue;

            set.add(grid[nextX][nextY]);
            area += map.get(grid[nextX][nextY]);
        }

        return area;
    }


    private int dfs(int[][] grid, int x, int y, int island) {
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
            grid[nextX][nextY] = island;
            ans += dfs(grid, nextX, nextY, island);
        }

        return ans;
    }
}