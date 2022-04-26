package com.geek.leetcode.doublepoint;

import java.beans.beancontext.BeanContext;
import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-04-23 20:17
 * 15. 三数之和
 * https://leetcode-cn.com/problems/3sum/
 *
 * 双指针 & 哈希法
 * 哈希解法：写法较困难，需要考虑怎么去重（需要考虑很多条件） 不适用哈希法
 * 双指针法：一定要排序、左右指针法
 *
 */
public class Code15 {

}

// 双指针法：左右指针法
class Solution15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // 第一个元素大于0，直接返回
            if (nums[i] > 0) {
                return result;
            }

            // 去除第二个元素重复的情况
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            // 左右指针滑动
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));

                    // 去重
                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;

                    right--;
                    left++;
                }
            }
        }
        return result;
    }
}