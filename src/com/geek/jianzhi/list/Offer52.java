package com.geek.jianzhi.list;

/**
 * @author G.E.E.K.
 * @create 2022-06-25 15:10
 * 剑指 Offer 52. 两个链表的第一个公共节点
 * https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/
 *
 * 思路：
 *
 */
public class Offer52 {

}

class Solution52 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0, lenB = 0;
        ListNode curA = headA;
        ListNode curB = headB;

        // 求链表A的长度
        while (curA != null) {
            lenA++;
            curA = curA.next;
        }

        // 求链表B的长度
        while (curB != null) {
            lenB++;
            curB = curB.next;
        }

        curA = headA;
        curB = headB;

        // A 短 B 长
        if (lenA > lenB) {
            int tmpA = lenA;
            lenA = lenB;
            lenB = tmpA;

            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }

        int dif = lenB - lenA;


        for (int i = 0; i < dif; i++) {
            curB = curB.next;
        }

        for (int i = 0; i < lenA; i++) {
            if (curA == curB) return curA;
            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }
}