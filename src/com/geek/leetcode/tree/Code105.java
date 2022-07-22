package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-07 23:25
 * 105. 从前序与中序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 *
 */
public class Code105 {

}


// 左闭右开
class Solution105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        return traversal(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public TreeNode traversal(int[] preorder, int preLeft, int preRight,
                          int[] inorder, int inLeft, int inRight) {
        // 数组长度为0，或者没有元素了
        if (preLeft == preRight) return null;

        // 取前序节点第一个元素作为节点元素
        int rootValue = preorder[preLeft];
        TreeNode root = new TreeNode(rootValue);

        // 叶子节点
        if (preRight - preLeft == 1) return root;

        // 找中序数组分割点
        int index;
        for (index = inLeft; index < inRight; index++) {
            if (inorder[index] == rootValue) break;
        }

        // 递归迭代左区间和右区间
        root.left = traversal(preorder, preLeft + 1, preLeft + 1 + index - inLeft ,inorder, inLeft, index);
        root.right = traversal(preorder, preLeft + 1 + index - inLeft, preRight, inorder, index + 1, inRight);

        return root;
    }
}