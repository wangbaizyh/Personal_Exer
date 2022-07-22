package com.geek.leetcode.stack$queue.MonotonousQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author G.E.E.K.
 * @create 2022-04-28 13:54
 * 239. 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * 思路：单调队列
 *
 */
public class Code239 {

}

// 单调栈（存储元素值）
class Solution239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotoneQueue queue = new MonotoneQueue();
        int[] res = new int[nums.length - k + 1];
        int num = 0;

        // 先将前k的元素放入队列
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        res[num++] = queue.peek();

        // 只需考虑滑动窗口的右边界
        for (int i = k; i < nums.length; i++) {
            // 滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            queue.poll(nums[i - k]);
            // 滑动窗口加入最后面的元素
            queue.offer(nums[i]);
            // 记录对应的最大值
            res[num++] = queue.peek();
        }

        return res;
    }
}

//单调队列（从大到小）（从队头到队尾）
class MonotoneQueue{
    // 使用双端队列来实现单调队列
    Deque<Integer> deque;

    public MonotoneQueue(){
        this.deque = new LinkedList<>();
    }
    // 每次弹出的时候，比较当前要弹出的数值是否等于队列出口元素的数值，如果相等则弹出。
    // 同时poll之前判断队列当前是否为空。
    void poll(int value) {
        if (!deque.isEmpty() && deque.peek() == value) {
            deque.poll();
        }
    }
    // 添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    // 保证队列元素单调递减
    // 比如此时队列元素3,1,2将要入队，比1大，所以1弹出，此时队列：3,2
    void offer(int value) {
        while (!deque.isEmpty() && value > deque.peekLast()) {
            deque.pollLast();
        }
        deque.offer(value);
    }

    // 队列队顶元素始终为最大值
    int peek() {
        return deque.peek();
    }
}

// 单调队列（存储元素下标）
class Solution239_01 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 使用双端队列
        Deque<Integer> queue = new LinkedList<>();
        // 结果数组
        int[] res = new int[nums.length - k + 1];
        // 结果下标
        int num = 0;

        for (int i = 0; i < nums.length; i++) {
            // 根据题意，i为nums下标，是要在[i - k + 1, i] 中选到最大值，只需要保证两点
            // 1.队列头结点需要在[i - k + 1, i]范围内，不符合则要弹出
            while (!queue.isEmpty() && queue.peek() < i - k + 1) {
                queue.poll();
            }

            // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }

            queue.offer(i);


            if (i >= k - 1) {
                res[num++] = nums[queue.peek()];
            }
        }

        return res;
    }
}