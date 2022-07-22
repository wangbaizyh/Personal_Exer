package com.geek.jianzhi.string;

/**
 * @author G.E.E.K.
 * @create 2022-06-27 15:23
 * 剑指 Offer 58 - I. 翻转单词顺序
 * https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/
 *
 */
public class Offer58_1 {

}

class Solution58_1 {
    public String reverseWords(String s) {
        s = s.trim();
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = strs.length - 1; i >= 0; i--) {
            if (!strs[i].equals("")) {
                if (i == 0) {
                    sb.append(strs[i]);
                } else {
                    sb.append(strs[i]);
                    sb.append(" ");
                }
            }
        }

        return sb.toString();
    }
}