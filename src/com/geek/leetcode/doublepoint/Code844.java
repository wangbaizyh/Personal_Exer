package com.geek.leetcode.doublepoint;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-04-26 21:07
 * 844. 比较含退格的字符串
 * https://leetcode-cn.com/problems/backspace-string-compare/
 *
 * 双指针法：快慢指针法
 *
 */
public class Code844 {

    @Test
    public void test() {
        boolean b = new Solution844().backspaceCompare("ab#c####", "ad#c#######");
        System.out.println(b);
    }
}

class Solution844 {
    public boolean backspaceCompare(String s, String t) {
        String s1 = convert(s);
        String t1 = convert(t);
        return s1.equals(t1);
    }

    private String convert(String s) {
        int slow = 0;
        char[] chars = s.toCharArray();

        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] == '#') {
                slow -= 1;
            } else {
                if (slow < 0) slow = 0;
                chars[slow] = chars[fast];
                slow++;
            }
        }

        if (slow < 0) {
            return "";
        }

        return new String(chars).substring(0, slow);
    }
}