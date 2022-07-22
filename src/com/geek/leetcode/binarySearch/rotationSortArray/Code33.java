package com.geek.leetcode.binarySearch.rotationSortArray;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 16:31
 * 33. 搜索旋转排序数组
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 *
 * 思路：二分查找
 * 分成左右两部分 有序数组进行查找
 *
 */
public class Code33 {

}

// 分段讨论
// 有序部分 + 无序部分
class Solution33 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 找到数据
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[left] == target) return left;
            if (nums[right] == target) return right;

            // 有序数组
            if (nums[mid] >= nums[left]) {
                // 有序数组左半部分
                if (target > nums[left] && target < nums[mid]) {
                    right = mid - 1;
                // 有序数组右半部分
                } else {
                    left = mid + 1;
                }
            // 无序数组
            } else {
                // 在无序数组右边升序的有序数组中
                if (nums[mid] < target && target < nums[right]) {
                    left = mid + 1;
                // 左边的无序数组中
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}

/**
 * 因为题目要求时间复杂度是log(n) 搜索问题容易想到使用二分搜索的方法
 * 这是一道很有难度的二分搜索题
 * 需要进行分段搜索
 */
class Solution33_1 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 二分搜索
        // 加等号是为了最终只剩一个的时候搜索到mid，返回结果
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            // 找到结果
            if (nums[mid] == target) return mid;

            // mid处于旋转后有序的一部分数组(左边的递增数组)
            if (nums[mid] >= nums[left]) {
                // target在有序数组部分
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                    // target在部分无序数组部分
                } else {
                    left = mid + 1;
                }
                // mid处于旋转后部分无序的一部分数组(右边的数组)
            } else {
                // 在有序数组中
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                    // 在部分无序数组中
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}