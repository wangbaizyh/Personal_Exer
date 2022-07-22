package com.geek.leetcode.binarySearch;

/**
 * @author G.E.E.K.
 * @create 2022-06-25 18:04
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 思路：二分查找
 *
 */
public class Code34 {

}

class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                int l = mid;
                int r = mid;
                // 寻找左边界
                while (l - 1 >= 0 && nums[l - 1] == target) {
                    l--;
                }
                // 寻找右边界
                while (r + 1 < nums.length && nums[r + 1] == target) {
                    r++;
                }

                // 返回左右边界
                return new int[]{l, r};
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 不存在目标元素
        return new int[]{-1, -1};
    }
}
