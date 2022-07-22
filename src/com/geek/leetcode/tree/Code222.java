package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 17:07
 * 222. 完全二叉树的节点个数
 * https://leetcode-cn.com/problems/count-complete-tree-nodes/
 *
 * 思路：完全二叉树的性质：可以差分为 多个满二叉树来进行处理
 *
 */
public class Code222 {

}

// 层次遍历 -- 求每层节点的个数
class Solution222 {
    public int countNodes(TreeNode root) {
        int result = 0;
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            result += len;

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return result;
    }
}

// 后序遍历 -- 从底向上累加所有节点的个数
class Solution222_01 {
    public int countNodes(TreeNode root) {
        return getNodes(root);
    }

    public int getNodes(TreeNode node) {
        if (node == null) return 0;
        int left = getNodes(node.left);
        int right = getNodes(node.right);
        return left + right + 1;
    }
}

// 完全二叉树的特性： 满二叉树 2^树深度 - 1
class Solution222_02 {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        // 左子树是满二叉树
        if (leftDepth == rightDepth) {
            // 左子树 + 根节点
            return (1 << leftDepth) + countNodes(root.right);
        } else {
            // 右子树是满二叉树
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    // 返回当前树最左节点的深度
    public int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            node = node.left;
            depth++;
        }

        return depth;
    }
}
