package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-05 15:19
 * 145. 二叉树的后序遍历
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * 递归
 *
 * 总结：可解决求最大深度（根节点的高度）、求高度等的问题
 * 需要对返回值进行处理，类似于从底向上访问
 *
 */
public class Code145 {

}

class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        postorder(root, list);
        return list;

    }

    public void postorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        postorder(node.left, list);
        postorder(node.right, list);
        list.add(node.val);
    }
}

// 后序遍历顺序 左-右-中 入栈顺序：中-左-右 出栈顺序：中-右-左， 最后翻转结果
class Solution145_01 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);

            if (node.left != null) {
                stack.push(node.left);
            }

            if (node.right != null) {
                stack.push(node.right);
            }
        }

        Collections.reverse(list);
        return list;
    }
}

// 标记法
class Solution145_02 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();

            if (node != null) {
                stack.pop();
                // 中
                stack.push(node);
                // 中节点访问过，但是还没有处理，加入空节点做为标记。
                stack.push(null);
                // 右
                if (node.right != null) {
                    stack.push(node.right);
                }
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
