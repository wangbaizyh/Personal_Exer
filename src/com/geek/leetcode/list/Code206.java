package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 2:23 PM
 * 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * 链表、双指针、递归
 *
 * 思路：
 *      1、双指针：前节点（更新）、当前节点（更新）、下一个节点（临时）
 */

public class Code206 {

}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

// 双指针
class Solution206_01 {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            // 保存下一个节点
            tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }

        return pre;
    }
}

// 递归
class Solution206_02 {
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }

        ListNode tmp = cur.next;
        cur.next = prev;
        return reverse(cur, tmp);
    }
}

// 从后向前递归
class Solution206_03 {
    public ListNode reverseList(ListNode head) {
        // 边缘条件判断
        // 传入空链表的情况
        if (head == null) return null;
        // 从最后两个解节点开始反转
        if (head.next == null) return head;

        // 递归调用，翻转第二个节点开始往后的链表
        // last是最后一个节点
        ListNode last = reverseList(head.next);
        // 翻转头节点与第二个节点的指向（反转指针方向）
        head.next.next = head;
        // 此时的 head 节点为尾节点，next 需要指向 NULL
        head.next = null;
        return last;
    }
}
