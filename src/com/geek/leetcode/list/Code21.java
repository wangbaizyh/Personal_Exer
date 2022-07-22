package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-07-16 11:12
 * 21. 合并两个有序链表
 * https://leetcode.cn/problems/merge-two-sorted-lists/
 *
 */
public class Code21 {

}

/**
 * 使用虚拟头节点进行合并
 * 循环遍历两个链表
 */
class Solution21 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        // 遍历两个有序链表
        // 直到一个遍历完
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        // 合并剩下的链表
        cur.next = list1 != null ? list1 : list2;
        return dummyHead.next;
    }
}