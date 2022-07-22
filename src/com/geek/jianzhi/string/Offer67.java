package com.geek.jianzhi.string;

/**
 * @author G.E.E.K.
 * @create 2022-06-29 15:04
 * 剑指 Offer 67. 把字符串转换成整数
 * https://leetcode.cn/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
 *
 * 思路：模拟
 *
 */
public class Offer67 {

}

class Solution67 {
    public int strToInt(String str) {
        str = str.trim();
        if (str.equals("") ) return 0;

        boolean sign = true;

        if (str.charAt(0) == '-') sign = false;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            str = str.substring(1);
        }

        long ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                ans *= 10;
                ans += str.charAt(i) - '0';

                if (sign && ans >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (!sign && -ans <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
            } else {
                break;
            }
        }

        if (sign) return (int) ans;
        return (int) -ans;
    }
}

