package com.geek.jianzhi.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-17 21:28
 * 剑指 Offer 32 - I. 从上到下打印二叉树
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/
 *
 * 思想：层序遍历
 *
 */
public class Offer32_1 {

}

class Solution32_1 {
    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[]{};
        ArrayList<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        while (!queue.isEmpty()){
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}