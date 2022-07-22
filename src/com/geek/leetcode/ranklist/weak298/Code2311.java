package com.geek.leetcode.ranklist.weak298;

/**
 * @author G.E.E.K.
 * @create 2022-06-19 22:03
 * 5254. 卖木头块
 * https://leetcode.cn/problems/selling-pieces-of-wood/
 *
 * 思路：线性DP  -- 子问题
 * 状态定义：
 * dp[i][j] 表示对一块高 i 宽 j 的木块，切割后能得到的最多钱数。
 * 状态转移方程：
 * 如果直接售卖，则收益为对应的 price[i][j]
 * 如果垂直切割，则最大收益为 dp[i][k] + dp[i][j-k] (1 < k < j)
 * 如果水平切割，则最大收益为 dp[k][j] + dp[i-k][j] (1 < k < i)
 * 取上述三种情况的最大值 即为dp[i][j]
 *
 */
public class Code2311 {

}

class Solution2311 {
    public long sellingWood(int m, int n, int[][] prices) {
        // 处理价格矩阵
        int[][] pr = new int[m + 1][n + 1];
        for (int[] p : prices) {
            pr[p[0]][p[1]] = p[2];
        }

        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 本身就有价格
                dp[i][j] = pr[i][j];

                // 切分之后的价格
                // 垂直切割
                for (int k = 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[i][j - k]);
                }

                // 水平切割
                for (int k = 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j] + dp[i - k][j]);
                }
            }
        }

        return dp[m][n];
    }
}
