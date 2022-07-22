package com.geek.leetcode.list;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-07-16 11:42
 * 23. 合并K个升序链表
 * https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * 思路
 */
public class Code23 {

}

/**
 * 顺序合并
 * 两两逐一合并K个有序链表
 * 时间复杂度（k^2*n）
 */
class Solution23 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode cur = null;
        // 循环两两合并所有的有序链表
        for (ListNode list : lists) {
            cur = mergeTwoLists(cur, list);
        }

        return cur;
    }
    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;

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
        cur.next = list1 != null ? list1 : list2;
        
        return dummyHead.next;
    }
}

/**
 * 分治合并（归并排序）
 * 对顺序合并进行优化
 * 时间复杂度 O（k*n * log k）
 * 空间复杂度 O（log k）
 */
class Solution23_1 {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    // 归并合并
    private ListNode merge(ListNode[] lists, int begin, int end){
        // 临界条件
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return lists[begin];
        }
        // 归并合并
        int mid = begin + ((end - begin) >> 1);
        return mergeTwoLists(merge(lists, begin, mid), merge(lists, mid + 1, end));
    }


    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;

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
        cur.next = list1 != null ? list1 : list2;

        return dummyHead.next;
    }
}

/**
 * 优先队列（小根堆）
 * 使用大小为k的小根堆存储k个链表，依次弹出最小的元素进行合并
 */
class Solution23_2 {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        // 根据节点值排序的小根堆
        Queue<ListNode> queue = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));
        // Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        // k个链表入堆
        for (ListNode list : lists) {
            // 非空则入队列
            if (list != null) queue.offer(list);
        }
        // 遍历所有的有序链表
        while (!queue.isEmpty()) {
            // 弹出最小的节点所在的链表
            ListNode node = queue.poll();
            // 合并链表
            cur.next = node;
            cur = cur.next;
            // 如果该链表未遍历完，放回队列中。
            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return dummyHead.next;
    }
}
