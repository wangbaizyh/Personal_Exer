package com.geek.leetcode.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-07-18 17:26
 * 415. 字符串相加
 * https://leetcode.cn/problems/add-strings/
 *
 * 思路：模拟、双指针（大数相加模拟）
 *
 */
public class Code415 {

}

class Solution415 {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        StringBuilder ans = new StringBuilder();
        // 进位
        int carry = 0;
        // 模拟大数相加（长度较短的补充前导0）
        while (i >= 0 || j >= 0) {
            // 当前位计算
            int a = i >= 0 ? num1.charAt(i) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j) - '0' : 0;
            // 计算
            int temp = a + b + carry;
            // 进位
            carry = temp / 10;
            // 填入当前数字
            ans.append(temp % 10);
            i--;
            j--;
        }
        // 有进位
        if (carry == 1) ans.append(1);
        // 翻转字符
        return ans.reverse().toString();
    }
}


