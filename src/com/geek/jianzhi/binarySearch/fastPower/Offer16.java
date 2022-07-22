package com.geek.jianzhi.binarySearch.fastPower;

/**
 * @author G.E.E.K.
 * @create 2022-06-04 23:30
 * 剑指 Offer 16. 数值的整数次方
 * https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 *
 * 思路：快速幂法->将时间复杂度降低至 O(log_2 n)
 * 二分思想的应用
 *
 * 快速幂解析（二分法角度）：
 * 快速幂实际上是二分思想的一种应用。
 * 算法流程：
 * 1. 当 x = 0 时：直接返回 0 （避免后续 x = 1 / x 操作报错）。
 * 2. 初始化 res = 1 ；
 * 3. 当 n < 0 时：把问题转化至 n≥0 的范围内，即执行 x = 1/x ，n = - n；
 * 4. 循环计算：当 n = 0 时跳出；
 *      当 n & 1 = 1 时：将当前 x 乘入 res （即 res *= x）；
 *      执行 x = x^2（即 x *= x）；
 *      执行 n 右移一位（即 n >>= 1）。
 * 5. 返回 res 。
 *
 * 注意：
 * Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，
 * 因此当 n = -2147483648 时执行 n=−n 会因越界而赋值出错。
 * 解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可。
 *
 */
public class Offer16 {

}

class Solution16 {
    public double myPow(double x, int n) {
        // 处理n等于0的情况
        if (x == 0) return 0;
        // 转变为long类型，为了处理-2^31的越界情况
        long b = n;
        // 初始化结果
        double res = 1.0;
        // 处理n小于0的情况, 转换为正数
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }

        // 快速幂开始
        // 循环计算（二分）
        while (b > 0) {
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }

        return res;
    }
}