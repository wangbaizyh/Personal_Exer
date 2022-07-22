package com.geek.jianzhi.traceback;

/**
 * @author G.E.E.K.
 * @create 2022-06-09 21:39
 * 剑指 Offer 17. 打印从1到最大的n位数
 * https://leetcode.cn/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 *
 * 思路：大数越界问题
 * 暴力、回溯
 *
 *
 */
public class Offer17 {

}

// 不考虑大数越界
class Solution17 {
    public int[] printNumbers(int n) {
        int end = (int) Math.pow(10, n) - 1;
        int[] res = new int[end];
        for (int i = 0; i < end; i++) {
            res[i] = i + 1;
        }

        return res;
    }
}

/**
 * 大数打印解法：
 * 需要解决以下三个问题：
 * 1. 表示大数的变量类型：
 *      无论是 short / int / long ... 任意变量类型，数字的取值范围都是有限的。
 *      因此，大数的表示应用字符串 String 类型。
 * 2. 生成数字的字符串集：
 *      使用 int 类型时，每轮可通过 +1 生成下个数字，而此方法无法应用至 String 类型。
 *      并且， String 类型的数字的进位操作效率较低，
 *      例如 "9999" 至 "10000" 需要从个位到千位循环判断，进位 4 次。
 *
 *      观察可知，生成的列表实际上是 n 位 0 - 9 的 全排列 ，
 *      因此可避开进位操作，通过递归生成数字的 String 列表。
 * 3. 递归生成全排列：
 *      基于分治算法的思想，先固定高位，向低位递归，当个位已被固定时，添加数字的字符串。
 *      例如当 n=2 时（数字范围 1−99 ），固定十位为 0 - 9 ，按顺序依次开启递归，
 *      固定个位 0 - 9 ，终止递归并添加数字字符串。
 *
 * 问题：删除高位多余的 0
 * 解决：
 * 1. 删除高位多余的 0 ：
 *      字符串左边界定义： 声明变量 start 规定字符串的左边界，
 *      以保证添加的数字字符串 num[start:] 中无高位多余的 0 。
 *      例如当 n = 2 时， 1 - 9 时 start = 1 ， 10 - 99 时 start = 0 。
 *
 *      左边界 start 变化规律： n − start=nine
 *
 *      统计 nine 的方法： 固定第 x 位时，当 i = 9 则执行 nine = nine + 1 ，
 *      并在回溯前恢复 nine = nine - 1 。
 *
 * 2. 列表从 1 开始：
 *      在以上方法的基础上，添加数字字符串前判断其是否为 "0" ，若为 "0" 则直接跳过。
 *
 */
class Solution17_01 {
    StringBuilder res;
    int nine = 0, start, n;
    char[] num, loop = {'0','1','2','3','4','5','6','7','8','9'};


    public String printNumbers(int n) {
        this.n = n;
        // 数字字符串集
        res = new StringBuilder();
        // 定义长度为 n 的字符列表
        num = new char[n];
        start = n - 1;
        // 开启全排列递归
        dfs(0);
        // 删除最后多余的逗号
        res.deleteCharAt(res.length() - 1);
        // 转化为字符串并返回
        return res.toString();
    }

    // 全排列 回溯
    void dfs(int x) {
        // 终止条件：已固定完所有位
        if (x == n) {
            // 去除高位0
            String s = String.valueOf(num).substring(start);
            // 拼接 num 并添加至 res 尾部，使用逗号隔开
            if (!s.equals("0")) res.append(s + ",");
            // 当前位为9的时候，开始位往左移一位
            if (n - start == nine) start--;
            return;
        }

        // 遍历 ‘0‘ - ’9‘
        for (char c : loop) {
            if (c == '9') nine++;
            // 固定第 x 位为 i
            num[x] = c;
            // 开启固定第 x + 1 位
            dfs(x + 1);
        }

        nine--;
    }
}

// 回溯
class Solution17_02 {
    int[] res;
    int nine = 0, count = 0, start, n;
    char[] num, loop = {'0','1','2','3','4','5','6','7','8','9'};


    public int[] printNumbers(int n) {
        this.n = n;
        res = new int[(int) Math.pow(10, n) - 1];
        num = new char[n];
        start = n - 1;
        dfs(0);
        return res;
    }

    // 全排列 回溯
    void dfs(int x) {
        if (x == n) {
            String s = String.valueOf(num).substring(start);
            // 列表从1开始
            if (!s.equals("0")) {
                res[count++] = Integer.parseInt(s);
            }
            // 当前位为9的时候，开始位往左移一位
            if (n - start == nine) start--;
            return;
        }

        for (char c : loop) {
            if (c == '9') nine++;
            num[x] = c;
            dfs(x + 1);
        }

        nine--;
    }
}