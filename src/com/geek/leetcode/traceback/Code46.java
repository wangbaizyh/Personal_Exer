package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-12 17:29
 * 46. 全排列
 * https://leetcode.cn/problems/permutations/
 *
 * 思路：排列问题 = 全遍历 + 树枝去重
 *
 */
public class Code46 {

}

class Solution46 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    int[] set = new int[21];

    public List<List<Integer>> permute(int[] nums) {
        backtracking(nums);
        return result;
    }

    public void backtracking(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 全遍历
        for (int i = 0; i < nums.length; i++) {
            // 树枝去重
            if (set[nums[i] + 10] == 1) continue;

            set[nums[i] + 10] = 1;
            path.add(nums[i]);
            backtracking(nums);
            set[nums[i] + 10] = 0;
            path.remove(path.size() - 1);
        }
    }
}

class Solution46_1 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        backtracking(nums);
        return result;
    }

    public void backtracking(int[] nums) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 全遍历
        for (int i = 0; i < nums.length; i++) {
            // 树枝去重
            if (used[i]) continue;

            used[i] = true;
            path.add(nums[i]);
            backtracking(nums);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}