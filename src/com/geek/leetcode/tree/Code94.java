package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-05 12:10
 * 94. 二叉树的中序遍历
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * 递归、栈（标记法、空节点）
 *
 */
public class Code94 {

}

// 递归
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    // 中序遍历，不用返回值，更新列表
    public void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}

// 中序遍历顺序: 左-中-右 入栈顺序： 左-右
class Solution94_01 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        // 指针法
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            // 指针来访问节点，访问到最底层
            if (cur != null) {
                // 将访问的节点放进栈
                stack.push(cur);
                // 左节点
                cur = cur.left;
            } else {
                // 从栈里弹出的数据，就是要处理的数据（放进result数组里的数据）
                cur = stack.pop();
                // 中间节点
                list.add(cur.val);
                // 右节点
                cur = cur.right;
            }
        }

        return list;
    }
}

// 迭代法
// 标记法（空节点）
class Solution94_02 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        // 空节点不如队列
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node != null) {
                stack.pop();
                // 右
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 中
                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);
                // 左
                if (node.left != null) {
                    stack.push(node.left);
                }
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