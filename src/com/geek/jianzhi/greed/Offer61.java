package com.geek.jianzhi.greed;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author G.E.E.K.
 * @create 2022-06-28 17:26
 * 剑指 Offer 61. 扑克牌中的顺子
 * https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
 *
 * 思路：贪心
 * 1.无重复
 * 2.max - min <= 4
 *
 */
public class Offer61 {

}

// 排序 + 遍历
class Solution61 {
    public boolean isStraight(int[] nums) {
        // 排序
        Arrays.sort(nums);
        int min = 0;
        boolean flag = true;

        for (int i = 0; i < 5; i++) {
            // 最小数字
            if (nums[i] != 0 && flag) {
                min = nums[i];
                flag = false;
            }

            // 重复
            if (i > 0 && nums[i] != 0 && nums[i] == nums[i - 1]) return false;
        }

        // 最大数字
        int max = nums[4];

        // 最大数字和最小数字之差小于等于4
        return max - min <= 4;
    }
}

// 集合Set + 遍历
class Solution61_1 {
    public boolean isStraight(int[] nums) {
        Set<Integer> repeat = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) continue;
            if (repeat.contains(num)) return false;
            repeat.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        return max - min <= 4;
    }
}

// 排序 + 遍历
class Solution61_2 {
    public boolean isStraight(int[] nums) {
        int min = 0;
        Arrays.sort(nums);
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) min++;
            else if (nums[i] == nums[i + 1]) return false;
        }

        return nums[4] - nums[min] <= 4;
    }
}

