package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 2:15 PM
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * 链表、模拟
 *
 * 思路：使用虚拟头节点，画图模拟。
 *      模拟链表交换的过程
 */

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
            // 存储第一个节点
            ListNode tmp = cur.next;
            // 存储第三个节点
            ListNode tmp1 = cur.next.next.next;

            // 第一步：下一个节点指向第二个节点
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
