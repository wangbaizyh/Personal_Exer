package com.geek.jianzhi.list;

/**
 * @author G.E.E.K.
 * @create 2022-06-13 20:41
 * 剑指 Offer 25. 合并两个排序的链表
 * https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 *
 * 思路：双指针
 *
 */
public class Offer25 {

}

// 使用虚拟头节点
class Solution25 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 初始化
        // 虚拟头节点
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        // 只要有一个为null就退出循环
        // 循环合并
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }

            cur = cur.next;
        }

        // 合并剩余尾部
        cur.next = l1 != null ? l1 : l2;

        return dummyHead.next;
    }
}