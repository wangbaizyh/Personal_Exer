package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-07 20:54
 * 113. 路径总和 II
 * https://leetcode-cn.com/problems/path-sum-ii/
 *
 *
 */
public class Code113 {

}

// 遍历整个树，找到所有路径，所以递归函数不要返回值！
class Solution113 {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<Integer> list = new ArrayList<>();
        list.add(root.val);
        preorder(root, targetSum - root.val, list, result);
        return result;
    }

    public void preorder(TreeNode node, int targetSum, List<Integer> list, List<List<Integer>> result) {

        // 叶子节点
        if (node.left == null && node.right == null && targetSum == 0) {
            // 必须新创建一个对象，否则指向的是同一个对象
            result.add(new ArrayList<>(list));
            return;
        }

        if (node.left != null) {
            list.add(node.left.val);
            preorder(node.left, targetSum - node.left.val, list, result);
            list.remove(list.size() - 1);
        }

        if (node.right != null) {
            list.add(node.right.val);
            preorder(node.right, targetSum - node.right.val, list, result);
            list.remove(list.size() - 1);
        }
    }
}