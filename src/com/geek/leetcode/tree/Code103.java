package com.geek.leetcode.tree;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 11:16
 * 103. 二叉树的锯齿形层序遍历
 * https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
 */
public class Code103 {

}

class Solution103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean swap = false;

        while (!deque.isEmpty()) {
            int len = deque.size();
            List<Integer> list = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                TreeNode node = deque.poll();
                list.add(node.val);

                if (node.left != null) deque.offer(node.left);
                if (node.right != null) deque.offer(node.right);
            }

            if (swap) {
                Collections.reverse(list);
            }

            swap = !swap;
            result.add(list);
        }

        return result;
    }
}