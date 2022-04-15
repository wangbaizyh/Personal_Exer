package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-13 11:23 PM
 * 面试题 02.07. 链表相交
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists-lcci/
 *
 * 思路：求两个链表交点节点的指针。
 *      - 先求两个链表的长度
 *      - 让curA为最长链表的头，lenA为其长度
 *      - 求长度差
 *      - 让curA和curB在同一起点上（末尾位置对齐）
 *      - 遍历curA 和 curB，遇到相同则直接返回
 */

public class Code02_07 {

}

public class Solution02_07 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lenA = 0,lenB = 0;

        // 求链表A的长度
        while (curA != null) {
            curA = curA.next;
            lenA++;
        }

        // 求链表B的长度
        while (curB != null){
            curB = curB.next;
            lenB++;
        }

        curA = headA;
        curB = headB;

        // 让curA为最长链表的头，lenA为其长度
        if (lenB > lenA){
            int tmpA = lenA;
            lenA = lenB;
            lenB = tmpA;

            ListNode tmpNode = curA;
            curA = curB;
            curB = tmpNode;
        }

        // 求长度差
        int gap = lenA - lenB;

        // 让curA和curB在同一起点上（末尾位置对齐）
        while (gap-- > 0){
            curA = curA.next;
        }

        // 遍历curA 和 curB，遇到相同则直接返回
        while (curA != null) {
            if (curA == curB) {
                return curA;
            }

            curA = curA.next;
            curB = curB.next;
        }

        return null;
    }
}