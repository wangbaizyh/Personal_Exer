package com.geek.leetcode.greed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-18 15:55
 * 763. 划分字母区间
 * https://leetcode.cn/problems/partition-labels/
 *
 */
public class Code763 {

}

// 原地Hash + 贪心
class Solution763 {
    public List<Integer> partitionLabels(String s) {
        // i为字符，hash[i]为字符出现的最后位置
        int[] hash = new int[27];
        // 统计每一个字符最后出现的位置
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a'] = i;
        }

        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = 0;
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, hash[s.charAt(i) - 'a']);
            if (i == right) {
                result.add(right - left + 1);
                left = i + 1;
            }
        }

        return result;
    }
}