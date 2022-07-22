package com.geek.jianzhi.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-06-13 16:57
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 *
 * 思路：数组问题一般都是双指针
 *
 */
public class Offer21 {

}

// 左右指针法
class Solution21 {
    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1, tmp;
        while (i < j) {
            // 左指针找第一个偶数
            while (i < j && (nums[i] & 1) == 1) i++;
            // 右指针找第一个奇数
            while (i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        return nums;
    }
}