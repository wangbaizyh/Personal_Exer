package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-09 12:34
 * 669. 修剪二叉搜索树
 * https://leetcode.cn/problems/trim-a-binary-search-tree/
 *
 * 思路：理解递归的模拟过程
 *
 */
public class Code669 {

}

class Solution669 {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        // 空节点直接返回
        if (root == null) return null;
        // 节点小于low，需要删除，寻找右子树符合条件的节点
        if (root.val < low) return trimBST(root.right, low, high);
        // 节点大于high，需要删除，寻找左子树符合条件的节点
        if (root.val > high) return trimBST(root.left, low, high);

        // 在[low, high]范围内的节点，遍历其左右子树
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }
}