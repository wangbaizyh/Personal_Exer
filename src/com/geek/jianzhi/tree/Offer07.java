package com.geek.jianzhi.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-31 11:29
 * 剑指 Offer 07. 重建二叉树
 * https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/
 *
 */
public class Offer07 {
}

class Solution07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preBegin, int preEnd, int[] inorder, int inBegin, int inEnd) {
        // 返回null节点
        if (preBegin > preEnd) return null;

        int rootValue = preorder[preBegin];
        TreeNode root = new TreeNode(rootValue);

        // 处理叶子节点
        if (preBegin == preEnd) {
            return root;
        }

        int mid = 0;
        for (int i = inBegin; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                mid = i;
                break;
            }
        }

        TreeNode left = build(preorder, preBegin + 1, preBegin + mid - inBegin, inorder, inBegin, mid - 1);
        TreeNode right = build(preorder, preBegin+ mid - inBegin + 1, preEnd, inorder, mid + 1, inEnd);

        root.left = left;
        root.right = right;

        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}