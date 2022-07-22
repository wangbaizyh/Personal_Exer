package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-18 17:27
 * 738. 单调递增的数字
 * https://leetcode.cn/problems/monotone-increasing-digits/
 *
 */
public class Code738 {

}

// 局部最优：遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，可以保证这两位变成最大单调递增整数。
// 全局最优：得到小于等于N的最大单调递增的整数。
// 从后向前遍历
class Solution738 {
    public int monotoneIncreasingDigits(int n) {
        char[] strNum = String.valueOf(n).toCharArray();
        // flag用来标记赋值9从哪里开始
        // 设置为这个默认值，为了防止第二个for循环在flag没有被赋值的情况下执行
        int flag = strNum.length;
        for (int i = strNum.length - 1; i > 0; i--) {
            if (strNum[i - 1] > strNum[i]) {
                flag = i;
                strNum[i - 1]--;
            }
        }

        for (int i = flag; i < strNum.length; i++) {
            strNum[i] = '9';
        }

        return Integer.parseInt(new String(strNum));
    }
}
