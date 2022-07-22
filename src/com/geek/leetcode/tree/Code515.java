package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-05 23:05
 * 515. 在每个树行中找最大值
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 *
 * 队列
 *
 */
public class Code515 {

}

class Solution515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                if (node.val > max) {
                    max = node.val;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(max);
        }

        return result;
    }
}
