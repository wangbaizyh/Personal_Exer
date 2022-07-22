package com.geek.leetcode.sort;

import org.junit.Test;
import java.util.PriorityQueue;

/**
 * @author G.E.E.K.
 * @create 2022-05-04 9:56
 * 703. 数据流中的第 K 大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-a-stream/
 *
 * 优先队列（小根堆）
 * 手写堆排：小根堆
 *
 */
public class Code703 {
    @Test
    public void test() {
        KthLargest kthLargest = new KthLargest(1, new int[]{});
        int ans = kthLargest.add(-3);
        System.out.println(ans);
    }

}

// 手写小根堆
class KthLargest {
    // 小顶堆
    private final int[] minHeap;
    // 堆的大小
    private final int heapSize;
    // 堆的最后一个元素的索引
    private int last;


    public KthLargest(int k, int[] nums) {
        this.heapSize = k;
        this.minHeap = new int[heapSize];
        // 初始化索引为-1，也就是空数组的情况
        this.last = -1;

        // 元素进堆，更新最后一个元素的索引
        for (int i = 0; i < heapSize && i < nums.length; i++) {
            this.minHeap[i] = nums[i];
            this.last = i;
        }

        // 调整堆有序，构建小根堆
        if (last == heapSize - 1) {
            buildMinHeap();
        }

        // 如果小顶堆被填满了,就继续添加元素,并使堆有序
        for (int i = heapSize; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    public int add(int val) {
        // 如果堆还未被填满（由题意堆会在至多一次add后被填满）
        // 则将元素放在无序堆的末尾，然后将堆有序化
        if (last == heapSize - 2) {
            minHeap[++last] = val;
            buildMinHeap();
        } else if (val > minHeap[0]) {
            // 如果新元素大于堆中第k大的元素（minHeap[0]）
            // 则替换它，并将堆有序化
            minHeap[0] = val;
            minHeapModify(0);
        }

        return minHeap[0];
    }

    // 构建小根堆
    private void buildMinHeap() {
        // 从最后一个父节点位置开始调整每一个节点的子树。
        // 数组长度为heaSize，因此最后一个节点的位置为heapSize - 1，
        // 所以父节点的位置为heapSize - 1 - 1 / 2。
        for (int i = (heapSize - 2) / 2; i >= 0; i--) {
            minHeapModify(i);
        }
    }

    // 调整堆有序化
    public void minHeapModify(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && minHeap[left] < minHeap[smallest]) {
            smallest = left;
        }

        if (right < heapSize && minHeap[right] < minHeap[smallest]) {
            smallest = right;
        }

        if (smallest != i){
            swap(i, smallest);
            minHeapModify(smallest);
        }
    }

    private void swap(int i, int j) {
        int tmp = minHeap[i];
        minHeap[i] = minHeap[j];
        minHeap[j] = tmp;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

// 优先队列 （小根堆）
class KthLargest_01 {
    PriorityQueue<Integer> queue;
    int k;

    public KthLargest_01(int k, int[] nums) {
        this.k = k;
        this.queue = new PriorityQueue<>();

        for (int x : nums) {
            add(x);
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
