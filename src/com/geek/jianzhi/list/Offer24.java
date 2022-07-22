package com.geek.jianzhi.list;

/**
 * @author G.E.E.K.
 * @create 2022-06-13 20:33
 * 剑指 Offer 24. 反转链表
 * https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/
 *
 * 思路：双指针
 *
 */
public class Offer24 {

}

// 双指针
class Solution24 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp;

        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }
}