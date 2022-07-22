package com.geek.leetcode.dictOrder;

/**
 * @author G.E.E.K.
 * @create 2022-04-27 11:03
 * 440. 字典序的第K小数字
 * https://leetcode-cn.com/problems/k-th-smallest-in-lexicographical-order/
 *
 * 字节考题
 *
 * 思路：字典树 十叉树先序遍历
 *
 * 前序遍历 + 字典树
 *
 */
public class Code440 {

}

// 先序遍历 + 限制条件 + 十叉树的思想
class Solution440 {
    public int findKthNumber(int n, int k) {
        // 首先遍历以1开头的数字
        // 由于cur可能会很大,因此int可能计算过程中会溢出,用long类型
        long cur = 1;
        // 因为1遍历了,因此k--
        k--;
        // 当且仅当k > 0(还未遍历到第k个的时候)循环
        while (k > 0) {
            // 获取以cur开头的子节点合格(<=n)数目nodes
            int nodes = getNodes(n, cur);
            // 若nodes <= k的话说明把这nodes个节点分完都还没到k
            // 向同层右边的树走
            if (nodes <= k) {
                // cur向右走
                cur++;
                // 抵消掉nodes个节点
                k -= nodes;
            } else {
                // 若nodes > k的话说明nodes个节点够分
                // 往下遍历
                // cur往下走
                cur *= 10;
                // 将cur计算进k
                k--;
            }
        }

        // 最后cur会停留在第k小的数上
        return (int) cur;
    }

    /*
    计算[1,n]内以cur为根(开头)的十叉树节点个数
    */
    private int getNodes(int n, long cur) {
        // next表示cur右边的数, 此时cur = 10, next = 11
        long next = cur + 1;
        // 统计合格的节点个数
        int total = 0;
        // 当cur <= n时可以进入循环
        while (cur <= n) {
            // 这里是最关键的一步:当n不在cur层时,该层有效节点数目为next - cur(全部都要了)
            // 当n在cur层时,该层有效节点数目为n - cur + 1(要一部分)
            // 统一起来就是取最小值
            total += Math.min(n - cur + 1, next - cur);
            // cur与next均向下计算
            cur *= 10;
            next *= 10;
        }

        return total;
    }
}