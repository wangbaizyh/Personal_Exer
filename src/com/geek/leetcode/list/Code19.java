package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 2:57 PM
 * 19. 删除链表的倒数第 N 个结点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * 快慢指针法
 *
 * 思路：双指针的经典应用，如果要删除倒数第n个节点，让fast移动n步，然后让fast和slow同时移动，
 * 直到fast指向链表末尾。删掉slow所指向的节点就可以了。
 *      - 使用虚拟头节点
 *
 */

public class Code19 {

}

// 删除的情况，必须使用虚拟头节点，例如只有一个节点的情况
// 双指针：快慢指针
class Solution19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        // 快指针
        ListNode fast = dummyHead;
        // 慢指针
        ListNode slow = dummyHead;

        // 快指针移动
        while (n > 0 && fast != null) {
            fast = fast.next;
            n--;
        }

        // 因为要删除倒数第n个节点，需要再进一步，慢指针指向删除的前一个节点
        fast = fast.next;

        // 一起移动（忽略异常的空指针情况）
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除对应节点
        slow.next = slow.next.next;
        return dummyHead.next;
    }
}

