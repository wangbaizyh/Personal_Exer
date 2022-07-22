package com.geek.leetcode.ranklist.weak298;

/**
 * @author G.E.E.K.
 * @create 2022-06-19 10:33
 * 2390. 兼具大小写的最好英文字母
 * https://leetcode.cn/problems/greatest-english-letter-in-upper-and-lower-case/
 *
 * 思路：使用数组进行原地Hash
 *
 */
public class Code2309 {

}

class Solution2309 {
    public String greatestLetter(String s) {
        int[][] map = new int[26][2];

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 'a') {
                map[s.charAt(i) - 'a'][0] = 1;
            } else {
                map[s.charAt(i) - 'A'][1] = 1;
            }
        }

        String ans = "";
        for (int i = 0; i < 26; i++) {
            if (map[i][0] == 1 && map[i][1] == 1) {
                char tmp = (char) ('A' + i);
                ans = String.valueOf(tmp);
            }
        }

        return ans;
    }
}