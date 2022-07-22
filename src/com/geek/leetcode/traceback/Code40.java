package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-11 11:09
 * 40. 组合总和 II
 * https://leetcode.cn/problems/combination-sum-ii/
 *
 * 思路：元素有重复
 * 搜索过程中去重，去重需要对树进行排序
 * 集合去重，使用一个used数据来记录
 */
public class Code40 {

}

class Solution40 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
        boolean[] used = new boolean[candidates.length];
        backtracking(candidates, target, 0, 0, used);
        return result;
    }

    public void backtracking(int[] candidates, int target,int sum, int startIndex, boolean[] used){
        // 终止条件
        if (sum == target){
            result.add(new ArrayList<>(path));
            return;
        }

        // 求和剪枝
        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 判断去重
            if (i > 0 && candidates[i] == candidates[i - 1] && !used[i - 1]) {
                continue;
            }

            sum += candidates[i];
            path.add(candidates[i]);
            used[i] = true;
            backtracking(candidates, target, sum, i + 1, used);
            used[i] = false;
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}

// 直接使用startIndex进行去重
class Solution40_01 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return result;
        Arrays.sort(candidates);
        backtracking(candidates, target, 0, 0);
        return result;
    }

    public void backtracking(int[] candidates, int target,int sum, int startIndex){
        if (sum == target){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length && sum + candidates[i] <= target; i++) {
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            if (i > startIndex && candidates[i] == candidates[i - 1]) {
                continue;
            }

            sum += candidates[i];
            path.add(candidates[i]);
            backtracking(candidates, target, sum, i + 1);;
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}