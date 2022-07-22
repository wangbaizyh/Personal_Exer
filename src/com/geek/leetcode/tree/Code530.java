package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 15:40
 * 530. 二叉搜索树的最小绝对差
 * https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
 *
 */
public class Code530 {

}

class Solution530 {
    TreeNode pre;
    int min = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inorder(root);
        return min;
    }

    public void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        if (pre != null) {
            min = Math.min(min, root.val - pre.val);
        }

        pre = root;

        inorder(root.right);
    }
}