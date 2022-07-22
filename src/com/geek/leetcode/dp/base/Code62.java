package com.geek.leetcode.dp.base;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 15:36
 * 62. 不同路径
 * https://leetcode.cn/problems/unique-paths/
 *
 */
public class Code62 {

}

// DFS 暴力超时
class Solution62 {
    public int uniquePaths(int m, int n) {
        return dfs(1, 1, m, n);
    }

    // 后序遍历
    private int dfs(int i, int j, int m, int n) {
        if (i > m || j > n) return 0;       // 越界了
        if (i == m && j == n) return 1;     // 找到一种方法，相当于找到了叶子节点
        return dfs(i + 1, j, m, n) + dfs(i, j + 1, m, n);
    }
}


// dp
class Solution62_01 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 状态：
        // dp[i][j] 到达位置i j的路径数
        // 初始化：注意得初始化第一行和第一列！
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 0; i < n; i++) dp[0][i] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 状态转移方程：
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}

// 一维滚动数组
class Solution62_02 {
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        // 状态：
        // dp[i] 表示当前层i位置的路径数
        // 初始化：
        // 第一层都只有一条路径
        for (int i = 0; i < n; i++) dp[i] = 1;

        // 滚动到最后一层
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 状态转移方程：
                // 当前层 i 位置路径数 = 上一层 i 位置路径数 + 当前层 i - 1 位置路径数
                dp[i] += dp[i - 1];
            }
        }

        return dp[n - 1];
    }
}


// 数论方法 -- 组合问题
class Solution62_03 {
    public int uniquePaths(int m, int n) {
        long up = 1;        // 分子
        int down = m - 1;   // 分母
        int count = m - 1;
        int t = m + n - 2;
        while (count-- > 0) {
            up *= (t--);
            while (down != 0 && up % down == 0) {
                up = up / down;
                down--;
            }
        }

        return (int) up;
    }
}