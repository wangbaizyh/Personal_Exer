package com.geek.leetcode.hashMap;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-06-30 15:59
 * 706. 设计哈希映射
 * https://leetcode.cn/problems/design-hashmap/
 *
 */
public class Code706 {

}

// 数组解法
class MyHashMap01 {
    private static final int N = 1000001;
    int[] map;

    public MyHashMap01() {
        this.map = new int[N];
        Arrays.fill(map, Integer.MAX_VALUE);
    }

    public void put(int key, int value) {
        map[key] = value;
    }

    public int get(int key) {
        return map[key] == Integer.MAX_VALUE ? -1 : map[key];
    }

    public void remove(int key) {
        map[key] = Integer.MAX_VALUE;
    }
}

// 链表解法
// 用链表解决哈希冲突
// 拉链法
// 由于没有扩容的逻辑，最坏情况下复杂度为 O(n)
// 数组 + 链表
class MyHashMap02 {
    // 节点类
    static class Node {
        int key, value;
        Node next;
        Node(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    // 由于使用的是「链表」，这个值可以取得很小
    private static final int N = 10001;
    Node[] nodes;

    public MyHashMap02() {
        this.nodes = new Node[N];
    }

    // 放入键值对
    public void put(int key, int value) {
        // 根据 key 获取哈希桶的位置
        int idx = getIndex(key);
        // 判断链表中是否已经存在
        Node loc = nodes[idx], cur = loc;
        // 当前哈希桶不为空
        if (loc != null) {
            Node pre = null;
            // 遍历哈希桶，寻找是否存在键值对
            while (cur != null) {
                // 存在key，覆盖值
                if (cur.key == key) {
                    cur.value = value;
                    return;
                }

                pre = cur;
                cur = cur.next;
            }
            cur = pre;
        }

        Node node = new Node(key, value);

        // 头插法
        // node.next = loc;
        // nodes[idx] = node;

        // 尾插法
        if (cur != null) {
             cur.next = node;
        } else {
            nodes[idx] = node;
        }

    }

    // 取出值
    public int get(int key) {
        // 桶下标
        int index = getIndex(key);
        // 遍历当前哈希桶
        Node cur = nodes[index];
        if (cur != null) {
            while (cur != null) {
                if (cur.key == key) {
                    return cur.value;
                }
                cur = cur.next;
            }
        }

        return  -1;
    }

    // 删除键值对
    public void remove(int key) {
        // 桶下标
        int index = getIndex(key);
        Node cur = nodes[index];
        // 虚拟头节点
        Node dummyHead = new Node(-1, -1);
        dummyHead.next = cur;
        Node pre = dummyHead;

        // 桶不为空
        if (cur != null) {
            while (cur != null) {
                if (cur.key == key) {
                    pre.next = cur.next;
                    nodes[index] = dummyHead.next;
                    return;
                }

                pre = cur;
                cur = cur.next;
            }
        }
    }

    // 寻找哈希桶的位置，确定桶下标
    private int getIndex(int key) {
        // 因为 nodes 的长度只有 10001
        // 为了让 key 对应的 hash 高位也参与运算，这里对 hashCode 进行右移异或
        // 使得 hashCode 的高位随机性和低位随机性都能体现在低 16 位中
        int hash = Integer.hashCode(key);
        hash ^= (hash >>> 16);
        return hash % nodes.length;
    }
}

// 开放寻址法
class MyHashMap03 {
    static class Node {
        int key, value;
        Node next;
        Node(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }

    // 冲突时的偏移量
    int OFFSET = 1;
    private static final int N = 10001;
    Node[] nodes;

    public MyHashMap03() {
        this.nodes = new Node[N];
    }

    public void put(int key, int value) {
        // 根据 key 获取哈希桶的位置
        int idx = getIndex(key);
        // 判断链表中是否已经存在
        Node node = nodes[idx];
        if (node != null) {
            node.value = value;
        } else {
            node = new Node(key, value);
            nodes[idx] = node;
        }
    }

    public int get(int key) {
        int index = getIndex(key);
        Node node = nodes[index];
        if (node == null) return -1;
        return node.value;
    }

    public void remove(int key) {
        int index = getIndex(key);
        Node node = nodes[index];
        if (node != null) {
            nodes[index] = null;
        }
    }

    // 寻找哈希桶的位置
    // 当 map 中没有 key 的时候，getIndex 总是返回一个空位置
    // 当 map 中包含 key 的时候，getIndex 总是返回 key 所在的位置
    private int getIndex(int key) {
        // 因为 nodes 的长度只有 10001
        // 为了让 key 对应的 hash 高位也参与运算，这里对 hashCode 进行右移异或
        // 使得 hashCode 的高位随机性和低位随机性都能体现在低 16 位中
        int hash = Integer.hashCode(key);
        hash ^= (hash >>> 16);
        int index = hash % N;
        while (nodes[index] != null && nodes[index].key != key) {
            hash += OFFSET;
            index = hash % N;
        }

        return hash % N;
    }
}