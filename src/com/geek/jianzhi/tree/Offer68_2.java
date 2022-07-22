package com.geek.jianzhi.tree;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 15:38
 * 剑指 Offer 68 - II. 二叉树的最近公共祖先
 * https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 *
 * 思路：后序遍历 + 回溯
 *
 */
public class Offer68_2 {

}

// 遍历整棵二叉树
// 后序遍历 + 回溯
class Solution68_2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 当前节点为空 || 当前节点为指定的节点
        if (root == p || root == q || root == null) return root;

        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 若找到两个节点，返回当前节点 （最小公共祖先）
        if (left != null && right != null) return root;
        // 只找到一个节点，返回找到的节点
        // 包含两种情况：找到最小公共祖先向上传递 || 没找到向上传递
        else if (left == null && right != null) return right;
        else if (left != null && right == null) return left;
        // 若未找到节点 p 或 q，返回null
        else return null;
    }
}