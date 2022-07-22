package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-15 17:19
 * 860. 柠檬水找零
 * https://leetcode.cn/problems/lemonade-change/
 *
 */
public class Code860 {

}

class Solution860 {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) five++;

            if (bill == 10) {
                if (five > 0) {
                    five--;
                    ten++;
                } else return false;
            }

            if (bill == 20) {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five > 2) {
                    five = five - 3;
                } else return false;
            }
        }

        return true;
    }
}
