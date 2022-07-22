package com.geek.leetcode.traceback;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-11 14:14
 * 93. 复原 IP 地址
 * https://leetcode.cn/problems/restore-ip-addresses/
 *
 */
public class Code93 {
    @Test
    public void test() {
        List<String> list = new Solution93().restoreIpAddresses("101023");
        System.out.println(list);
    }

}

class Solution93 {
    List<String> result = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        // 长度剪枝
        if (s.length() < 4 || s.length() > 12) return result;
        backtracking(s,  0);
        return result;
    }

    public void backtracking(String s, int startIndex) {
        // 当抵达字符串末尾，而且有四个字段的时候，加入结果集
        if (startIndex == s.length() && path.size() == 4) {
           StringBuilder ans = new StringBuilder();
           ans.append(path.get(0));
           for (int i = 1; i < path.size(); i++) {
               ans.append(".").append(path.get(i));
           }
           result.add(ans.toString());
           return;
        }

        // 当抵达字符串末尾 或者 有四个字段的时候 （不满足条件时）
        if (startIndex == s.length() || path.size() == 4) return;

        // 剪枝：ip段的长度最大是3，并且ip段处于[0,255]
        for (int i = startIndex; i < s.length(); i++) {
            // 判断 [startIndex,i] 这个区间的子串是否合法
            if (!isValid(s, startIndex, i)) break;

            path.add(s.substring(startIndex, i + 1));
            backtracking(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // 判断字符串s在左闭右闭区间[start, end]所组成的数字是否合法
    private boolean isValid(String s, int start, int end) {
        // 0开头的数字不合法
        if (s.charAt(start) == '0' && start != end) return false;
        // 大于三位数
        if (end - start > 2) return false;
        // 大于255
        return Integer.parseInt(s.substring(start, end + 1)) <= 255;
    }
}

class Solution93_01 {
    List<String> result = new ArrayList<>();
    StringBuilder sb = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() < 4 || s.length() > 12) return result;
        backtracking(s, 0, 0);
        return result;
    }

    // number表示stringBuilder中ip段的数量
    public void backtracking(String s, int startIndex, int number) {
        // 如果start等于s的长度并且ip段的数量是4，则加入结果集，并返回
        if (startIndex == s.length() && number == 4) {
            result.add(sb.toString());
            return;
        }

        // 如果start等于s的长度但是ip段的数量不为4，或者ip段的数量为4但是start小于s的长度，则直接返回
        if (startIndex == s.length() || number == 4) return;

        // 剪枝：ip段的长度最大是3，并且ip段处于[0,255]
        for (int i = startIndex; i < s.length(); i++) {
            // 判断 [startIndex,i] 这个区间的子串是否合法
            if (!isValid(s, startIndex, i)) break;

            sb.append(s.substring(startIndex, i + 1));
            // 当stringBuilder里的网段数量小于3时，才会加点；如果等于3，说明已经有3段了，最后一段不需要再加点
            if (number < 3) {
                sb.append(".");
            }
            number++;
            backtracking(s, i + 1, number);
            number--;
            // 删除当前stringBuilder最后一个网段，注意考虑点的数量的问题
            // 多删一位，删掉插入的点
            sb.delete(startIndex + number, i + number + 2);
        }
    }

    // 判断字符串s在左闭右闭区间[start, end]所组成的数字是否合法
    private boolean isValid(String s, int start, int end) {
        // 0开头的数字不合法
        if (s.charAt(start) == '0' && start != end) return false;
        // 大于三位数
        if (end - start > 2) return false;
        // 大于255
        return Integer.parseInt(s.substring(start, end + 1)) <= 255;
    }
}

// 直接修改原来的字符串
class Solution93_02 {
    List<String> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        // 剪枝
        if (s.length() < 4 || s.length() > 12) return result;
        backtracking(s, 0, 0);
        return result;
    }

    // number表示stringBuilder中ip段的数量
    public void backtracking(String s, int startIndex, int number) {
        // 逗点数量为3时，分隔结束
        if (number == 3) {
            if (isValid(s, startIndex, s.length() - 1)) {
                // 判断第四段子字符串是否合法，如果合法就放进result中
                result.add(s);
            }
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            // 判断 [startIndex,i] 这个区间的子串是否合法
            if (isValid(s, startIndex, i)) {
                s = s.substring(0, i + 1) + "." + s.substring(i + 1);
                number++;
                backtracking(s, i + 2, number);
                number--;
                s = s.substring(0, i + 1) + s.substring(i + 2);
            } else break;
        }
    }

    // 判断字符串s在左闭右闭区间[start, end]所组成的数字是否合法
    private boolean isValid(String s, int start, int end) {
        if (start > end) return false;
        // 0开头的数字不合法
        if (s.charAt(start) == '0' && start != end) return false;
        // 大于三位数
        if (end - start > 2) return false;
        int num = 0;
        for (int i = start; i <= end; i++) {
            // 遇到非数字字符不合法
            if (s.charAt(i) > '9' || s.charAt(i) < '0') return false;
            num = num * 10 + s.charAt(i) - '0';
            if (num > 255) return false;
        }

        return true;
    }
}