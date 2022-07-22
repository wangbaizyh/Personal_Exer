package com.geek.jianzhi.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-06-21 22:19
 * 剑指 Offer 38. 字符串的排列
 * https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/
 *
 * 思路：回溯、排列问题
 *
 */
public class Offer38 {
    @Test
    public void test() {
        String[] ans = new Solution38().permutation("abc");
        for (String an : ans) {
            System.out.println(an);
        }
    }
}

class Solution38 {
    List<String> ans = new ArrayList<>();

    public String[] permutation(String s) {
        char[] strs = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        // 树层去重
        boolean[] used = new boolean[s.length()];
        Arrays.sort(strs);
        backtrack(strs, sb, used);
        return ans.toArray(new String[ans.size()]);
    }

    private void backtrack(char[] strs, StringBuilder sb, boolean[] used) {
        if (sb.length() == strs.length) {
            ans.add(new String(sb));
            return;
        }

        for (int i = 0; i < strs.length; i++) {
            // 同一树枝没有使用过
            if (used[i]) continue;
            // 树层去重
            if (i > 0 && strs[i - 1] == strs[i] && used[i - 1]) continue;

            sb.append(strs[i]);
            used[i] = true;
            backtrack(strs, sb, used);
            used[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}