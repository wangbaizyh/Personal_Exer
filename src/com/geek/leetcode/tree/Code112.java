package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-07 17:49
 * 112. 路径总和
 * https://leetcode-cn.com/problems/path-sum/
 *
 * 思路：路径问题 -- 前序遍历 + 回溯法
 * 路径的遍历 与 树的遍历 的区别
 *
 *
 */
public class Code112 {

}

// 前序遍历
class Solution112 {
    int sum = 0;
    boolean isTarget;

    public boolean hasPathSum01(TreeNode root, int targetSum) {
        if (root == null) return false;
        sum += root.val;

        if (root.left == null && root.right == null) {
            if (sum == targetSum) return true;
        }
        return false;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        preorder(root, 0, targetSum);
        return isTarget;
    }

    public void preorder(TreeNode node, int cur, int targetSum) {
        cur += node.val;

        if (node.left == null && node.right == null && cur == targetSum) {
            isTarget = true;
            return;
        }

        if (node.left != null) {
            preorder(node.left, cur, targetSum);
        }

        if (node.right != null) {
            preorder(node.right, cur, targetSum);
        }
    }
}

// 前序遍历 + 回溯法 （遍历整棵树） 累加
class Solution112_01 {
    boolean isTarget;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        preorder(root, root.val, targetSum);
        return isTarget;
    }

    public void preorder(TreeNode node, int cur, int targetSum) {

        if (node.left == null && node.right == null && cur == targetSum) {
            isTarget = true;
            return;
        }

        if (node.left != null) {
            cur += node.left.val;
            preorder(node.left, cur, targetSum);
            cur -= node.left.val;
        }

        if (node.right != null) {
            cur += node.right.val;
            preorder(node.right, cur, targetSum);
            cur -= node.right.val;
        }
    }
}

// 前序遍历 + 回溯法 递减 (剪枝：没有遍历整棵树) 找一条路径
class Solution112_02 {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return preorder(root, targetSum - root.val);
    }

    public boolean preorder(TreeNode node, int targetSum) {

        // 叶子节点
        if (node.left == null && node.right == null && targetSum == 0) {
            return true;
        }

        if (node.left == null && node.right == null) {
            return false;
        }

        if (node.left != null) {
            if (preorder(node.left, targetSum - node.left.val)) {
                return true;
            }
        }

        if (node.right != null) {
            if (preorder(node.right, targetSum - node.right.val)) {
                return true;
            }
        }

        return false;
    }
}