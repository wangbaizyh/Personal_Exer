package com.geek.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 15:05
 * 98.验证二叉搜索树
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * 思路：中序遍历下，输出的二叉搜索树节点的数值是有序序列。
 * 二叉搜索树中不能有重复元素。
 *
 */
public class Code98 {

}

// 中序遍历转换为列表
class Solution98 {
    private List<Integer> list = new ArrayList<>();

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        list.add(node.val);
        inorder(node.right);
    }


    public boolean isValidBST(TreeNode root) {
        inorder(root);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) return false;
        }

        return true;
    }
}

// 中序遍历 -- 寻找不符合条件的路径（寻找一条路径 需要返回值）
class Solution98_01 {
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;

        boolean left = isValidBST(root.left);

        if (pre != null && pre.val >= root.val) return false;
        pre = root;

        boolean right = isValidBST(root.right);

        return left && right;
    }
}