package com.geek.jianzhi.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 22:19
 * 剑指 Offer II 006. 排序数组中两个数字之和
 * https://leetcode-cn.com/problems/kLl5u1/
 *
 * 双指针法、左右指针法
 *
 */
public class Offer06 {

}

class Solution06 {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{left,right};
            }
        }

        return new int[]{};
    }
}
