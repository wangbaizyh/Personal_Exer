package com.geek.jianzhi.tree;

/**
 * @author G.E.E.K.
 * @create 2022-06-14 21:52
 * 剑指 Offer 28. 对称的二叉树
 * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/
 *
 * 思路：后序遍历（有返回值）
 *
 */
public class Offer28 {

}

class Solution28 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return compare(root.left, root.right);
    }

    private boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;

        boolean outside = compare(left.left, right.right);
        boolean inside = compare(left.right, right.left);

        return outside && inside;
    }
}