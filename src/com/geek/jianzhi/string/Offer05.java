package com.geek.jianzhi.string;

/**
 * @author G.E.E.K.
 * @create 2022-04-25 14:31
 * 剑指 Offer 05. 替换空格
 * https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
 *
 * 思路：字符串、暴力模拟、双指针、扩展数组空间
 *
 */
public class Offer05 {

}


// 方法一：暴力解法
class Solution05_01 {
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                res.append("%20");
            } else {
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}

// 方法二：扩充数组，双指针法(前后指针法)
class Solution05_02 {
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        // 扩展空间, 空格数量的两倍
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                str.append("  ");
            }
        }

        // 没有空格直接返回
        if (str.length() == 0) {
            return s;
        }

        // 有空格情况 定义两个指针
        // 左指针：指向原始字符串最后一个位置
        int left = s.length() - 1;
        s += str.toString();
        // /右指针：指向扩展字符串的最后一个位置
        int right = s.length() - 1;
        char[] chars = s.toCharArray();
        while (left < right) {
            if (chars[left] == ' '){
                chars[right--] = '0';
                chars[right--] = '2';
                chars[right] = '%';
            } else {
                chars[right] = chars[left];
            }

            left--;
            right--;
        }

        return new String(chars);
    }
}