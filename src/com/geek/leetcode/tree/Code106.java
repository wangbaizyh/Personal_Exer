package com.geek.leetcode.tree;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-07 21:15
 * 106. 从中序与后序遍历序列构造二叉树
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * 思路：一层一层切割（递归）
 *      - 第一步：如果数组大小为零的话，说明是空节点了。
 *      - 第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
 *      - 第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点
 *      - 第四步：切割中序数组，切成中序左数组和中序右数组 （顺序别搞反了，一定是先切中序数组）
 *      - 第五步：切割后序数组，切成后序左数组和后序右数组
 *      - 第六步：递归处理左区间和右区间
 *
 * 切割数组注意循环不变量原则（左闭右开）
 */
public class Code106 {

}

class Solution106 {
    // 中序区间：[inorderBegin, inorderEnd)，后序区间[postorderBegin, postorderEnd)
    public TreeNode traversal(int[] inorder, int inBegin, int inEnd,
                              int[] postorder, int postBegin, int postEnd) {
        // 第一步：如果数组大小为零的话，说明是空节点了。
        // 没有元素了
        if (postBegin == postEnd) return null;
        // 第二步：如果不为空，那么取后序数组最后一个元素作为节点元素。
        int rootValue = postorder[postEnd - 1];
        TreeNode root = new TreeNode(rootValue);

        // 叶子节点（只有一个元素）
        if (postEnd - postBegin == 1) return root;

        // 第三步：找到后序数组最后一个元素在中序数组的位置，作为切割点
        int index;
        for (index = inBegin; index < inEnd; index++) {
            if (inorder[index] == rootValue) break;
        }

        // 第四步：切割中序数组，切成中序左数组和中序右数组
        // 左中序区间，左闭右开[leftInBegin, leftInEnd)
        int leftInBegin = inBegin;
        int leftInEnd = index;
        // 右中序区间，左闭右开[rightInBegin, rightInEnd)
        int rightInBegin = index + 1;
        int rightInEnd = inEnd;

        // 第五步：切割后序数组，切成后序左数组和后序右数组
        // 左闭右开，注意这里使用了左中序数组大小作为切割点：index - inBegin
        // 左后序区间，左闭右开[leftPostBegin, leftPostEnd)
        int leftPostBegin = postBegin;
        int leftPostEnd = postBegin + index - inBegin;
        // 右后序区间，左闭右开[rightPostBegin, rightPostEnd)
        int rightPostBegin = postBegin + index - inBegin;
        int rightPostEnd = postEnd - 1;

        // 第六步：递归处理左区间和右区间
        root.left = traversal(inorder, leftInBegin, leftInEnd, postorder, leftPostBegin, leftPostEnd);
        root.right = traversal(inorder, rightInBegin, rightInEnd, postorder, rightPostBegin, rightPostEnd);

        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0) return null;
        // 左闭右开的原则
        return traversal(inorder, 0, inorder.length, postorder,0, postorder.length);
    }
}

// 左闭右开
class Solution106_01 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) return null;
        return traversal(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode traversal(int[] inorder, int inLeft, int inRight,
                              int[] postorder, int postLeft, int postRight) {
        // 数组长度为0，或者没有元素了
        if (postLeft == postRight) return null;

        // 取后序节点最后一个元素作为节点元素
        int rootValue = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootValue);

        // 叶子节点
        if (postRight - postLeft == 1) return root;

        // 找中序数组分割点
        int index;
        for (index = inLeft; index < inRight; index++) {
            if (inorder[index] == rootValue) break;
        }

        // 递归迭代左区间和右区间
        root.left = traversal(inorder, inLeft, index ,postorder, postLeft, postLeft + index - inLeft);
        root.right = traversal(inorder, index + 1, inRight, postorder, postLeft + index - inLeft, postRight - 1);

        return root;
    }
}