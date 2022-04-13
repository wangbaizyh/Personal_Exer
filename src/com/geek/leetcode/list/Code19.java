package com.geek.leetcode.list;

public class Code19 {

}

// 双指针
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 快指针
        ListNode fast = dummyHead;
        // 慢指针
        ListNode slow = dummyHead;

        // 快指针移动
        while (n-- > 0 && fast != null) {
            fast = fast.next;
        }

        // 一起移动（忽略异常的空指针情况）
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除对应节点
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}
