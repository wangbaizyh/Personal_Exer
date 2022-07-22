package com.geek.leetcode.binarySearch.rotationSortArray;

/**
 * @author G.E.E.K.
 * @create 2022-07-18 14:15
 * 81. 搜索旋转排序数组 II
 * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
 *
 * 思路：分段二分
 *
 */
public class Code81 {
}

class Solution81 {
    public boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) return true;
            // 以右指针为基准
            // 处于右侧有序区间
            if (nums[mid] < nums[high]) {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else if (nums[mid] > nums[high]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                high--;
            }
        }

        return false;
    }
}