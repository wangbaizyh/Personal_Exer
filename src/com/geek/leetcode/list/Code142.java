package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 11:30 AM
 * 142. 环形链表 II
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * 快慢指针法、数学题（规律）
 *
 */

public class Code142 {

}

class Solution142 {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        // 快指针走两步
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 有环
            if (slow == fast) {
                // 2 (x + y) = x + y + n (y + z)
                // x + y = n (y + z)
                // x = (n - 1)(y + z) +z

                // 从相遇节点开始：z
                ListNode index1 = fast;
                // 从头节点开始：x
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2){
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }

        return null;
    }
}
