package com.geek.jianzhi.sort;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-22 11:46
 * 剑指 Offer 41. 数据流中的中位数
 * https://leetcode.cn/problems/shu-ju-liu-zhong-de-zhong-wei-shu-lcof/
 *
 * 思路：数据流问题正常使用堆排序来解决
 * 添加元素时保持数组有序 使用堆排序可进行优化
 * 建立一个 小顶堆 A 和 大顶堆 B ，各保存列表的一半元素，且规定：
 *      A 保存 较大 的一半
 *      B 保存 较小 的一半
 * 随后，中位数可仅根据 A,B 的堆顶元素计算得到。
 *
 * 算法流程：
 * 设元素总数为 N = m + n ，其中 m 和 n 分别为 A 和 B 中的元素个数。
 *
 *
 *
 */
public class Offer41 {

}

class MedianFinder {
    Queue<Integer> A, B;

    /** initialize your data structure here. */
    public MedianFinder() {
        // 小顶堆，保存较大的一半
        A = new PriorityQueue<>();
        // 大顶堆，保存较小的一半
        B = new PriorityQueue<>((o1, o2) -> (o2 - o1));
    }

    public void addNum(int num) {
        // 保证两堆大小尽量相等
        // 不相等的时候，先进入小顶堆，然后把小顶堆堆顶最小的元素加入大顶堆
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        // 相等的时候，先进入大顶堆，然后把大顶堆堆顶最大的元素加入小顶堆
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }

    // 返回中位数
    public double findMedian() {
        // 两堆大小不相等，返回小根堆堆顶
        // 相等则返回两堆堆顶的均值
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}