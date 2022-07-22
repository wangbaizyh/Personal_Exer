package com.geek.jianzhi;

/**
 * @author G.E.E.K.
 * @create 2022-06-20 21:30
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 *
 * 思路：
 *
 *
 */
public class Offer36 {

}

class Solution36 {
    Node pre, head;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(Node cur) {
        if (cur == null) return;
        inorder(cur.left);
        if (pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        inorder(cur.right);
    }
}

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};