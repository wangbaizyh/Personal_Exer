package com.geek.jianzhi.queue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 15:43
 * 剑指 Offer 59 - I. 滑动窗口的最大值
 * https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/
 *
 * 思路：单调队列 --> 使用双端队列
 * 两种方法：
 *      - 存储元素 （封装单调栈类）
 *      - 存储下标 （直接使用Deque存储）
 *
 */
public class Offer59_1 {

}

class Solution59_1 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[]{};
        MyQueue queue = new MyQueue();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;

        // 先将前k的元素放入队列
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        ans[index++] = queue.peek();

        // 只需考虑滑动窗口的右边界
        for (int i = k; i < nums.length; i++) {
            // 滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            queue.poll(nums[i - k]);
            // 滑动窗口加入最后面的元素
            queue.offer(nums[i]);
            // 记录对应的最大值
            ans[index++] = queue.peek();
        }

        return ans;
    }
}

// 单调队列（从大到小）
// 单调递减队列
class MyQueue {
    // 使用双端队列来实现单调队列
    Deque<Integer> queue;

    public MyQueue() {
        this.queue = new LinkedList<>();
    }

    // 每次弹出的时候，比较当前要弹出的数值是否等于队列出口元素的数值，如果相等则弹出。
    // 同时poll之前判断队列当前是否为空。
    public void poll(int value) {
        if (!queue.isEmpty() && queue.peekFirst() == value) {
            queue.pollFirst();
        }
    }

    // 添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    // 保证队列元素单调递减
    // 比如此时队列元素3,1,2将要入队，2比1大，所以1弹出，此时队列：3,2
    public void offer(int value){
        while (!queue.isEmpty() && value > queue.peekLast()) {
            queue.pollLast();
        }
        queue.offerLast(value);
    }

    public int peek() {
        return queue.peekFirst();
    }
}

// 单调栈（存储元素下标）
class Solution59_2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[]{};
        // 使用双端队列
        Deque<Integer> queue = new LinkedList<>();
        // 结果数组
        int[] ans = new int[nums.length - k + 1];
        int index = 0;

        for (int i = 0; i < nums.length; i++) {
            // 根据题意，i为nums下标，是要在[i - k + 1, i] 中选到最大值，只需要保证两点
            // 1.队列头结点需要在[i - k + 1, i]范围内，不符合则要弹出
            while (!queue.isEmpty() && queue.peekFirst() < i - k + 1) {
                queue.pollFirst();
            }

            // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
            while (!queue.isEmpty() && nums[i] > nums[queue.peekLast()]) {
                queue.pollLast();
            }

            // 当前下标进入队列
            queue.offerLast(i);

            if (i >= k - 1) {
                ans[index++] = nums[queue.peekFirst()];
            }
        }

        return ans;
    }
}


