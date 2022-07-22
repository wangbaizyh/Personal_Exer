package com.geek.leetcode.tree;

/**
 * @author G.E.E.K.
 * @create 2022-05-09 10:46
 * 235. 二叉搜索树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * 思路：二叉搜索树有序，因此可以从上往下顺序遍历，使用前序遍历
 *
 */
public class Code235 {

}

// 寻找一条路径
class Solution235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return preorder(root, p, q);
    }

    private TreeNode preorder(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        // 左闭右闭
        if ((root.val >= p.val && root.val <= q.val) || (root.val >= q.val && root.val <= p.val)) return root;

        if (root.val > p.val) {
            return preorder(root.left, p, q);
        }

        return preorder(root.right, p, q);
    }
}

class Solution235_01 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else return root;
    }
}

// 迭代法
class Solution235_02 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else return root;
        }

        return null;
    }
}