package com.geek.leetcode.doublepoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-04-24 14:10
 * 18. 四数之和
 * https://leetcode-cn.com/problems/4sum/
 *
 * 双指针法、左右指针法
 *
 */
public class Code18 {

}

class Solution18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // a
        for (int i = 0; i < nums.length; i++) {

            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // b
            for (int j = i + 1; j < nums.length; j++) {

                // 去重
                if (j - i > 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                int left = j + 1;
                int right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum > target){
                        right--;
                        while (left < right && nums[right] == nums[right + 1 ]) right--;
                    } else if (sum < target){
                        left++;
                        while (left < right && nums[left] == nums[left - 1 ]) left++;
                    } else {
                        result.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));

                        // 去重
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        while (left < right && nums[left] == nums[left + 1 ]) left++;

                        right--;
                        left++;
                    }
                }
            }
        }

        return result;
    }
}