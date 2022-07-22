package com.geek.leetcode.sort;

/**
 * @author G.E.E.K.
 * @create 2022-07-12 13:47
 * 88. 合并两个有序数组
 * https://leetcode.cn/problems/merge-sorted-array/
 *
 * 思路：快排、双指针
 *
 */
public class Code88 {

}

class Solution88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;

        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }

        quicksort(nums1, 0, m + n - 1);
    }

    private void quicksort(int[] nums, int begin, int end) {
        if (begin >= end) return;

        int base = nums[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            while (left < right && nums[right] >= base) right--;
            nums[left] = nums[right];

            while (left < right && nums[left] <= base) left++;
            nums[right] = nums[left];
        }

        nums[left] = base;
        quicksort(nums, begin, left - 1);
        quicksort(nums, left + 1, end);
    }
}

// 正解
// 双指针倒序遍历
class Solution88_1 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1, right = n - 1;
        int index = m + n - 1;
        // 倒序遍历
        while (index >= 0) {
            if (right == -1 || left == -1) break;
            if (nums2[right] > nums1[left]) {
                nums1[index] = nums2[right];
                right--;
            } else {
                nums1[index] = nums1[left];
                left--;
            }
            index--;
        }
        // 合并剩余数组(右数组没合并完)
        if (left == -1) {
            while (right >= 0) {
                nums1[index] = nums2[right];
                right--;
                index--;
            }
        }
    }
}