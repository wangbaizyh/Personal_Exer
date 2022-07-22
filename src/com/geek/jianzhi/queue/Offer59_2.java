package com.geek.jianzhi.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 23:08
 * 剑指 Offer 59 - II. 队列的最大值
 * https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/
 *
 * 思路：辅助单调队列 --> 单调递减队列（从大到小）
 *
 */
public class Offer59_2 {

}

class MaxQueue {
    // 保存所有递减元素
    Deque<Integer> deque;
    // 原始队列（类似于数组）
    Queue<Integer> queue;

    public MaxQueue() {
        this.deque = new LinkedList<>();
        this.queue = new LinkedList<>();
    }

    // 最大值
    public int max_value() {
        if (deque.isEmpty()) return -1;
        else return deque.peekFirst();
    }

    public void push_back(int value) {
        // 入队
        queue.offer(value);
        // 单调队列入队
        while (!deque.isEmpty() && value > deque.peekLast()) {
            deque.pollLast();
        }

        deque.offerLast(value);
    }

    // 出队
    public int pop_front() {
        if (queue.isEmpty()) return -1;

        int ans = queue.poll();

        if (!deque.isEmpty() && ans == deque.peekFirst()) {
            deque.pollFirst();
        }

        return ans;
    }
}