package com.geek.leetcode.doublepoint;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 19:45
 * 27. 移除元素
 * https://leetcode-cn.com/problems/remove-element/
 *
 * 双指针法：左右指针法
 * 
 */
public class Code27 {

    @Test
    public void test() {
        int res = new Solution27().removeElement(new int[]{3, 2, 2, 3}, 3);
        System.out.println(res);
    }

}

class Solution27 {
    public int removeElement(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        // 必须取等，考虑只有一个元素的情况
        while (left <= right) {

            // 找左边等于val的元素
            while (left <= right && nums[left] != val) left++;

            // 找右边不等于val的元素
            while (left <= right && nums[right] == val) right--;

            // 将右边不等于val的元素覆盖左边等于val的元素
            if (left < right) {
                nums[left] = nums[right];
                left++;
                right--;
            }
        }

        return left;
    }
}