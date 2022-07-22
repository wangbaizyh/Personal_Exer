package com.geek.leetcode.greed;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-15 16:07
 * 135. 分发糖果
 * https://leetcode.cn/problems/candy/
 *
 * 总结：
 * 采用了两次贪心的策略：
 * 一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
 * 一次是从右到左遍历，只比较左边孩子评分比右边大的情况。
 * 这样从局部最优推出了全局最优，即：相邻的孩子中，评分高的孩子获得更多的糖果。
 *
 */
public class Code135 {
    @Test
    public void test() {
        int candy = new Solution135().candy(new int[]{1, 2, 2});
        System.out.println(candy);
    }

}

// 贪心算法
// 如果两边一起考虑一定会顾此失彼。
// 局部最优：只要右边评分比左边大，右边的孩子就多一个糖果
// 全局最优：相邻的孩子中，评分高的右孩子获得比左边孩子更多的糖果
// 再确定左孩子大于右孩子的情况（从后向前遍历）
// 确定左孩子大于右孩子的情况一定要从后向前遍历！
// 又要进行贪心！
// 局部最优：取candyVec[i + 1] + 1 和 candyVec[i] 最大的糖果数量，保证第i个小孩的糖果数量即大于左边的也大于右边的
// 全局最优：相邻的孩子中，评分高的孩子获得更多的糖果
// candyVec[i]只有取最大的才能既保持对左边candyVec[i - 1]的糖果多，也比右边candyVec[i + 1]的糖果多
class Solution135 {
    public int candy(int[] ratings) {
        int[] candyVec = new int[ratings.length];
        Arrays.fill(candyVec, 1);
        // 从前向后
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candyVec[i] = candyVec[i - 1] + 1;
            }
        }

        // 从后向前
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candyVec[i] = Math.max(candyVec[i], candyVec[i + 1] + 1);
            }
        }

        int result = 0;
        for (int candy : candyVec) {
            result += candy;
        }

        return result;
    }
}