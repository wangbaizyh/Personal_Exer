package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-09 11:25
 * 701. 二叉搜索树中的插入操作
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 *
 */
public class Code701 {

}

// 找到一条路径
class Solution701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
            return root;
        }

        // 有方向的递归
        if (root.val > val) root.left = insertIntoBST(root.left, val);
        if (root.val < val) root.right = insertIntoBST(root.right, val);

        return root;
    }
}