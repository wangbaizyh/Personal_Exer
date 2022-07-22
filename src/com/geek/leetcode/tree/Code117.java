package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 10:41
 * 117. 填充每个节点的下一个右侧节点指针 II
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 * 队列
 *
 */
public class Code117 {

}

class Solution117 {
    public Node02 connect(Node02 root) {
        Queue<Node02> queue = new LinkedList<>();

        if (root == null) {
            return null;
        }

        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                Node02 cur = queue.poll();

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
