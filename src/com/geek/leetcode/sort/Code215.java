package com.geek.leetcode.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author G.E.E.K.
 * @create 2022-04-28 22:33
 * 215. 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 *
 * 两种方法：快速排序、堆排序
 *         1、快速排序（分治算法）
 *         2、堆排序
 *
 *
 */
public class Code215 {
    @Test
    public void test() {
        int res = new Solution215().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println(res);
        int[] test1 = {3, 2, 1, 5, 6, 4};
        QuickSort.quickSort(test1);
        System.out.println(Arrays.toString(test1));
        Solution215.quickSort(test1, 0, test1.length - 1);
        System.out.println(Arrays.toString(test1));

        res = new Solution215_01().findKthLargest(new int[]{2, 1}, 1);
        System.out.println(res);
    }

}

/**
 * 快速排序(方法一)
 * 从小到大排序，取倒数第k个数字
 */
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        quickSort(nums, 0 , nums.length - 1);
        return nums[nums.length - k];
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        // 随机选取一个为基准进行排序
        int picked = (int) (Math.random() * (right - left + 1)) + left;
        // 交换元素到开头
        swap(nums, left, picked);
        // 基准数值
        int base = nums[left];
        int begin = left, end = right;

        while (begin < end) {
            while (begin < end && nums[end] >= base) end--;
            nums[begin] = nums[end];

            while (begin < end && nums[begin] <= base) begin++;
            nums[end] = nums[begin];
        }

        nums[begin] = base;
        // 左边小于的部分递归排序
        quickSort(nums, left, begin - 1);
        // 右边大于的部分递归排序
        quickSort(nums, begin + 1, right);
    }

    // 交换数组元素
    private static void swap(int[]nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}

// 快排缩小搜索区间(快排剪枝)
// 从大到小排序
class Solution215_01 {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    private static int quickSelect(int[] nums, int left, int right, int k) {
        int q = quickSort(nums, left, right);
        if (q == k - 1) {
            return nums[q];
        } else {
            return q < k - 1 ? quickSelect(nums, q + 1, right, k) : quickSelect(nums, left, q - 1, k);
        }
    }

    public static int quickSort(int[] nums, int left, int right) {
        // 随机选取一个为基准进行排序
        int picked = (int) (Math.random() * (right - left + 1)) + left;
        // 交换元素到开头
        swap(nums, left, picked);
        // 基准数值
        int base = nums[left];
        int begin = left;
        int end = right;

        // 从大到小排序
        while (begin < end) {
            // 找到大于base的数
            while (begin < end && nums[end] <= base) {
                end--;
            }
            // 覆盖到左边
            nums[begin] = nums[end];

            // 找到小于base的数
            while (begin < end && nums[begin] >= base) {
                begin++;
            }
            // 覆盖到右边
            nums[end] = nums[begin];
        }

        // 边界
        nums[begin] = base;

        return begin;
    }

    // 交换数组元素
    private static void swap(int[]nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}

// 堆排序
// 大根堆 + k - 1次删除
// 手写堆排 -- 大根堆
class Solution215_02_01 {
    public int findKthLargest(int[] nums, int k) {
        // 堆大小
        int heapSize = nums.length;
        // 构建大根堆
        buildMaxHeap(nums, heapSize);
        // 建堆完毕后，nums[0]为最大元素。逐个删除堆顶元素，直到删除了k - 1个。
        for (int i = nums.length - 1; i >= nums.length - k + 1; i--) {
            // 先将堆的最后一个元素与堆顶元素交换，由于此时堆的性质被破坏，需对此时的根节点进行向下调整操作。
            swap(nums, 0 , i);
            // 相当于删除堆顶元素，此时长度变为nums.length - 2。即下次循环的i
            heapSize--;
            maxHeapModify(nums, 0, heapSize);
        }

        return nums[0];
    }

