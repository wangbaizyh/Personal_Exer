package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 11:34
 * 654. 最大二叉树
 * https://leetcode-cn.com/problems/maximum-binary-tree/
 *
 * 思路：构造树一般使用前序遍历，先构造根节点再构造左右子树
 *
 */
public class Code654 {

}

class Solution654 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        return buildTree(nums, 0, nums.length);
    }

    public TreeNode buildTree(int[] nums, int left, int right) {
        if (right == left) return null;

        int index = left;
        int rootValue = nums[index];

        for (int i = left + 1; i < right; i++) {
            if (nums[i] > rootValue) {
                index = i;
                rootValue = nums[i];
            }
        }

        TreeNode root = new TreeNode(rootValue);
        // 叶子节点
        if (right - left == 1) return root;

        // 递归遍历左右区间
        root.left = buildTree(nums, left, index);
        root.right = buildTree(nums, index + 1, right);

        return root;
    }
}