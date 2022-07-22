package com.geek.jianzhi.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-06-18 22:36
 * 剑指 Offer 35. 复杂链表的复制
 * https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/
 *
 * 思想：哈希表
 *
 */
public class Offer35 {

}

// 哈希表
class Solution35 {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        // 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        // 构建新链表的 next 和 random 指向
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        // 返回新链表的头节点
        return map.get(head);
    }
}

// 拼接 + 拆分
class Solution35_01 {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        Node cur = head;
        // 复制各节点，并构建拼接链表
        while (cur != null) {
            Node temp = new Node(cur.val);
            temp.next = cur.next;
            cur.next = temp;
            cur = temp.next;
        }

        // 构建各新节点的 random 指向
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        // 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }

        // 单独处理原链表尾节点
        pre.next = null;
        // 返回新链表头节点
        return res;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}