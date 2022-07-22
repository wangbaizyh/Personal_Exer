package com.geek.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-05-07 12:09
 * 513. 找树左下角的值
 * https://leetcode-cn.com/problems/find-bottom-left-tree-value/
 *
 * 思路：题目意思 -> 二叉树至少有一个节点
 *
 *
 */
public class Code513 {

}

// 层序遍历
class Solution513 {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();
            // 标记该层访问的第一个节点
            boolean flag = true;

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                // 获取该层第一个节点的值
                if (flag) {
                    result = node.val;
                    flag = false;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        // 遍历完整棵树后，返回的便是最后一层的第一个节点
        // 也就是最底层最左边的节点
        return result;
    }
}

// 前序遍历 + 回溯
// 递归函数遍历整个树，则不能有返回值，遍历某一条固定路线，则需要返回值。
// 此问题遍历整棵树
class Solution513_01 {
    // 使用全局变量来存储最大深度和最左叶子节点
    // 初始化最大深度为0
    int maxLen = 0;
    int maxLeftValue;


    public int findBottomLeftValue(TreeNode root) {
        // 初始深度为：1
        preorder(root,1);
        return maxLeftValue;
    }

    // 前序遍历
    // 循环的目的：遍历整棵树，找到最底层最左边的节点
    private void preorder(TreeNode node, int leftLen) {
        // 中：叶子节点（只在叶子节点更新）
        if (node.left == null && node.right == null) {
            // 叶子节点深度 > 最大深度， 更新最大深度和最左边的节点，返回
            if (leftLen > maxLen){
                maxLen = leftLen;
                maxLeftValue = node.val;
            }
            return;
        }

        // 左：遍历左子树，并回溯深度
        if (node.left != null) {
            leftLen++;
            preorder(node.left, leftLen);
            leftLen--;
        }

        // 右：遍历右子树，并回溯深度
        if (node.right != null) {
            leftLen++;
            preorder(node.right, leftLen);
            leftLen--;
        }
    }
}