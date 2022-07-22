package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-12 16:59
 * 491. 递增子序列
 * https://leetcode.cn/problems/increasing-subsequences/
 *
 * 思路：不能排序，只能使用HashSet去重
 *
 */
public class Code49 {

}

class Solution49 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }
        // 树层去重, 使用set来对本层元素进行去重
        HashSet<Integer> set = new HashSet<>();

        for (int i = startIndex; i < nums.length; i++) {

            // 不为空，而且当前值小于列表最后一个值 || 去重
            if ((!path.isEmpty() && nums[i] < path.get(path.size() - 1)) || set.contains(nums[i])) {
                continue;
            }

            set.add(nums[i]);
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}

class Solution49_01 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        if (path.size() > 1) {
            result.add(new ArrayList<>(path));
        }
        // 树层去重, 使用数组来对本层元素进行去重
        int[] set = new int[201];

        for (int i = startIndex; i < nums.length; i++) {

            if ((!path.isEmpty() && nums[i] < path.get(path.size() - 1)) || set[nums[i] + 100] == 1) {
                continue;
            }

            set[nums[i] + 100] = 1;
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}