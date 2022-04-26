package com.geek.leetcode.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 20:34
 * 26. 删除有序数组中的重复项
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * 双指针法：快慢指针法
 *
 */
public class Code26 {

}

class Solution26 {
    public int removeDuplicates(int[] nums) {
        int slow = 1;

        // 从第一个索引开始，判断与前一个是否相等
        for (int fast = 1; fast < nums.length; fast++) {
            // 与前一个元素相等的时候跳过
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                slow++;
            }
        }

        return slow;
    }
}