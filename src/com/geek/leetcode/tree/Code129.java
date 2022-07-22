package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 21:01
 * 129. 求根节点到叶节点数字之和
 * https://leetcode.cn/problems/sum-root-to-leaf-numbers/
 *
 * 思路：回溯
 * 根节点到叶节点的路径和
 *
 */
public class Code129 {

}

// 前序遍历
class Solution129 {
    int res;

    public int sumNumbers(TreeNode root) {
        int trace = root.val;
        preorder(root, trace);
        return res;
    }

    private void preorder(TreeNode node, int trace) {
        // 到达叶子节点
        if (node.left == null && node.right == null) {
            res += trace;
            return;
        }

        if (node.left != null) preorder(node.left, trace * 10 + node.left.val);
        if (node.right != null) preorder(node.right, trace * 10 + node.right.val);
    }
}

// 后序遍历(带返回值)
class Solution129_1 {
    public int sumNumbers(TreeNode root) {
        return postorder(root, 0 );
    }

    private int postorder(TreeNode node, int trace) {
        if (node == null) return 0;
        int value = trace * 10 + node.val;
        if (node.left == null && node.right == null) {
            return value;
        }

        return postorder(node.left, value) + postorder(node.right, value);
    }
}

// 迭代法（原地修改节点值）
class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                // 叶子节点
                if (node.left == null && node.right == null) ans += node.val;
                // 左节点
                if (node.left != null) {
                    node.left.val = node.val * 10 + node.left.val;
                    queue.offer(node.left);
                }
                // 右节点
                if (node.right != null){
                    node.right.val = node.val * 10 + node.right.val;
                    queue.offer(node.right);
                }
            }
        }
        return ans;
    }
}