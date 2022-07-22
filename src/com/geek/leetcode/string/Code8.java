package com.geek.leetcode.string;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 16:07
 * 8. 字符串转换整数 (atoi)
 * https://leetcode.cn/problems/string-to-integer-atoi/
 *
 * 思路：模拟
 */
public class Code8 {
    @Test
    public void test() {
        int ans = new Solution8().myAtoi("-91283472332");
        System.out.println(ans);
    }
}

class Solution8 {
    public int myAtoi(String s) {
        // 第一步 去除空格
        String str = s.trim();
        if (str.length() == 0) return 0;
        long res = 0;

        // 第二步 判断正负符号
        boolean flag = false;
        if (str.charAt(0) == '-') {
            flag = true;
        }
        if (str.charAt(0) == '+' || str.charAt(0) == '-') str = str.substring(1);

        // 第三步 读取数字
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                // 处理数字
                res = res * 10 + str.charAt(i) - '0';
                // 判断越界
                if (flag && -res <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
                if (!flag && res >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
            } else {
                // 退出
                break;
            }
        }

        if (flag) return (int) -res;
        return (int) res;
    }
}