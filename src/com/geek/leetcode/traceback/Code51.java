package com.geek.leetcode.traceback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-05-13 10:22
 * 51. N 皇后
 * https://leetcode.cn/problems/n-queens/
 *
 *
 */
public class Code51 {

}

class Solution51 {
    List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // 使用二维char数组来初始化原始棋盘
        char[][] chessboard = new char[n][n];
        // 先全部填满'.'
        for (char[] c : chessboard) {
            Arrays.fill(c,'.');
        }
        // 回溯搜索存在的棋盘方案
        backtracking(n, 0, chessboard);
        return result;
    }

    public void backtracking(int n, int row, char[][] chessboard) {
        // 回溯结束：抵达最后一行
        if (row == n) {
            result.add(Array2List(chessboard));
            return;
        }

        // 遍历该行每一列
        for (int col = 0; col < n; col++) {
            // 判断是否符合条件
            if (isValid(row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                // 递归回溯下一层
                backtracking(n, row + 1, chessboard);
                chessboard[row][col] = '.';
            }
        }
    }

    // 将二维char数组转换为列表的形式
    public List<String> Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(new String(c));
        }

        return list;
    }

    boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 因为是一层一层的遍历，所以不用对行进行检查
        // 检查列
        for (int i = 0; i < row; i++) {
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i = row - 1, j= col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i = row - 1, j= col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}