package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-11 11:47
 * 131. 分割回文串
 * https://leetcode.cn/problems/palindrome-partitioning/
 *
 * 关键问题：切割、判断回文
 * 难点：
 *      - 切割问题可以抽象为组合问题
 *      - 模拟切割线
 *      - 递归如何终止
 *      - 在递归循环中如何截取子串
 *      - 如何判断回文
 *
 */
public class Code131 {

}

class Solution131 {
    List<List<String>> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return result;
    }

    public void backtracking(String s, int startIndex) {
        // 如果起始位置等于s的大小，说明找到了一组分割方案
        if (startIndex == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 是回文子串，则进行记录
            if (isPalindrome(s, startIndex, i)) {
                // 获取[startIndex,i]在s中的子串
                String str = s.substring(startIndex, i + 1);
                path.add(str);
            } else {
                continue;
            }

            // 下一条分割线从i + 1开始，保证不重复
            backtracking(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // 判断回文子串
    private boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }

        return true;
    }
}
