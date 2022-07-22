package com.geek.jianzhi.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-06-18 22:14
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/
 *
 * 思路：回溯
 *
 */
public class Offer34 {

}

class Solution34 {
    List<List<Integer>> ans;
    int target;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) return new ArrayList<>();

        this.ans = new ArrayList<>();
        this.target = target;
        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        preorder(root, root.val, list);

        return ans;
    }

    private void preorder(TreeNode node, int sum, List<Integer> list) {

        // 叶子节点
        if (node.left == null && node.right == null && sum == target) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (node.left != null) {
            list.add(node.left.val);
            preorder(node.left, sum + node.left.val, list);
            list.remove(list.size() - 1);
        }

        if (node.right != null) {
            list.add(node.right.val);
            preorder(node.right, sum + node.right.val, list);
            list.remove(list.size() - 1);
        }
    }
}