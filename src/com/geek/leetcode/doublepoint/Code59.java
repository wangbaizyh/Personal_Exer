package com.geek.leetcode.doublepoint;

/**
 * @author G.E.E.K.
 * @create 2022-05-09 16:10
 * 59. 螺旋矩阵 II
 * https://leetcode.cn/problems/spiral-matrix-ii/
 *
 */
public class Code59 {

}

class Solution59 {
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int top = 0, bottom = n - 1, left = 0, right = n - 1;
        int count = 1;

        while (count <= n * n) {
            for (int i = left; i <= right; i++) {
                result[top][i] = count++;
            }

            top++;

            for (int i = top; i <= bottom; i++) {
                result[i][right] = count++;
            }

            right--;

            for (int i = right; i >= left; i--) {
                result[bottom][i] = count++;
            }

            bottom--;

            for (int i = bottom; i >= top; i--) {
                result[i][left] = count++;
            }

            left++;
        }

        return result;
    }
}