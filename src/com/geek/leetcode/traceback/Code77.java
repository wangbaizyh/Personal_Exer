package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-10 11:18
 * 77. 组合
 * https://leetcode.cn/problems/combinations/
 *
 * 可以剪枝的地方就在递归中每一层的for循环所选择的起始位置。
 * 如果for循环选择的起始位置之后的元素个数 已经不足 我们需要的元素个数了，那么就没有必要搜索了。
 */
public class Code77 {

}

class Solution77 {
    /**
     * 存放符合条件结果的集合
     */
    List<List<Integer>> result = new ArrayList<>();
    /**
     * 用来存放符合条件单一结果
     */
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // startIndex记录下一层递归搜索起始的位置
        backtracking(n, k,1);
        return result;
    }

    public void backtracking(int n, int k, int startIndex) {
        // 递归终止条件
        if (list.size() == k) {
            result.add(new ArrayList<>(list));
            return;
        }

        // 宽度取决于每层的节点个数
        // 控制树的横向遍历
        // 剪枝优化
        for (int i = startIndex; i <= n - (k - list.size()) + 1; i++) {
            // 处理节点
            list.add(i);
            // 递归：控制树的纵向遍历，注意下一层搜索要从i+1开始
            backtracking(n, k, i + 1);
            // 回溯，撤销处理的节点
            list.remove(list.size() - 1);
        }
    }
}