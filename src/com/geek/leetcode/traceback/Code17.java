package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-10 14:57
 * 17. 电话号码的字母组合
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 *
 * 思路：
 * 1. 数字和字母如何映射
 * 2. 两个字母就两个for循环，三个字符我就三个for循环，以此类推，然后发现代码根本写不出来
 * 3. 输入1 * #按键等等异常情况
 *
 */
public class Code17 {

}

class Solution17 {
    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    // 初始对应所有的数字，为了直接对应2-9，新增了两个无效的字符串""
    // 也可以使用map
    private final String[] letterMap = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) return result;
        backtracking(digits, 0);
        return result;
    }

    public void backtracking(String digits, int index) {
        // 终止条件：抵达字符串末尾
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }

        // 将index指向的数字转为int
        int digit = digits.charAt(index) - '0';
        // 取数字对应的字符集
        String letters = letterMap[digit];

        // 遍历字符集合
        for (int i = 0; i < letters.length(); i++) {
            sb.append(letters.charAt(i));
            backtracking(digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}