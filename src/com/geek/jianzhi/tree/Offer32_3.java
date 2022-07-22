package com.geek.jianzhi.tree;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-06-17 22:51
 * 剑指 Offer 32 - III. 从上到下打印二叉树 III
 * https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/
 *
 * 思想：层序遍历
 *
 */
public class Offer32_3 {

}

class Solution32_3 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        queue.offer(root);
        boolean flag = false;

        while (!queue.isEmpty()){
            int len = queue.size();
            List<Integer> list1 = new ArrayList<>();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();
                list1.add(node.val);

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            if (flag) {
                Collections.reverse(list1);
                flag = false;
            } else {
                flag = true;
            }

            list.add(list1);
        }

        return list;
    }
}