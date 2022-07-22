package com.geek.jianzhi.dp;

import java.util.ArrayList;

/**
 * @author G.E.E.K.
 * @create 2022-06-28 18:00
 * 剑指 Offer 62. 圆圈中最后剩下的数字
 * https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
 *
 * 思路：约瑟夫环问题 -- 动态规划 (数学问题)
 *
 */
public class Offer62 {

}

// 动态规划
// 数学解法 -- 数学推理
// https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
class Solution62 {
    public int lastRemaining(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个元素，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }

        return ans;
    }
}


// 模拟链表
class Solution62_1 {
    public int lastRemaining(int n, int m) {
        // 使用数组链表来存储数字环
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        // 第一次开始下标
        int idx = 0;

        // 删除n-1次
        while (n > 1) {
            // 删除第m个元素
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }

        // 得到最后剩下的元素
        return list.get(0);
    }
}





