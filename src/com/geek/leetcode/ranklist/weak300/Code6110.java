package com.geek.leetcode.ranklist.weak300;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-07-03 11:36
 * 6110. 网格图中递增路径的数目
 * https://leetcode.cn/contest/weekly-contest-300/problems/number-of-increasing-paths-in-a-grid/
 *
 * 思路：DFS
 *
 */
public class Code6110 {
    @Test
    public void test() {
        int paths = new Solution6110().countPaths(new int[][]{{1, 1}, {3, 4}});
        System.out.println(paths);
    }

}

/**
 * dfs记忆化搜索:
 * 从每个格子出发搜索递增的路径数有多少
 * 有上下左右4个方向,合法的方向是比之前格子严格大的
 * 另外用一个memory[i][j]保存从grid[i][j]出发的递增路径数
 * dfs(i,j)主逻辑:
 * grid[i][j]出发的递增路径数 = 本身自成1条路径 + 上下左右出发严格递增路径数之和
 *
 */
class Solution6110 {
    private static final int MOD = (int) 1e9 + 7;
    // 方向
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;
    // 记忆化容器：保存从grid[i][j]出发的递增路径数
    int[][] memory, grid;
    boolean[][] used;

    public int countPaths(int[][] grid) {
        // 初始化
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        memory = new int[m][n];
        used = new boolean[m][n];
        long count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                used[i][j] = true;
                count = (count + dfs(i, j)) % MOD;
                used[i][j] = false;
            }
        }

        return (int) count;
    }

    private int dfs(int i, int j) {
        // 搜索记忆
        // 已经搜索过了,直接返回其数值
        if (memory[i][j] != 0) return memory[i][j];
        // 本身自成一条严格递增路径
        long res = 1;

        // 一共有4个搜索方向
        for (int[] dir : dirs) {
            int nextI = i + dir[0], nextJ = j + dir[1];
            // 越界 || 已经搜索过 || 大小不符合要求
            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || used[nextI][nextJ] || grid[nextI][nextJ] <= grid[i][j]) {
                continue;
            }

            used[nextI][nextJ] = true;
            // 下一点出发点路径数
            int paths = dfs(nextI, nextJ);
            used[nextI][nextJ] = false;

            // 累加结果
            res = (res + paths) % MOD;
        }
        // 存储记忆
        memory[i][j] = (int) res;
        return (int) res;
    }
}

class Solution6110_2 {
    private static final int MOD = (int) 1e9 + 7;
    // 方向
    private static final int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m, n;
    // 记忆化容器：保存从grid[i][j]出发的递增路径数
    int[][] memory, grid;

    public int countPaths(int[][] grid) {
        // 初始化
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;
        memory = new int[m][n];
        long count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count = (count + dfs(i, j)) % MOD;
            }
        }

        return (int) count;
    }

    private int dfs(int i, int j) {
        // 搜索记忆
        // 已经搜索过了,直接返回其数值
        if (memory[i][j] != 0) return memory[i][j];
        // 本身自成一条严格递增路径
        long res = 1;

        // 一共有4个搜索方向
        for (int[] dir : dirs) {
            int nextI = i + dir[0], nextJ = j + dir[1];
            // 越界 || 已经搜索过 || 大小不符合要求
            if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || grid[nextI][nextJ] <= grid[i][j]) {
                continue;
            }

            // 下一点出发点路径数
            int paths = dfs(nextI, nextJ);

            // 累加结果
            res = (res + paths) % MOD;
        }
        // 存储记忆
        memory[i][j] = (int) res;
        return (int) res;
    }
}

// 暴力DFS
// 超时
class Solution6110_xx {
    private static final int MOD = (int) 1e9 + 7;
    long count;
    int n, m;

    public int countPaths(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        count = m * n;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                boolean[][] used = new boolean[n][m];
                used[i][j] = true;
                dfs(grid, i, j, used);
            }
        }

        return (int) count % MOD;
    }

    private void dfs(int[][]grid, int i, int j, boolean[][] used) {
        // 向右走
        if (j + 1 < m && !used[i][j + 1] && grid[i][j + 1] > grid[i][j]) {
            j++;
            used[i][j] = true;
            count++;
            dfs(grid, i, j, used);
            used[i][j] = false;
            j--;
        }

        // 向下走
        if (i + 1 < n && !used[i + 1][j] && grid[i + 1][j] > grid[i][j]) {
            i++;
            used[i][j] = true;
            count++;
            dfs(grid, i, j, used);
            used[i][j] = false;
            i--;
        }

        // 向左走
        if (j - 1 >= 0 && !used[i][j - 1] && grid[i][j - 1] > grid[i][j]) {
            j--;
            used[i][j] = true;
            count++;
            dfs(grid, i, j, used);
            used[i][j] = false;
            j++;
        }

        // 向上走
        if (i - 1 >= 0 && !used[i - 1][j] && grid[i - 1][j] > grid[i][j]) {
            i--;
            used[i][j] = true;
            count++;
            dfs(grid, i, j, used);
            used[i][j] = false;
            i++;
        }
    }
}