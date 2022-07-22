package com.geek.jianzhi.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-14 21:37
 * 剑指 Offer 27. 二叉树的镜像
 * https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/
 *
 * 思路：交换每层的左右节点 (翻转二叉树)
 *
 */
public class Offer27 {

}

// 先序遍历
class Solution27 {
    public TreeNode mirrorTree(TreeNode root) {
        preorder(root);

        return root;
    }

    private void preorder(TreeNode root) {
        if (root == null) return;

        swap(root);
        preorder(root.left);
        preorder(root.right);
    }

    // 空节点也可能交换
    private void swap(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
}

// 层序遍历
// 交换每层的左后孩子节点
class Solution27_01 {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                swap(node);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return root;
    }

    // 空节点也可能交换
    private void swap(TreeNode node) {
        TreeNode tmp = node.left;
        node.left = node.right;
        node.right = tmp;
    }
}