package com.geek.jianzhi.tree;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 15:16
 * 剑指 Offer 68 - I. 二叉搜索树的最近公共祖先
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * 思路：利用二叉搜索树的特性
 *
 */
public class Offer68_1 {

}

// 前序遍历
class Solution68_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 两节点在左右两边的情况
        if ((p.val >= root.val && q.val <= root.val) || (p.val <= root.val && q.val >= root.val)) return root;

        if (p.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return lowestCommonAncestor(root.left, p, q);
    }
}