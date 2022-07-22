package com.geek.jianzhi.list;

/**
 * @author G.E.E.K.
 * @create 2022-06-09 23:09
 * 剑指 Offer 18. 删除链表的节点
 * https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/
 *
 * 思路：双指针
 *
 */
public class Offer18 {

}

class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        // 删除头节点
        if(head.val == val) return head.next;

        // 其它情况
        ListNode pre  = head;
        ListNode cur = head.next;

        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }

        // 删除节点
        if (cur != null) pre.next = cur.next;

        return head;
    }
}