package com.geek.leetcode.list;

public class Code24 {

}

class Solution24 {
    public ListNode swapPairs(ListNode head) {
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode cur = dummyHead;
        // 遍历链表
        while (cur.next != null && cur.next.next != null) {
            // 存储下一个节点
            ListNode tmp = cur.next;
            // 存储第三个节点
            ListNode tmp1 = cur.next.next.next;

            // 第一步：下一个节点指向第三个
            cur.next = cur.next.next;
            // 第二步：第二个节点指向第一个，实现交换
            cur.next.next = tmp;
            // 第三步：第一个节点指向原来的第三个节点
            cur.next.next.next = tmp1;

            // 将当前节点赋值为第一个节点，cur移动两位，准备下一轮交换
            cur = cur.next.next;
        }

        // 返回头节点
        return dummyHead.next;
    }
}
