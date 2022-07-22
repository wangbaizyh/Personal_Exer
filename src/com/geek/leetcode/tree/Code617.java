package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 14:09
 * 617. 合并二叉树
 * https://leetcode-cn.com/problems/merge-two-binary-trees/
 *
 *
 */
public class Code617 {

}

// 前序遍历
class Solution617 {
    // 同时遍历两个树
    // 以root1为树进行修改
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        // 修改t1的数值和结构
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right,root2.right);

        return root1;
    }
}

// 前序遍历
class Solution617_01 {
    // 同时遍历两个树
    // 新new一棵树
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) return root2;
        if (root2 == null) return root1;
        // 重新定义一个节点
        TreeNode root = new TreeNode();
        root.val = root1.val + root2.val;
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right,root2.right);

        return root;
    }
}