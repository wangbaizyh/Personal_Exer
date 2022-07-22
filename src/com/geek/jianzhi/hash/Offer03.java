package com.geek.jianzhi.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author G.E.E.K.
 * @create 2022-05-30 10:28
 * 剑指 Offer 03. 数组中重复的数字
 * https://leetcode.cn/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
 *
 * 思路：哈希表
 */
public class Offer03 {

}

// 哈希表
class Solution03 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for (int num : nums) {
            if (set.contains(num)) {
                repeat = num;
                break;
            } else {
                set.add(num);
            }
        }

        return repeat;
    }
}

class Solution03_01 {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) return num;
            set.add(num);
        }

        return -1;
    }
}

// 用数组做字典
// 原地交换（等价于字典）
class Solution03_02 {
    public int findRepeatNumber(int[] nums) {
        int i = 0;

        while (i < nums.length) {

            // 若 nums[i] = i ：说明此数字已在对应索引位置，无需交换，因此跳过；
            if (nums[i] == i) {
                i++;
                continue;
            }

            // 若 nums[nums[i]] = nums[i] ：代表索引 nums[i] 处和索引 i 处的元素值都为 nums[i] ，
            // 即找到一组重复值，返回此值 nums[i] ；
            if (nums[i] == nums[nums[i]]) return nums[i];

            // 否则：交换索引为 i 和 nums[i] 的元素值，将此数字交换至对应索引位置。
            swap(nums, i, nums[i]);
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}