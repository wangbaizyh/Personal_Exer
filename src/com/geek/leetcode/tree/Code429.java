package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-05 22:48
 * 429. N 叉树的层序遍历
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * 队列
 *
 */
public class Code429 {

}

class Solution429 {
    public List<List<Integer>> levelOrder(Node01 root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Node01> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                Node01 node = queue.poll();
                list.add(node.val);

                if (node.children == null || node.children.size() == 0) {
                    continue;
                }

                for (Node01 child : node.children) {
                    if (child != null){
                        queue.offer(child);
                    }
                }
            }

            result.add(list);
        }

        return result;
    }
}

class Node01 {
    public int val;
    public List<Node01> children;

    public Node01() {}

    public Node01(int _val) {
        val = _val;
    }

    public Node01(int _val, List<Node01> _children) {
        val = _val;
        children = _children;
    }
}