    private static void buildMaxHeap(int[] nums, int heapSize) {
        // 从最后一个父节点位置开始调整每一个节点的子树。
        // 数组长度为heaSize，因此最后一个节点的位置为heapSize - 1，
        // 所以父节点的位置为heapSize - 1 - 1 / 2。
        for (int i = (heapSize - 2) / 2; i >= 0; i--) {
            maxHeapModify(nums, i, heapSize);
        }
    }

    // 调整当前节点和子节点的顺序。
    private static void maxHeapModify(int[] nums, int i, int heapSize) {
        // left和right表示当前父节点i的两个左右子节点。
        int left = i * 2 + 1, right = i * 2 + 2, largest = i;
        // 如果左子点在数组内，且比当前父节点大，则将最大值的指针指向左子点。
        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }

        // 如果右子点在数组内，且比当前父节点大，则将最大值的指针指向右子点。
        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }

        // 如果最大值的指针不是父节点，则交换父节点和当前最大值指针指向的子节点。
        if (largest != i) {
            swap(nums, i , largest);
            // 由于交换了父节点和子节点，因此可能对子节点的子树造成影响，所以对子节点的子树进行调整。
            maxHeapModify(nums, largest, heapSize);
        }
    }

    // 交换数组元素
    private static void swap(int[]nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// 小根堆 + 容量为k
// 手写堆排 -- 小根堆
class Solution215_02_02 {
    public int findKthLargest(int[] nums, int k) {
        // 容量大小
        int heapSize = k;
        // 构建前k个元素的小根堆
        buildMinHeap(nums, heapSize);
        // 建堆完毕后，nums[0]为最小元素，遍历完整个数组，即为第k小的元素。
        for (int i = k; i < nums.length; i++) {
            // 如果此时的元素大于最小堆顶
            if (nums[i] > nums[0]) {
                // 交换堆顶和此元素，重新对堆进行排序
                swap(nums, 0, i);
                minHeapModify(nums, 0 , heapSize);
            }
        }

        return nums[0];
    }

    private static void buildMinHeap(int[] nums, int heapSize) {
        // 从最后一个父节点位置开始调整每一个节点的子树。
        // 数组长度为heaSize，因此最后一个节点的位置为heapSize - 1，
        // 所以父节点的位置为heapSize - 1 - 1 / 2。
        for (int i = (heapSize - 2) / 2; i >= 0; i--) {
            minHeapModify(nums, i, heapSize);
        }
    }

    // 调整当前结点和子节点的顺序。
    private static void minHeapModify(int[] nums, int i, int heapSize) {
        // left和right表示当前父节点i的两个左右子节点。
        int left = i * 2 + 1, right = i * 2 + 2, smallest = i;
        // 如果左子点在数组内，且比当前父节点小，则将最小值的指针指向左子点。
        if (left < heapSize && nums[left] < nums[smallest]) {
            smallest = left;
        }

        // 如果右子点在数组内，且比当前父节点小，则将最小值的指针指向右子点。
        if (right < heapSize && nums[right] < nums[smallest]) {
            smallest = right;
        }

        // 如果最小值的指针不是父节点，则交换父节点和当前最小值指针指向的子节点。
        if (smallest != i) {
            swap(nums, i , smallest);
            // 由于交换了父节点和子节点，因此可能对子节点的子树造成影响，所以对子节点的子树进行调整。
            minHeapModify(nums, smallest, heapSize);
        }
    }

    // 交换数组元素
    private static void swap(int[]nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// 优先队列(小顶堆) --> 时间较短
class Solution215_03 {
    public int findKthLargest(int[] nums, int k) {
        // 小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // 前k个元素构建小根堆
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }

        // 维护容量为k的小根堆
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > queue.peek()) {
                queue.poll();
                queue.offer(nums[i]);
            }
        }

        return queue.poll();
    }
}

// 优先队列(大顶堆) --> 时间较长
class Solution215_04 {
    public int findKthLargest(int[] nums, int k) {
        // 大根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> (o2 - o1)
        );

        // 构建大根堆
        for (int num : nums) {
            queue.offer(num);
        }

        // 移出k - 1个元素
        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }

        // 第k大元素
        return queue.poll();
    }
}

