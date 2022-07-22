package com.geek.leetcode.binarySearch;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-07-18 20:35
 * 69. x 的平方根
 * https://leetcode.cn/problems/sqrtx/
 *
 * 思路：二分查找
 */
public class Code69 {
    @Test
    public void test() {
        double ans = new Solution69_1().mySqrt(2);
        System.out.println(ans);
        double cmp = Math.sqrt(2);
        System.out.println(cmp);
    }
}

class Solution69 {
    public int mySqrt(int x) {
        int low = 0, high = (x >> 1) + 1;
        // 相等是为了处理不是正数方根的时候，高低位位置互换，取高位，得到整数部分
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if ((long) mid * mid == x) return mid;
            else if ((long) mid * mid > x) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 返回保留的整数部分
        return high;
    }
}

/**
 * 拓展延申：改编到N位小数？
 * 控制左右指针移动的步长，比如精确到小数点后10位，二分，每次移动1e-10。
 */
class Solution69_1 {
    public double mySqrt(int x) {
        double low = 0.0, high = (x >> 1) + 1;
        // 相等是为了处理不是正数方根的时候，高低位位置互换，取高位，得到整数部分
        while (low <= high) {
            double mid = low + (high - low) / 2;
            if (mid * mid == x) return mid;
            else if (mid * mid > x) {
                high = mid - 1e-10;
            } else {
                low = mid + 1e-10;
            }
        }
        // 返回精度为x位的平方根
        return high;
    }
}

