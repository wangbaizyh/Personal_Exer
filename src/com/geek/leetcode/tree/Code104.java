package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 10:44
 * 104. 二叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * 思路：需要遍历整棵树，找到最大深度
 * 方法：层次遍历、后序遍历（求根节点的高度）、先序遍历 + 回溯法（求深度）
 *
 */
public class Code104 {

}


// 层次遍历
class Solution104 {
    public int maxDepth(TreeNode root) {
        int depth = 0;
        // 判断空根节点，防止进入队列，出现空指针问题
        if (root == null) {
            return depth;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // 如果队列不为空，说明该层有节点，因此深度+1
            int len = queue.size();
            depth++;

            // 继续往下遍历
            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return depth;
    }
}

// 后序遍历 -- 求根节点的高度
class Solution104_01 {
    public int maxDepth(TreeNode root) {
        return getDepth(root);
    }

    public int getDepth(TreeNode node) {
        // 递归结束条件
        if (node == null) return 0;
        int leftDepth = getDepth(node.left);        // 左
        int rightDepth = getDepth(node.right);      // 右
        // 返回左右子树的最大高度 + 1 （根节点的高度）
        int depth = 1 + Math.max(leftDepth, rightDepth);    // 中
        return depth;
    }
}

// 前序遍历 -- 回溯法
class Solution104_02 {
    // 定义全局遍历存储回溯法的结果，也就是最大深度
    // 初始化最大深度为0
    int result = 0;

    public int maxDepth(TreeNode root) {
        // 判断空节点情况
        if (root == null) return result;
        // 至少一个节点情况，此时传入深度为：1
        getDepth(root, 1);
        return result;
    }

    public void getDepth(TreeNode node, int depth) {
        // 如果当前深度 > 最大深度，更新最大深度
        result = Math.max(depth, result);   // 中 （每个节点都更新）

        if (node.left == null && node.right == null) return;

        if (node.left != null) {            // 左
            depth++;                        // 深度+1
            getDepth(node.left, depth);
            depth--;                        // 回溯，深度-1
        }

        if (node.right != null) {           // 右
            depth++;                        // 深度+1
            getDepth(node.right, depth);
            depth--;                        // 回溯，深度-1
        }
    }
}