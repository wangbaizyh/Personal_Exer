package com.geek.leetcode.hashMap;

import java.util.HashMap;

/**
 * @author G.E.E.K.
 * @create 2022-04-22 14:59
 * 454. 四数相加 II
 * https://leetcode-cn.com/problems/4sum-ii/
 *
 *
 *
 */
public class Code454 {

}

class Solution454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int temp;
        int result = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                temp = i + j;
                map.put(temp, map.getOrDefault(temp,0) + 1);
            }
        }

        for (int i : nums3) {
            for (int j : nums4) {
                temp = i + j;
                result += map.getOrDefault(-temp,0);
            }
        }

        return result;
    }
}