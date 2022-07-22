package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-11 16:30
 * 78. 子集
 * https://leetcode.cn/problems/subsets/
 *
 * 思考：求取子集问题，不需要任何剪枝！因为子集就是要遍历整棵树。
 *
 */
public class Code78 {

}

class Solution78 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtracking(nums, 0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        // 刚开始为空集
        // 不需要终止条件，因为需要收集每个节点的值
        result.add(new ArrayList<>(path));

        // 不需要剪枝
        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
