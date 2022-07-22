package com.geek.leetcode.binarySearch.rotationSortArray;

/**
 * @author G.E.E.K.
 * @create 2022-06-02 20:41
 * 153. 寻找旋转排序数组中的最小值
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
 *
 * 思路：旋转数组->二分查找
 *
 */
public class Code153 {

}

// 二分查找
// 时间复杂度：时间复杂度为O(log n)，其中 n 是数组 nums 的长度。
// 在二分查找的过程中，每一步会忽略一半的区间，因此时间复杂度为 O(log n)。

class Solution153 {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            // 查找左区间
            if (nums[mid] < nums[high]) {
                high = mid;
            } else if (nums[mid] > nums[high]){
                // 查找右区间
                low = mid + 1;
            }
        }

        return nums[low];
    }
}

/**
 * 二分查找
 * 通过中值和右指针判断范围（因为是递增数组旋转）
 * 具有分段递增的特性
 */
class Solution153_1 {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            // 中间值在有半部分有序区域，也就是递增区域，有可能为最小值
            if (nums[mid] < nums[high]) {
                high = mid;
                // 在左边升序数组，最小值在有右边
            } else {
                low = mid + 1;
            }
        }

        return nums[low];
    }
}