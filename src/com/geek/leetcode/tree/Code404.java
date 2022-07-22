package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-07 10:28
 * 404. 左叶子之和
 * https://leetcode-cn.com/problems/sum-of-left-leaves/
 *
 * 前序遍历、迭代法、层次遍历
 *
 */
public class Code404 {

}

// 前序遍历
class Solution404 {
    int result = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        preorder(root);
        return result;
    }

    // 遍历整棵树
    public void preorder(TreeNode node) {
        if (node == null) return;

        // 左子树为一个叶子节点，添加到结果当中
        if (node.left != null && node.left.left == null && node.left.right == null) {
            result += node.left.val;
        }

        preorder(node.left);
        preorder(node.right);
    }
}

// 后序遍历
class Solution404_04 {

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        int leftValue  = sumOfLeftLeaves(root.left);
        int rightValue  = sumOfLeftLeaves(root.right);

        int midValue  = 0;
        // 当前节点左子树为一个叶子节点，命中规则，更新当前节点的值
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }

        return midValue + leftValue + rightValue;
    }
}

