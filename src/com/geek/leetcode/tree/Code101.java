package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 14:51
 * 101. 对称二叉树
 * https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 思路：从根节点出发，遍历左右节点两棵子树，遍历两棵树而且要比较内侧和外侧节点，
 * 所以准确的来说是一个树的遍历顺序是左右中，一个树的遍历顺序是右左中。
 *
 * 后序遍历
 *
 */
public class Code101 {

}

// 后序遍历 递归法
class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        // 处理树为空的情况
        if (root == null) return true;
        // 同时遍历左右子树
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;

        // 此时就是：左右节点都不为空，且数值相同的情况
        // 此时才做递归，做下一层的判断
        // 左子树左节点 和 右子树右节点比较
        boolean outside = compare(left.left, right.right);
        // 左子树右节点 和 右子树左节点比较
        boolean inside = compare(left.right, right.left);

        // 中：结合左右比较结果
        return outside && inside;
    }
}

// 迭代法 队列
class Solution101_01 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            queue.offer(left.left);
            queue.offer(right.right);

            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }
}

// 迭代法 栈 （也可以使用双端队列模拟两个栈）
class Solution101_02 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);

        while (!stack.isEmpty()) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();

            if (left == null && right == null) {
                continue;
            }

            if (left == null || right == null || left.val != right.val) {
                return false;
            }

            stack.push(left.left);
            stack.push(right.right);

            stack.push(left.right);
            stack.push(right.left);
        }

        return true;
    }
}