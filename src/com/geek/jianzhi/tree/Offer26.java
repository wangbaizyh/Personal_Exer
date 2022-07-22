package com.geek.jianzhi.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-13 22:00
 * 剑指 Offer 26. 树的子结构
 * https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
 *
 * 思路：子结构和子树的区别
 *
 */
public class Offer26 {

}

// 树的子结构
class Solution26 {
    // 先序遍历
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        return recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean recur(TreeNode A, TreeNode B) {
        // 当节点 B 为空：
        // 说明树 B 已匹配完成（越过叶子节点），因此返回 true ；
        if (B == null) return true;
        // 当节点 A 为空：
        // 说明已经越过树 A 叶子节点，即匹配失败，返回 false ；
        // 当节点 A 和 B 的值不同：说明匹配失败，返回 false ；
        if (A == null || A.val != B.val) return false;

        return recur(A.left, B.left) && recur(A.right, B.right);
    }
}

// 层序遍历 + 后序遍历
// 判断子树
class Solution26_01 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 都为空树
        if (A == null && B == null) return true;
        // 其中一个为空树
        if (A == null || B == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(A);

        boolean ans ;

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {
                TreeNode node = queue.poll();

                // 当前节点值和另一棵树的根节点值相同，进行遍历比较
                if (node.val == B.val) {
                    ans = compare(node, B);
                    // 相同，则存在子树，返回
                    if (ans) return true;
                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }

        // 不是子树
        return false;
    }

    // 判断两棵树是否相等，后序遍历
    public boolean compare(TreeNode A, TreeNode B) {
        // 相等：都为null节点
        if (A == null && B == null) return true;
        // 不相等：一个为null || 节点值不相同
        if (A == null || B == null || A.val != B.val) return false;

        boolean left = compare(A.left, B.left);
        boolean right = compare(A.right, B.right);

        // 节点值相等 && 左右子树相等
        return left && right;
    }
}