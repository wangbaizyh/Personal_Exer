package com.geek.jianzhi.tree;

/**
 * @author G.E.E.K.
 * @create 2022-06-26 12:15
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 *
 * 思路：中序遍历
 *
 */
public class Offer54 {

}

class Solution54 {
    int ans, k;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return ans;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.right);

        if (k == 0) return;
        if (--k == 0) {
            ans = node.val;
        }

        inorder(node.left);
    }
}