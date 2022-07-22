package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 12:48
 * 590. N 叉树的后序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 *
 *
 */
public class Code590 {

}

class Solution590 {
    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return result;
    }

    public void dfs(Node node, List<Integer> list) {
        if (node == null) return;

        for (Node child : node.children) {
            if (child != null) {
                dfs(child, list);
            }
        }

        list.add(node.val);
    }
}

// 标记法
class Solution590_01 {
    public List<Integer> postorder(Node root) {
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

                stack.push(node);
                stack.push(null);

                for (int i = node.children.size() - 1; i >= 0; i--) {
                    if (node.children.get(i) != null) {
                        stack.push(node.children.get(i));
                    }
                }

            } else {
                stack.pop();
                node = stack.pop();
                result.add(node.val);
            }
        }

        return result;
    }
}


