package com.geek.leetcode.doublepoint;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-07-11 20:47
 * 54. 螺旋矩阵
 * https://leetcode.cn/problems/spiral-matrix/
 *
 */
public class Code54 {

}

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int top = 0, bottom = m - 1;
        int left = 0, right = n - 1;
        int count = 0;
        List<Integer> ans = new ArrayList<>();

        while (count <= m * n) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[top][i]);
                count++;
            }
            top++;
            if (count == m * n) break;

            for (int i = top; i <= bottom; i++) {
                ans.add(matrix[i][right]);
                count++;
            }
            right--;
            if (count == m * n) break;

            for (int i = right; i >= left; i--) {
                ans.add(matrix[bottom][i]);
                count++;
            }
            bottom--;
            if (count == m * n) break;

            for (int i = bottom; i >= top; i--) {
                ans.add(matrix[i][left]);
                count++;
            }
            left++;
            if (count == m * n) break;
        }

        return ans;
    }
}