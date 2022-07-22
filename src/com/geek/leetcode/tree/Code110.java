package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 17:50
 * 110. 平衡二叉树
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 *
 * 树的深度与高度的概念
 * 求深度用前序遍历
 * 求高度用后序遍历
 *
 */
public class Code110 {

}

// 后序遍历 -- 求高度
class Solution110 {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    public int getHeight(TreeNode node) {
        // 递归结束条件（包括空节点）
        if (node == null) return 0;

        // -1 表示树不平衡
        int leftHeight = getHeight(node.left);         // 左
        if (leftHeight == -1) return -1;
        int rightHeight = getHeight(node.right);       // 右
        if (rightHeight == -1) return -1;

        int result;
        // 当前节点左右子树的高度差 > 1，则不平衡
        if (Math.abs(leftHeight - rightHeight) > 1) {   // 中
            return -1;
        } else {
            // 当前子树平衡
            // 更新以当前节点为根节点的树的最大高度
            result = 1 + Math.max(leftHeight, rightHeight);
        }

        // 最终返回的是根节点的最大高度
        return result;
    }
}