package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-05 11:53
 * 144. 二叉树的前序遍历
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * 递归、栈
 *
 * 总结：前序遍历可以和回溯法相结合，对于求深度的问题
 * 此时需要注意递归函数需要传入需要回溯的值，比如初始深度
 * 需要定义全局变量来比较和存储最终结果
 *
 */
public class Code144 {

}

class Solution144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        preorder(root,list);
        return list;
    }

    public void preorder(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);
        preorder(root.left, result);
        preorder(root.right, result);
    }
}

// 前序遍历顺序：中-左-右，入栈顺序：中-右-左
class Solution144_01 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return list;
    }
}

// 标记法 (空节点)
class Solution144_02 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node != null) {
                // 将该节点弹出，避免重复操作，下面再将右中左节点添加到栈中
                stack.pop();
                // 右
                if (node.right != null) {
                    stack.push(node.right);
                }

                // 左
                if (node.left != null) {
                    stack.push(node.left);
                }

                // 中
                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);
            } else {
                // 只有遇到空节点的时候，才将下一个节点放进结果集
                // 将空节点弹出
                stack.pop();
                // 重新取出栈中元素
                node = stack.pop();
                list.add(node.val);
            }
        }

        return list;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}