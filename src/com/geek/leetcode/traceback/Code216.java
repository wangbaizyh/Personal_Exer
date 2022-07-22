package com.geek.leetcode.traceback;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-10 14:20
 * 216. 组合总和 III
 * https://leetcode.cn/problems/combination-sum-iii/
 *
 */
public class Code216 {
    @Test
    public void test() {
        List<List<Integer>> ans = new Solution216().combinationSum3(3, 7);
        System.out.println(ans);
    }

}

class Solution216 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> list = new ArrayList<>();
    int sum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k, n , 1);
        return result;
    }

    public void backtracking(int k, int n, int startIndex) {
        // 结束条件
        if (list.size() == k) {
            if (sum == n) result.add(new ArrayList<>(list));
            return;
        }

        // 剪枝优化
        for (int i = startIndex; i <= 9 - (k - list.size()) + 1 && sum < n; i++) {
            sum += i;
            list.add(i);
            backtracking(k, n, i + 1);
            sum -= i;
            list.remove(list.size() - 1);
        }
    }
}
