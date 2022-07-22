package com.geek.jianzhi.binarySearch;

/**
 * @author G.E.E.K.
 * @create 2022-05-30 11:07
 * 剑指 Offer 04. 二维数组中的查找
 * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 *
 * 思路：二维数组的二分查找
 *
 */
public class Offer04 {

}


// 二维数组二分查找
// 从右上角开始查找
// 1.当前元素等于目标值，则返回true
// 2.当前元素大于目标值，则移到左边一列
// 3.当前元素小于目标值，则移到下边一行
class Solution04 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 边界条件
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }

        int rows = matrix.length, columns = matrix[0].length;
        // 初始化为右上角的元素
        int row = 0, column = columns - 1;
        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }

        return false;
    }
}

// 矩阵逆时针旋转 45° ，类似于 二叉搜索树 。
// “根节点” 对应的是矩阵的 “左下角” 和 “右上角” 元素，本文称之为 标志数 ，
// 以 matrix 中的 左下角元素 为标志数 flag
// 若 flag > target ，则 target 一定在 flag 所在 行的上方 ，即 flag 所在行可被消去。
// 若 flag < target ，则 target 一定在 flag 所在 列的右方 ，即 flag 所在列可被消去。
class Solution04_01 {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 边界条件
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return false;
        }

        int rows = matrix.length, columns = matrix[0].length;
        // 初始化为右上角的元素
        int row = rows - 1, column = 0;
        while (row >= 0 && column < columns) {
            int num = matrix[row][column];

            if (num == target) {
                return true;
            } else if (num > target) {
                row--;
            } else {
                column++;
            }
        }

        return false;
    }
}