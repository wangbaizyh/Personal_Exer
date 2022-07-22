package com.geek.jianzhi.list;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-06-13 17:41
 * 剑指 Offer 22. 链表中倒数第k个节点
 * https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/
 *
 * 思路：栈、双指针
 *
 */
public class Offer22 {

}

class Solution22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        Stack<ListNode> stack = new Stack<>();

        while (head != null) {
            stack.push(head);
            head = head.next;
        }

        for (int i = 0; i < k - 1; i++) {
            stack.pop();
        }

        return stack.pop();
    }
}

// 快慢指针法
class Solution22_01 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}