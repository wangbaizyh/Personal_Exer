package com.geek.leetcode.hashMap.staticHash;

/**
 * @author G.E.E.K.
 * @create 2022-07-19 11:03
 * 41. 缺失的第一个正数
 * https://leetcode.cn/problems/first-missing-positive/
 *
 * 思路：原地哈希
 * 时间复杂度 O(n)
 */
public class Code41 {

}

/**
 * 原地哈希
 */
class Solution41 {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // 遍历数组进行原地哈希
        for (int i = 0; i < n; i++) {
            // 循环交换直至相等
            // 满足在指定范围内、并且没有放在正确的位置上，才交换
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // 原地哈希交换
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        // 找没有出现的最小的正数
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        // 都正确则返回数组长度 + 1
        return n + 1;
    }
}


