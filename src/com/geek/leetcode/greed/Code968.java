package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-18 21:58
 * 968. 监控二叉树
 * https://leetcode.cn/problems/binary-tree-cameras/
 *
 */
public class Code968 {

}

/**
 *  贪心算法
 *  局部最优：让叶子节点的父节点安摄像头，所用摄像头最少
 *  整体最优：全部摄像头数量所用最少！
 *
 *  思路：从低到上，先给叶子节点父节点放个摄像头，然后隔两个节点放一个摄像头，直至到二叉树头结点。
 *  取左孩子的返回值，右孩子的返回值，即 left 和 right， 以后推导中间节点的状态
 *
 *  节点状态：
 *      该节点无覆盖
 *      本节点有摄像头
 *      本节点有覆盖
 * 三个数字来表示：
 *      0：该节点无覆盖
 *      1：本节点有摄像头
 *      2：本节点有覆盖
 *
 */

// 树形贪心
class Solution968 {
    private int result;

    public int minCameraCover(TreeNode root) {
        result = 0;
        // 根节点无覆盖
        int state = inorder(root);
        if (state == 0) result++;
        return result;

    }

    // 中序遍历
    private int inorder(TreeNode node) {
        // 空节点，该节点有覆盖
        if (node == null) return 2;

        int left = inorder(node.left);
        int right = inorder(node.right);

        // 左右节点都有覆盖
        if (left == 2 && right == 2) return 0;

        // 左右节点有一个没覆盖
        if (left == 0 || right == 0) {
            result++;
            return 1;
        }

        // 左右节点有一个摄像头
        if (left == 1 || right == 1) return 2;

        return -1;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}