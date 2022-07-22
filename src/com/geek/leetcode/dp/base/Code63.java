package com.geek.leetcode.dp.base;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 16:37
 * 63. 不同路径 II
 * https://leetcode.cn/problems/unique-paths-ii/
 *
 */
public class Code63 {
    @Test
    public void test() {
        int ans = new Solution63_01().uniquePathsWithObstacles(new int[][]{{1, 0}});
        System.out.println(ans);
    }

}

// DFS
class Solution63 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        return dfs(0 ,0, m - 1, n - 1, obstacleGrid);

    }

    private int dfs(int i, int j, int m, int n, int[][] obstacleGrid) {
        if (i > m || j > n) return 0;
        if (i == m && j == n) return 1;
        if (obstacleGrid[i][j] == 1) return 0;

        return dfs(i + 1, j , m, n, obstacleGrid) + dfs(i, j + 1, m, n, obstacleGrid);
    }
}

// DP
class Solution63_01 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 状态：
        // dp[i][j] 到达位置i j的路径数
        // 初始化：
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) dp[i][0] = 0;
            else if (i > 0 && dp[i - 1][0] == 0) dp[i][0] = 0;
            else dp[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) dp[0][i] = 0;
            else if (i > 0 && dp[0][i - 1] == 0) dp[0][i] = 0;
            else dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

class Solution63_02 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        // 状态：
        // dp[i][j] 到达位置i j的路径数
        // 初始化：
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }

        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                else dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}

// 一维滚动数组
class Solution63_03 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        // 状态：
        // dp[i] 表示当前层i位置的路径数
        // 初始化：
        for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }
}