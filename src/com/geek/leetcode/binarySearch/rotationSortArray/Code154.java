package com.geek.leetcode.binarySearch.rotationSortArray;

/**
 * @author G.E.E.K.
 * @create 2022-06-03 15:18
 * 154. 寻找旋转排序数组中的最小值 II
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array-ii/
 *
 * 思路：有重复数字；二分查找
 *       分成两个有序的排序数组，寻找临界点（第二个数组的开始位置）
 *
 */
public class Code154 {

}

// 二分查找
class Solution154 {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            // 查找左区间
            if (nums[mid] < nums[high]) {
                high = mid;
            } else if (nums[mid] > nums[high]) {
                // 查找右区间
                low = mid + 1;
            } else {
                // 元素可重复，
                // (重要)右区间减1
                high--;
            }
        }

        return nums[low];
    }
}

/**
 * 二分查找
 * 通过中值和右指针判断范围（因为是递增数组旋转）
 * 具有分段递增的特性（二段递增）
 * 有重复值情况，中值和右指针相等的时候，右指针收缩
 */
class Solution154_1 {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            // 中间值在有半部分有序区域，也就是递增区域，有可能为最小值
            if (nums[mid] < nums[high]) {
                high = mid;
                // 在左边升序数组，最小值在有右边
            } else if (nums[mid] > nums[high]){
                low = mid + 1;
            } else {
                // 有指针线性收缩
                high--;
            }
        }

        return nums[low];
    }
}