package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-07-17 14:15
 * 143. 重排链表
 * https://leetcode.cn/problems/reorder-list/
 *
 */
public class Code143 {

}

/**
 * 链表操作的题目
 */
class Solution143 {
    public void reorderList(ListNode head) {
        // 首先遍历链表，计算出链表的长度
        int count = 0;
        ListNode cur = head;
        while (cur != null) {
            count++;
            cur = cur.next;
        }
        // 算出长度中值，分割两段
        int mid = (count + 1) / 2;
        // 指向前一段末尾
        cur = head;
        for (int i = 0; i < mid - 1; i++) {
            cur = cur.next;
        }
        // 保存后一段开始
        ListNode next = cur.next;
        // 断开链表
        cur.next = null;
        // 翻转链表
        ListNode start = reverse(next);
        // 交错插入链表
        cur = head;
        while (cur != null) {
            ListNode tmpCur = cur.next;
            cur.next = start;
            // 第二段翻转链表不为空
            if (start != null) {
                ListNode tmpStart = start.next;
                start.next = tmpCur;
                start = tmpStart;
            }

            cur = tmpCur;
        }
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