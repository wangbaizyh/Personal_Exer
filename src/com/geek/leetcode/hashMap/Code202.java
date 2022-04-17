package com.geek.leetcode.hashMap;

import java.util.HashSet;

/**
 * @author G.E.E.K.
 * @create 2022-04-17 19:33
 * 202. 快乐数
 * https://leetcode-cn.com/problems/happy-number/
 *
 * hashSet
 *
 */
public class Code202 {

}

class Solution202 {
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();

        while (n != 1 && !set.contains(n)) {
            set.add(n);
            n = getNextNumber(n);
        }

        return n == 1;
    }

    // 计算每位数字的平方和
    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int tmp = n % 10;
            res += tmp * tmp;
            n /= 10;
        }

        return res;
    }
}