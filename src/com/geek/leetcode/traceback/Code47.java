package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-12 21:40
 * 47. 全排列 II
 * https://leetcode.cn/problems/permutations-ii/
 *
 * 思路：树层去重
 *
 */
public class Code47 {

}

class Solution47 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];
        Arrays.sort(nums);
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
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            // 树层去重
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // 如果同⼀树⽀nums[i]没使⽤过开始处理
            // 树枝去重
            if (!used[i]) {
                used[i] = true;
                path.add(nums[i]);
                backtracking(nums);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}