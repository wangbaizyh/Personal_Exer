package com.geek.jianzhi.queue;

import java.util.PriorityQueue;

/**
 * @author G.E.E.K.
 * @create 2022-05-04 14:03
 * 剑指 Offer II 059. 数据流的第 K 大数值
 * https://leetcode-cn.com/problems/jBjn9C/
 *
 * 思路：优先队列 -- 小根堆
 *
 */
public class Offer_02_59 {

}

class KthLargest {
    PriorityQueue<Integer> queue;
    int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.queue = new PriorityQueue<>();

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        queue.offer(val);

        if (queue.size() > k) {
            queue.poll();
        }

        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */