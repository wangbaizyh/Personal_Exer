package com.geek.leetcode.binarySearch;

/**
 * @author G.E.E.K.
 * @create 2022-07-18 14:14
 * 35. 搜索插入位置
 * https://leetcode.cn/problems/search-insert-position/
 *
 *
 */
public class Code35 {
}

/**
 * 二分
 */
class Solution35 {
    public int searchInsert(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return low;
    }
}
