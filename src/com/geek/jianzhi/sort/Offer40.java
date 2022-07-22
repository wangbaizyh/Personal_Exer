package com.geek.jianzhi.sort;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-22 11:04
 * 剑指 Offer 40. 最小的k个数
 * https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/
 *
 * 思路：快速排序
 * 排序都行：桶排序、堆排序
 */
public class Offer40 {

}

// 直接快排整个数组
class Solution40 {
    public int[] getLeastNumbers(int[] arr, int k) {
        quicksort(arr);
        return Arrays.copyOf(arr, k);
    }

    private static void subSort(int[] data, int begin, int end) {
        if (begin >= end) return;

        int base = data[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            while (left < right && data[right] >= base) {
                right--;
            }
            data[left] = data[right];

            while (left < right && data[left] <= base) {
                left++;
            }
            data[right] = data[left];
        }

        data[left] = base;
        subSort(data, begin, left - 1);
        subSort(data, left + 1, end);
    }

    public static void quicksort(int[] data) {
        subSort(data, 0, data.length - 1);
    }
}

// 利用快排特性，使用哨兵搜索并返回 最小的 k 个数
// 剪枝不必要的递归
class Solution40_1 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        quicksort(arr, k, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    public void quicksort(int[] data, int k, int begin, int end) {
        if (begin >= end) return;

        int base = data[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            while (left < right && data[right] >= base) {
                right--;
            }
            data[left] = data[right];

            while (left < right && data[left] <= base) {
                left++;
            }
            data[right] = data[left];
        }

        data[left] = base;
        if (left > k) quicksort(data, k, begin, left - 1);
        if (left < k) quicksort(data, k, left + 1, end);
    }
}

// 大根堆
class Solution40_02 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> queue = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num: arr) {
            if (queue.size() < k) {
                queue.offer(num);
            } else if (num < queue.peek()) {
                queue.poll();
                queue.offer(num);
            }
        }

        // 返回堆中的元素
        int[] res = new int[queue.size()];
        int idx = 0;
        for(int num: queue) {
            res[idx++] = num;
        }
        return res;
    }
}

// 桶排序
class Solution40_03 {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num: arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }
}