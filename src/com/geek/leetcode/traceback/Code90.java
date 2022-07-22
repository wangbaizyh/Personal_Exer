package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-12 15:40
 * 90. 子集 II
 * https://leetcode.cn/problems/subsets-ii/
 *
 */
public class Code90 {

}

class Solution90 {
    private final List<List<Integer>> result = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtracking(nums, 0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        // 添加集合元素
        result.add(new ArrayList<>(path));

        for (int i = startIndex; i < nums.length; i++) {

            // 树层去重
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }

            path.add(nums[i]);
            // 往下遍历
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}