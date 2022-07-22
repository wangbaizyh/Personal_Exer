package com.geek.leetcode.hashMap.LRU;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-07-06 15:03
 * 146. LRU 缓存
 * https://leetcode.cn/problems/lru-cache/
 *
 * 思路：考察LRU缓存淘汰策略
 * 哈希链表数据结构：LinkedHashMap  双向链表和哈希链表的结合体
 *
 */
public class Code146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}

// 手写LRU算法
// 双向链表的节点类（哈希表节点类）
class Node {
    public int key, value;
    public Node next, pre;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

// 定义双向链表类
class DoubleList {
    // 头尾虚节点
    private final Node head;
    private final Node tail;
    // 链表元素数
    private int size;

    // 初始化双向链表的数据
    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    // 在链表尾部添加节点 x，时间 O(1)
    public void addLast(Node x) {
        x.pre = tail.pre;
        x.next = tail;
        tail.pre.next = x;
        tail.pre = x;
        size++;
    }

    // 删除链表中的 x 节点（x 一定存在）
    // 由于是双链表且给的是目标 Node 节点，时间 O(1)
    public void remove(Node x) {
        x.pre.next = x.next;
        x.next.pre = x.pre;
        size--;
    }

    // 删除链表中第一个节点，并返回该节点，时间 O(1)
    public Node removeFirst() {
        if (head.next == tail) return null;
        Node first = head.next;
        remove(first);
        return first;
    }

    // 返回链表长度，时间 O(1)
    public int size() {
        return size;
    }
}

class LRUCache {
    // key -> Node(key, val)
    private final HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private final DoubleList cache;
    // 最大容量
    private final int cap;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.cache = new DoubleList();
        this.cap = capacity;
    }

    // 封装工具方法

    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(int key) {
        Node x = map.get(key);
        // 先从链表中删除这个节点
        cache.remove(x);
        // 重新插到队尾
        cache.addLast(x);
    }

    /* 添加最近使用的元素 */
    private void addRecently(int key, int value) {
        Node x = new Node(key, value);
        // 链表尾部就是最近使用的元素
        cache.addLast(x);
        // 别忘了在 map 中添加 key 的映射
        map.put(key, x);
    }

    /* 删除某一个 key */
    private void deleteKey(int key) {
        Node x = map.get(key);
        // 从链表中删除
        cache.remove(x);
        // 从 map 中删除
        map.remove(key);
    }

    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node deleteNode = cache.removeFirst();
        // 同时别忘了从 map 中删除它的 key
        map.remove(deleteNode.key);
    }

    // 访问数据，提到链表尾部
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        makeRecently(key);
        return map.get(key).value;
    }

    // 添加数据
    public void put(int key, int value) {
        // 数据存在，改变到链表尾部
        if (map.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, value);
        // 数据不存在，插入到链表尾部
        } else {
            // 缓存满了，执行淘汰
            if (cap == cache.size()) {
                // 删除最近最久未使用的元素
                removeLeastRecently();
            }
            // 添加为最近使用的元素
            addRecently(key, value);
        }
    }
}

class LRUCache01 {
    // 最大容量
    private int cap;
    // 哈希链表
    private LinkedHashMap<Integer,Integer> cache;

    public LRUCache01(int capacity) {
        this.cap = capacity;
        this.cache = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // 修改 key 的值
            cache.put(key, value);
            // 将 key 变为最近使用
            makeRecently(key);
        } else {
            if (cap == cache.size()) {
                // 链表头部就是最久未使用的 key
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            // 将新的 key 添加链表尾部
            cache.put(key, value);
        }
    }

    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(int key) {
        int value = cache.get(key);
        // 删除 key，重新插入到队尾
        cache.remove(key);
        cache.put(key, value);
    }
}

// 继承哈希链表
class LRUCache02 extends LinkedHashMap<Integer, Integer> {
    // 最大容量
    private final int cap;

    public LRUCache02(int capacity) {
        super(capacity, 0.75F, true);
        this.cap = capacity;
    }

    public int get(int key) {
        if (!super.containsKey(key)) {
            return -1;
        }

        return super.get(key);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > cap;
    }
}
