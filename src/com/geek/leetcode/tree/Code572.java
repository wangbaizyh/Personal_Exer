package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 15:37
 * 572. 另一棵树的子树
 * https://leetcode-cn.com/problems/subtree-of-another-tree/
 *
 *
 */
public class Code572 {

}

// 层序遍历 + 后序遍历
class Solution572 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean ans = false;

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                // 当前节点值和另一棵树的根节点值相同，进行遍历比较
                if (node.val == subRoot.val) {
                    ans = compare(node, subRoot);
                    // 相同，则存在子树，返回
                    if (ans) return true;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        // 不是子树
        return false;
    }

    // 判断两棵树是否相等，后序遍历
    public boolean compare(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;

        boolean left = compare(p.left, q.left);
        boolean right = compare(p.right, q.right);

        return left && right;
    }
}

// 迭代法 队列
class Solution572_01 {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null) return true;
        if (root == null || subRoot == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean ans = false;

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                if (node.val == subRoot.val) {
                    ans = compare(node, subRoot);
                    if (ans) return true;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return false;
    }

    public boolean compare(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode leftTree = queue.poll();
            TreeNode rightTree = queue.poll();

            if (leftTree == null && rightTree == null) continue;

            if (leftTree == null || rightTree == null || leftTree.val != rightTree.val) {
                return false;
            }

            queue.offer(leftTree.left);
            queue.offer(rightTree.left);

            queue.offer(leftTree.right);
            queue.offer(rightTree.right);
        }

        return true;
    }
}
