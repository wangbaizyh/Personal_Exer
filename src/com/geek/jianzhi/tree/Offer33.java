package com.geek.jianzhi.tree;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-06-17 22:58
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/
 *
 * 思想：回溯、单调递增栈
 *
 *
 */
public class Offer33 {

}


// 回溯
class Solution33 {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }

    private boolean recur(int[] postorder, int i, int j) {
        // 回溯终止条件：子节点数量小于等于以1个
        if (i >= j) return true;

        // 划分左右子树
        // 寻找第一个大于根节点的节点：右子树第一个节点
        int index = i;
        while (postorder[index] < postorder[j]) index++;

        // 判断右子树是否都大于根节点
        int mid = index;
        while (postorder[index] > postorder[j]) index++;

        // 此树正确 && 左子树正确 && 右子数正确
        return index == j && recur(postorder, i, mid - 1) && recur(postorder, mid, j - 1);
    }
}

// 单调栈的定义：从栈底往栈顶
// 辅助单调栈
class Solution33_01 {
    public boolean verifyPostorder(int[] postorder) {
        // 初始化：
        // 单调栈 stack ，父节点值 root = +∞ （单调递增栈）
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;

        for (int i = postorder.length - 1; i >= 0 ; i--) {
            if (postorder[i] > root) return false;

            while (!stack.isEmpty() && stack.peek() > postorder[i]) {
                root  = stack.pop();
            }

            stack.add(postorder[i]);
        }

        return true;
    }
}

