package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 15:36
 * 203. 移除链表元素
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 *
 * 链表
 *
 * 思路：虚拟头节点
 *
 */
public class Code203 {

}

class Solution203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;

        while (cur.next != null){
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return dummyHead.next;
    }
}
