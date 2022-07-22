package com.geek.leetcode.dp.base;

/**
 * @author G.E.E.K.
 * @create 2022-05-19 20:46
 * 96. 不同的二叉搜索树
 * https://leetcode.cn/problems/unique-binary-search-trees/
 *
 * 思路：寻找重叠子问题
 *
 */
public class Code96 {

}

class Solution96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        // 状态：
        // dp[i] 1到i为节点组成的二叉搜索树的个数为dp[i]。
        // 递推公式：
        // dp[i] += dp[以j为头结点左子树节点数量] * dp[以j为头结点右子树节点数量]
        // dp[i] += dp[j] * dp[i - j];
        // 初始化：
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}