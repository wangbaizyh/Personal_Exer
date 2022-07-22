package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 22:13
 * 236. 二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 *
 * 总结：
 * 求最小公共祖先，需要从底向上遍历，那么二叉树，只能通过后序遍历（即：回溯）实现从低向上的遍历方式。
 * 在回溯的过程中，必然要遍历整棵二叉树，即使已经找到结果了，依然要把其他节点遍历完，因为要使用递归函数的返回值（也就是代码中的left和right）做逻辑判断。
 * 要理解如果返回值left为空，right不为空为什么要返回right，为什么可以用返回right传给上一层结果。
 */
public class Code236 {

}

// 遍历整棵二叉树
// 后序遍历 + 回溯
class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == q || root == p || root == null) return root;

        // 后序遍历
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 若找到两个节点，返回当前节点
        if (left != null && right != null) return root;
        // 只找到一个节点，返回找到的节点
        else if (left == null && right != null) return right;
        else if (left != null && right == null) return left;
        // 若未找到节点 p 或 q，返回null
        else return null;
    }
}