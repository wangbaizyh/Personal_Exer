package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 10:50
 * 111. 二叉树的最小深度
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * 总结：后序遍历的话，从底向上，因此必须对递归的返回值进行处理（例如求高度）
 * 层序遍历直接处理需要的结果。
 * 前序遍历，例如求深度，需要进行回溯，定义全局遍历存储最终需要的结果，递归参数也需要传入需要回溯的值
 *
 */
public class Code111 {

}

// 层次遍历
class Solution111 {
    public int minDepth(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            depth++;

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                // 找第一个叶子节点所在层的深度，就是最小深度
                if (node.left == null && node.right == null) return depth;

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return depth;
    }
}

// 最小深度：根节点到叶子节点
// 后序遍历
class Solution111_01 {
    public int minDepth(TreeNode root) {
        return getDepth(root);
    }

    public int getDepth(TreeNode node) {
        if (node == null) return 0;

        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);

        // 左空，右不空（不是叶子节点）--右子数深度+1
        if (node.left == null && node.right != null) {
            return 1 + rightDepth;
        }

        // 右空，左不空（不是叶子节点）--左子树深度+1
        if (node.left != null && node.right == null) {
            return 1 + leftDepth;
        }

        // 叶子节点 或者 左右都不为空的节点的情况 的节点深度
        return 1 + Math.min(leftDepth, rightDepth);
    }
}

