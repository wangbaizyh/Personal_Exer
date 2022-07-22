package com.geek.jianzhi.tree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 12:30
 * 剑指 Offer 55 - I. 二叉树的深度
 * https://leetcode.cn/problems/er-cha-shu-de-shen-du-lcof/
 *
 * 思路：
 */
public class Code55_1 {
    @Test
    public void test() {
        TreeNode root = new TreeNode(1);
        TreeNode child = new TreeNode(2);
        root.left = child;
        int ans = new Solution55_1_2().maxDepth(root);
        System.out.println(ans);
    }
}

// 层序遍历
class Solution55_1_1 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            depth++;
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }

        return depth;
    }
}


// 前序遍历
class Solution55_1_2 {
    int maxDepth;

    public int maxDepth(TreeNode root) {
        preorder(root, 0);
        return maxDepth;
    }

    private void preorder(TreeNode node, int depth) {
        if (node == null) {
            maxDepth = Math.max(maxDepth, depth);
            return;
        }

        depth++;
        preorder(node.left, depth);
        preorder(node.right, depth);
        depth--;
    }
}