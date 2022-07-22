package com.geek.leetcode.dp.knapsackProblem;

/**
 * @author G.E.E.K.
 * @create 2022-05-23 15:15
 * 474. 一和零
 * https://leetcode.cn/problems/ones-and-zeroes/
 *
 */
public class Code474 {

}

// 二个维度的01背包问题
// 物品的重量有两个维度，也就是有两个背包
class Solution474 {
    public int findMaxForm(String[] strs, int m, int n) {
        // 状态：
        // dp[i][j] 最多有i个0和j个1的字符串的最大子集大小
        int[][] dp = new int[m + 1][n + 1];
        // 三层for循环
        for (String str : strs) {
            int oneNum = 0, zeroNum = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zeroNum++;
                else oneNum++;
            }

            // 遍历背包容量且从后向前遍历！
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
