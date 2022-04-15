package com.geek.leetcode.list;

/**
 * @author G.E.E.K.
 * @create 2022-04-15 15:48
 * 707. 设计链表
 * https://leetcode-cn.com/problems/design-linked-list/
 *
 * 题解：
 * 1.注意新添加一个ListNode结构体来表示链表的节点
 * 2.使用虚拟头节点和尾节点的方法
 *      -查找的时候需要多找一个节点，也就是index+1
 *      -临界条件：链表index的最大值 = size - 1 ， 也就是 index>=size || index < 0 不合法
 *      -单链表的头尾插入操作可以解耦到在index位置插入，头：index=0 尾：index=size
 *      -插入、删除的时候，找前一个节点，因此只找到index
 */
public class Code707 {

}

// 单链表
class MyLinkedList {
    // 链表节点
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    // size存储链表元素的个数，虚拟头节点不算在内
    int size;
    // 虚拟头结点
    ListNode head;

    // 初始化链表
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    // 获取第index个节点的数值
    public int get(int index) {
        // 如果index非法，返回-1  size-1:最后一个节点
        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode currentNode = head;
        // 包含一个虚拟头节点，所以查找第 index+1 个节点
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }

        return currentNode.val;
    }

    // 在链表最前面插入一个节点
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    // 在链表的最后插入一个节点
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }


    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index < 0) {
            index = 0;
        }

        size++;
        // 找到前一个节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        ListNode add = new ListNode(val);
        add.next = pre.next;
        pre.next = add;
    }

    // 删除第index个节点
    public void deleteAtIndex(int index) {
        // 无效索引，index=size-1的时候为最后一个节点
        if (index < 0 || index >= size) {
            return;
        }

        size--;
        // 找到前一个节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }
}

// 双链表
class MyLinkedList01 {
    static class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }
    }

    // size存储链表元素的个数
    int size;
    // 虚拟头结点和虚拟尾节点
    ListNode head,tail;

    // 初始化链表
    public MyLinkedList01() {
        size = 0;
        head = new ListNode(0);
        tail = new ListNode(0);
        head.next = tail;
        tail.prev = head;
    }

    // 获取第index个节点的数值
    public int get(int index) {
        // 如果index非法，返回-1  size-1:最后一个节点
        if (index < 0 || index >= size) {
            return -1;
        }

        ListNode currentNode = head;
        // 通过判断 index < (size - 1) / 2 来决定是从头结点还是尾节点遍历，提高效率
        if (index < ((size - 1) >> 1)) {
            for (int i = 0; i <= index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            // 从尾节点遍历
            currentNode = tail;
            for (int i = 0; i <= size - index - 1; i++) {
                currentNode = currentNode.prev;
            }
        }

        return currentNode.val;
    }

    // 在链表最前面插入一个节点
    public void addAtHead(int val) {
        ListNode cur = head;
        ListNode newNode = new ListNode(val);

        newNode.next = cur.next;
        newNode.prev = cur;
        cur.next.prev = newNode;
        cur.next = newNode;

        size++;
    }

    // 在链表的最后插入一个节点
    public void addAtTail(int val) {
        ListNode cur = tail;
        ListNode newNode = new ListNode(val);

        newNode.next = cur;
        newNode.prev = cur.prev;
        cur.prev.next = newNode;
        cur.prev = newNode;
        size++;
    }


    // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
    // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
    // 如果 index 大于链表的长度，则返回空
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index < 0) {
            index = 0;
        }

        size++;
        // 找到前一个节点
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        ListNode add = new ListNode(val);
        add.next = pre.next;
        add.prev = pre;
        pre.next.prev = add;
        pre.next = add;
    }

    // 删除第index个节点
    public void deleteAtIndex(int index) {
        // 无效索引
        if (index < 0 || index >= size) {
            return;
        }

        size--;
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

        pre.next = pre.next.next;
        pre.next.prev = pre;
    }
}
