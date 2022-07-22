package com.geek.leetcode.list;

import java.util.HashSet;

/**
 * @author G.E.E.K.
 * @create 2022-07-12 11:20
 * 141. 环形链表
 * https://leetcode.cn/problems/linked-list-cycle/
 *
 * 思路：模拟
 *
 */
public class Code141 {

}

// 哈希表去重
class Solution141 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        HashSet<ListNode> set = new HashSet<>();

        while (head != null) {
            if (set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }

        return false;
    }
}

/**
 * 快慢双指针
 * 快指针速度为2 慢指针速度为1 也就是 快指针追赶慢指针
 */
class Solution141_1 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }

        return false;
    }
}