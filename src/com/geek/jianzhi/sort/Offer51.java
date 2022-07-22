package com.geek.jianzhi.sort;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 21:40
 * 剑指 Offer 51. 数组中的逆序对
 * https://leetcode.cn/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 *
 * 思路：逆序对 -- 归并排序
 *
 * 归并排序的经典应用！！！！！！！！！
 *
 */
public class Offer51 {
    @Test
    public void test() {
        int ans = new Solution51().reversePairs(new int[]{2, 4, 3, 5, 1});
        System.out.println(ans);
    }
}

// 归并排序
// 先分再合
class Solution51 {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    // 返回逆序对的个数（交换操作的次数）
    private int mergeSort(int[] nums, int begin, int end) {
        if (begin >= end) return 0;
        // 递归划分
        int mid = begin + (end - begin) / 2;
        int res = mergeSort(nums, begin, mid) + mergeSort(nums, mid + 1, end);
        // 合并阶段
        int[] tmp = new int[end - begin + 1];
        int left = begin, right = mid + 1;
        // 临时数组
        for (int i = begin; i <= end; i++) {
            tmp[i - begin] = nums[i];
        }

        for (int i = begin; i <= end; i++) {
            // 左数组归并完成
            if (left == mid + 1) {
                nums[i] = tmp[right - begin];
                right++;
            // 右数组归并完成 || 左数组当前元素小于右数组当前元素
            } else if (right == end + 1 || tmp[left - begin] <= tmp[right - begin]) {
                nums[i] = tmp[left - begin];
                left++;
            // 构成逆序对的情况
            } else {
                nums[i] = tmp[right - begin];
                right++;
                res += mid - left + 1;
            }
        }

        return res;
    }
}