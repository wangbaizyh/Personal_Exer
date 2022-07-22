package com.geek.jianzhi.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-06-14 22:03
 * 剑指 Offer 29. 顺时针打印矩阵
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 *
 * 思路：螺旋矩阵
 *
 */
public class Offer29 {

}

class Solution29 {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int n = matrix.length, m = matrix[0].length;
        int count = 0;
        int[] ans = new int[n * m];
        int top = 0, bottom = n - 1, left = 0, right = m - 1;

        while (count < n * m) {
            for (int i = left; i <= right; i++) {
                ans[count++] = matrix[top][i];
            }
            top++;
            if (count == m * n) break;

            for (int i = top; i <= bottom; i++) {
                ans[count++] = matrix[i][right];
            }
            right--;
            if (count == m * n) break;

            for (int i = right; i >= left; i--) {
                ans[count++] = matrix[bottom][i];
            }
            bottom--;
            if (count == m * n) break;

            for (int i = bottom; i >= top; i--) {
                ans[count++] = matrix[i][left];
            }
            left++;
            if (count == m * n) break;
        }

        return ans;
    }
}