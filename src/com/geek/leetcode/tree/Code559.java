package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-06 16:16
 * 559. N 叉树的最大深度
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree/
 *
 * 思路：深度问题
 *
 */
public class Code559 {

}

// 后序遍历
class Solution559 {
    public int maxDepth(Node root) {
        return getDepth(root);
    }

    public int getDepth(Node node) {
        if (node == null) return 0;

        int depth = 0;
        // 求所有子树的最大高度
        for (Node child : node.children) {
            depth = Math.max(depth, maxDepth(child));
        }

        // 当前子树的高度
        return depth + 1;
    }
}

// 层次遍历
class Solution559_01 {
    public int maxDepth(Node root) {
        int depth = 0;
        if (root == null) return depth;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        // 遍历整棵树，求最大的层数
        while (!queue.isEmpty()) {
            int len = queue.size();
            depth++;

            for (int i = 0; i < len; i++) {
                Node node = queue.poll();

                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
        }

        return depth;
    }
}

// 前序遍历 -- 回溯
class Solution559_02 {
    // 全局遍历存储最大深度
    int result = 0;

    public int maxDepth(Node root) {
        if (root == null) return result;
        // 根节点深度为：1
        getDepth(root, 1);
        return result;
    }

    public void getDepth(Node node, int depth){
        // 如果当前节点深度 > 最大深度，更新最大深度
        result = Math.max(result, depth);

        // 回溯遍历所有子节点
        for (Node child : node.children) {
            depth++;
            getDepth(child, depth);
            depth--;
        }
    }
}