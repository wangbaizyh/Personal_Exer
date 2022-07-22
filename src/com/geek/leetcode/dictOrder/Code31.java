package com.geek.leetcode.dictOrder;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-07-17 14:57
 * 31. 下一个排列
 * https://leetcode.cn/problems/next-permutation/
 *
 * 思路：字典序相关问题（贪心）
 * 1.从后往前查找第一个相邻的升序对，找到交换的「小数」，此时升序对之后的区间都是降序的
 * 2.在降序区间从后往前找第一个大于「小数」的「大数」
 * 3.交换「小数」和「大数」
 * 4.将降序区间逆序成升序区间
 * 5.如果1.找不到升序对，则直接跳到4.，返回起点
 *
 */
public class Code31 {

}

class Solution31 {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            // 1.从后往前找第一个升序对 (贪心)
            if (nums[i] < nums[i + 1]) {
                // 2.从后往前找第一个大于小数的大数 (大数交换才有意义)
                for (int j = nums.length - 1; j > i; j--) {
                    // 3.交换大数和小数 (只交换一次)
                    if (nums[j] > nums[i]) {
                        int tmp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = tmp;
                        break;
                    }
                }
                // 4.逆序降序区间
                Arrays.sort(nums, i + 1, nums.length);
                return;
            }
        }
        // 5.找不到升序对，回到起点
        Arrays.sort(nums);
    }
}

class Solution31_1 {
    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            // 1.从后往前找第一个升序对 (贪心)
            if (nums[i] < nums[i + 1]) {
                // 2.从后往前找第一个大于小数的大数 (大数交换才有意义)
                for (int j = nums.length - 1; j > i; j--) {
                    // 3.交换大数和小数 (只交换一次)
                    if (nums[j] > nums[i]) {
                        swap(nums, i, j);
                        break;
                    }
                }
                // 4.逆序降序区间
                reverse(nums, i + 1);
                return;
            }
        }
        // 5.找不到升序对，回到起点
        reverse(nums, 0);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}

class Solution31_2 {
    public void nextPermutation(int[] nums) {
        // 1.从后往前找第一个升序对 (贪心)
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 存在升序对
        if (i >= 0) {
            int j = nums.length - 1;
            // 2.从后往前找第一个大于小数的大数 (大数交换才有意义)
            while (j > i && nums[i] >= nums[j]) {
                j--;
            }
            // 3.交换大数和小数 (只交换一次)
            swap(nums, i, j);
        }

        // 4.逆序降序区间 (包括没找到升序对的场景)
        reverse(nums, i + 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}