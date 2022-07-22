package com.geek.jianzhi.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-30 17:23
 * 剑指 Offer 06. 从尾到头打印链表
 * https://leetcode.cn/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
 *
 * 思路：反转链表
 *
 */
public class Offer06 {
    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        node1.next = node3;
        node3.next = node2;

        int[] ans = new Solution06_01().reversePrint(node1);
    }

}

// 反转链表 + 输出
class Solution06 {
    public int[] reversePrint(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        cur = pre;
        ArrayList<Integer> list = new ArrayList<>();
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

// 栈
class Solution06_01 {
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();

        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        int size = stack.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = stack.pop();
        }

        return ans;
    }
}

// 递归
class Solution06_02 {
    List<Integer> list = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
        reverse(head);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    private void reverse(ListNode head) {
        if (head == null) return;
        reverse(head.next);
        list.add(head.val);
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}