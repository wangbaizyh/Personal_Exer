package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 21:47
 * 92. 反转链表 II
 * https://leetcode.cn/problems/reverse-linked-list-ii/
 *
 * 思想：分段翻转
 *
 */
public class Code92 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node = new Solution92().reverseBetween(node1, 2, 4);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}

class Solution92 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 开始节点
        ListNode pre = null;
        ListNode start = dummyHead;
        for (int i = 0; i < left && start.next != null; i++) {
            pre = start;
            start = start.next;
        }

        // 结束节点
        ListNode end = dummyHead;
        for (int i = 0; i < right && end.next != null; i++) {
            end = end.next;
        }

        ListNode next = end.next;
        // 断开链表
        end.next = null;
        // 链接前链表
        pre.next = reverse(start);
        // 链接后链表
        start.next = next;

        return dummyHead.next;
    }

    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode cur = node;
        ListNode next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}