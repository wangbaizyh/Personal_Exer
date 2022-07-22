package com.geek.leetcode.sort;

/**
 * @author G.E.E.K.
 * @create 2022-04-28 23:35
 */
public class QuickSort {

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private static void subSort(int[] data, int begin, int end) {
        if (begin >= end) {
            return;
        }

        // 基准
        int base = data[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            // 找到小于base的数
            while (left < right && data[right] >= base) {
                right--;
            }
            // 覆盖到左边
            data[left] = data[right];

            // 找到大于base的数
            while (left < right && data[left] <= base) {
                left++;
            }
            // 覆盖到右边
            data[right] = data[left];
        }

        // 边界
        data[left] = base;
        subSort(data, begin, left - 1);
        subSort(data, left + 1, end);
    }

    public static void quickSort(int[] data) {
        subSort(data,0, data.length - 1);
    }

    private static void subSort_01(int[] data, int begin, int end) {
        if (begin >= end) {
            return;
        }

        // 随机选取一个为基准进行排序
        int picked = (int) (Math.random() * (end - begin + 1)) + begin;
        // 交换元素到开头
        swap(data, begin, picked);
        // 基准数值
        int base = data[begin];
        // 待交换的下标(+1)
        int low = begin;

        // 从第一位开始遍历数组（从小到大排序）  双指针
        for (int fast = begin + 1; fast <= end; fast++) {
            // 当前值小于基准则进行交换
            if (data[fast] < base) {
                swap(data, fast, low + 1);
                // 往前移动
                low++;
            }
        }

        // 把基准交换为边界位置
        swap(data, begin, low);

        // 左边小于的部分递归排序
        subSort_01(data, begin, low - 1);
        // 右边大于的部分递归排序
        subSort_01(data, low + 1, end);
    }



    public static void quickSort_01(int[] data) {
        subSort_01(data, 0 , data.length - 1);
    }
}
