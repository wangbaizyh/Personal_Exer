package com.geek.leetcode.hashMap.staticHash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-07-19 11:57
 * 448. 找到所有数组中消失的数字
 * https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 *
 * 思路：原地哈希
 *
 */
public class Code448 {

}

/**
 * 原地哈希
 */
class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) ans.add(i + 1);
        }
        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}