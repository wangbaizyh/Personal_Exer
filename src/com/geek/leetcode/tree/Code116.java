package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-05 23:12
 * 116. 填充每个节点的下一个右侧节点指针
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 *
 * 层次遍历模板题
 *
 */
public class Code116 {

}

class Solution116 {
    public Node02 connect(Node02 root) {
        Queue<Node02> queue = new LinkedList<>();

        if (root == null) {
            return null;
        }
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                // 从当前层第一个节点到倒数第二个节点，next指针指向该层下一个节点
                Node02 cur = queue.poll();
                // 最后一个节点不处理，默认指向null
                if (i < len - 1) {
                    cur.next = queue.peek();
                }

                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }

        return root;
    }
}

class Node02 {
    public int val;
    public Node02 left;
    public Node02 right;
    public Node02 next;

    public Node02() {}

    public Node02(int _val) {
        val = _val;
    }

    public Node02(int _val, Node02 _left, Node02 _right, Node02 _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}