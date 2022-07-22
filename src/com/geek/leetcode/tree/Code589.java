package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 12:22
 * 589. N 叉树的前序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 *
 * 递归、栈
 *
 */
public class Code589 {

}

class Solution589 {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    public void dfs(Node node, List<Integer> list) {
        if (node == null) return;

        list.add(node.val);
        if (node.children == null || node.children.size() == 0) {
            return;
        }

        for (Node child : node.children) {
            if (child != null) {
                dfs(child, list);
            }
        }
    }
}


// 标记法
class Solution589_01 {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.peek();

            if (node != null) {
                stack.pop();

                for (int i = node.children.size() - 1; i >= 0; i--) {
                    if (node.children.get(i) != null) {
                        stack.push(node.children.get(i));
                    }

                }

                stack.push(node);
                stack.push(null);
            } else {
                stack.pop();
                node = stack.pop();
                result.add(node.val);
            }
        }

        return result;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}