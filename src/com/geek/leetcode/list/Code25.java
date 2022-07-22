package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-07-07 16:18
 * 25. K 个一组翻转链表
 * https://leetcode.cn/problems/reverse-nodes-in-k-group/
 *
 * 思路：分段 + 翻转链表
 *
 */
public class Code25 {

}

class Solution25 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 前置节点
        ListNode pre = dummyHead;
        // 后置节点
        ListNode end = dummyHead;

        // 翻转到最后一组结束停止
        while (end.next != null) {
            // 寻找要翻转的一组节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            // 最后一组节点数小于k，不翻转
            if (end == null) break;
            // 下一段开始节点
            ListNode next = end.next;
            // 断开链表
            end.next = null;
            // 翻转开始节点
            ListNode start = pre.next;
            // 反转链表, 拼接上一段节点
            pre.next = reverse(start);
            // 断开链表重新连接 （翻转后的最后一个节点）
            start.next = next;
            // 翻转结束, 重置start
            pre = start;
            // 翻转结束, 重置end
            end = start;
        }

        return dummyHead.next;
    }

    // 反转链表的实现
    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
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