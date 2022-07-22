package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-07-15 10:16
 * 160. 相交链表
 * https://leetcode.cn/problems/intersection-of-two-linked-lists/
 *
 */
public class Code160 {

}

/**
 * 链表操作问题
 */
class Solution160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;

        int lenA = 0, lenB = 0;

        while (curA != null) {
            curA = curA.next;
            lenA++;
        }

        while (curB != null) {
            curB = curB.next;
            lenB++;
        }

        if (lenA > lenB) {
            int tmp = lenA;
            lenA = lenB;
            lenB = tmp;

            ListNode tmpA = headA;
            headA = headB;
            headB = tmpA;
        }

        int diff = lenB - lenA;

        for (int i = 0; i < diff; i++) {
            headB = headB.next;
        }

        for (int i = 0; i < lenA; i++) {
            if (headA == headB) return headA;
            headA = headA.next;
            headB = headB.next;
        }

        return null;
    }
}