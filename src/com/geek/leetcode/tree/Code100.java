package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 15:27
 * 100. 相同的树
 * https://leetcode-cn.com/problems/same-tree/
 *
 * 思路：同时遍历两个树，看两棵树的结构和数值是否相同
 *
 */
public class Code100 {

}

// 后序遍历 递归法
class Solution100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return compare(p, q);
    }

    // 同时遍历两个树，进行后序遍历
    public boolean compare(TreeNode p, TreeNode q) {
        // 都为空，则相同
        if (p == null && q == null) return true;
        // 一个为空或者节点值不同，则不相同
        if (p == null || q == null || p.val != q.val) return false;

        // 同时遍历左子树
        boolean left = compare(p.left, q.left);
        // 同时遍历右子数
        boolean right = compare(p.right, q.right);

        // 中：都相同则相同
        return left && right;
    }
}

// 迭代法（队列）
class Solution100_01 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode leftTree = queue.poll();
            TreeNode rightTree = queue.poll();

            // 都为空则相同
            if (leftTree == null && rightTree == null) continue;

            // 不相同的情况
            if (leftTree == null || rightTree == null || leftTree.val != rightTree.val) {
                return false;
            }

            // 对比两棵树的左子树
            queue.offer(leftTree.left);
            queue.offer(rightTree.left);
            // 对比两棵树的右子树
            queue.offer(leftTree.right);
            queue.offer(rightTree.right);
        }

        return true;
    }
}