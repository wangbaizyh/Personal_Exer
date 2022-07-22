package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 21:18
 * 257. 二叉树的所有路径
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * 求路径：前序遍历 + 回溯法
 *
 */
public class Code257 {

}

// 前序遍历 + 回溯法
class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        // 使用List存放路径，方便回溯删除
        List<Integer> paths = new ArrayList<>();
        // 前序遍历
        preorder(root, paths, result);
        return result;
    }

    private void preorder(TreeNode root, List<Integer> paths, List<String> result) {
        // 当前节点加入路径（路径问题）
        paths.add(root.val);

        // 递归的结束条件：叶子节点
        if (root.left == null && root.right == null) {
            // 拼接路径
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");
            }
            sb.append(paths.get(paths.size() - 1));
            result.add(sb.toString());
            return;
        }

        // 列表需要回溯
        if (root.left != null) {
            preorder(root.left, paths, result);
            paths.remove(paths.size() - 1);         // 回溯
        }

        if (root.right != null) {
            preorder(root.right, paths, result);
            paths.remove(paths.size() - 1);         // 回溯
        }
    }
}

// 迭代法、前序遍历
class Solution257_01 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;

        Stack<Object> stack = new Stack<>();
        // 节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");

        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            // 若找到叶子节点
            if (node.left == null && node.right == null) {
                result.add(path);
            }

            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }

            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }

        return result;
    }
}

// 统一迭代法
class Solution257_02 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        Stack<Object> stack = new Stack<>();
        // 节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");

        while (!stack.isEmpty()) {
            // 节点和路径同时出栈
            String path = (String) stack.pop();
            Object node = stack.peek();
            if (path != null) {
                TreeNode cur = (TreeNode) node;
                stack.pop();

                if (cur.right != null) {
                    stack.push(cur.right);
                    stack.push(path + "->" + cur.right.val);
                }

                if (cur.left != null) {
                    stack.push(cur.left);
                    stack.push(path + "->" + cur.left.val);
                }

                stack.push(cur);
                stack.push(path);
                stack.push(null);

            } else {
                path = (String) stack.pop();
                TreeNode cur = (TreeNode) stack.pop();
                // 若找到叶子节点
                if (cur.left == null && cur.right == null) {
                    result.add(path);
                }
            }
        }

        return result;
    }
}