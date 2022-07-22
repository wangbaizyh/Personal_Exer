package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 14:51
 * 700. 二叉搜索树中的搜索
 * https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
 *
 */
public class Code700 {

}

// 递归法 前序遍历
class Solution700 {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root.val == val) return root;

        if (root.val > val && root.left != null) {
            return searchBST(root.left, val);
        }

        if (root.val < val && root.right != null) {
            return searchBST(root.right, val);
        }

        return null;
    }
}

// 迭代法
class Solution700_01 {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null) {
            if (root.val > val) root = root.left;
            else if (root.val < val) root = root.right;
            else return root;
        }

        return null;
    }
}