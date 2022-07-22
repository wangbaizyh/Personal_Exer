package com.geek.jianzhi.doublepoint;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 14:35
 * 剑指 Offer 57 - II. 和为s的连续正数序列
 * https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
 *
 * 思路：前后指针、滑动窗口
 *
 */
public class Offer57_2 {
    @Test
    public void test() {
        int[][] ans = new Solution57_2().findContinuousSequence(9);
    }
}

// 左闭右闭原则
class Solution57_2 {
    public int[][] findContinuousSequence(int target) {
        if (target < 3) return new int[][]{};

        List<int[]> ans = new ArrayList<>();
        int slow = 1, fast = 2;

        int sum = 3;
        while (fast <= (target + 1) / 2)  {
            if (sum <= target) {
                if (sum == target) {
                    int[] ints = new int[fast - slow + 1];
                    for (int i = slow; i <= fast; i++) {
                        ints[i - slow] = i;
                    }
                    ans.add(ints);
                }
                fast++;
                sum += fast;
            } else {
                sum -= slow;
                slow++;
            }
        }

        return ans.toArray(new int[ans.size()][]);
    }
}