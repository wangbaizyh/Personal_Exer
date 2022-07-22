package com.geek.jianzhi.binarySearch;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-06-25 15:37
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * https://leetcode.cn/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 *
 * 思路：二分查找
 *
 */
public class Offer53_1 {
    @Test
    public void test() {
        int search = new Solution53_1().search(new int[]{1}, 1);
        System.out.println(search);
    }
}

// 二分搜索
class Solution53_1 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int count = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                count++;
                int l = mid - 1;
                int r = mid + 1;
                while (l >= 0 && nums[l--] == target) count++;
                while (r < nums.length && nums[r++] == target) count++;
                return count;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return count;
    }
}

// 二分查找：左右边界
// 两次二分
class Solution53_1_1 {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        // 搜索右边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }

        int end = left;
        // 若数组中无 target ，则提前返回
        if (right == -1) return 0;
        if (right >= 0 && nums[right] != target) return 0;

        // 搜索左边界
        left = 0;
        right = end;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        int begin = right;
        return end - begin - 1;
    }
}

class Solution53_1_2 {
    public int search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    // helper() 函数旨在查找数字 target 在数组 nums 中的 插入点 ，
    // 且若数组中存在值相同的元素，则插入到这些元素的右边。
    private int helper(int nums[], int target) {
        int left = 0, right = nums.length - 1;
        // 搜索右边界
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }

        return left;
    }
}