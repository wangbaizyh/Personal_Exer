package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-09 16:49
 * 538. 把二叉搜索树转换为累加树
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 *
 * 思路：右中左，反中序遍历BST
 *
 *
 */
public class Code538 {

}

class Solution538 {
    // 记录前一个节点的数值，初始化为0
    int pre = 0;

    // 右中左遍历
    public void traversal(TreeNode node) {
        if (node == null) return;
        traversal(node.right);
        node.val += pre;
        pre = node.val;
        traversal(node.left);

    }

    public TreeNode convertBST(TreeNode root) {
        traversal(root);
        return root;
    }
}