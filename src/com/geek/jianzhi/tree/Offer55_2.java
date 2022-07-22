package com.geek.jianzhi.tree;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 12:55
 * 剑指 Offer 55 - II. 平衡二叉树
 * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/
 *
 * 思路：
 *
 */
public class Offer55_2 {

}

// 后序遍历
// 求高度
class Solution55_2_1 {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        // 递归结束条件（包括空节点）
        if (root == null) return 0;

        // -1 表示树不平衡
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;

        int result;

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            result = 1 + Math.max(leftHeight, rightHeight);
        }

        return result;
    }
}