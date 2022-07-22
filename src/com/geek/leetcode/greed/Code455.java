package com.geek.leetcode.greed;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-13 15:25
 * 455. 分发饼干
 * https://leetcode.cn/problems/assign-cookies/
 *
 * 贪心思路：大饼干满足胃口大的小孩
 *
 */
public class Code455 {

}

// 小饼干喂饱小胃口
class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int sum = 0;
        for (int i = 0; i < s.length; i++) {
            if (sum < g.length && s[i] >= g[sum]) sum++;
        }

        return sum;
    }
}

// 大饼干喂饱大胃口
class Solution455_01 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int index = s.length - 1;
        int result = 0;
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }

        return result;
    }
}