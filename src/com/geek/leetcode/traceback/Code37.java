package com.geek.leetcode.traceback;

/**
 * @author G.E.E.K.
 * @create 2022-05-13 11:22
 * 37. 解数独
 * https://leetcode.cn/problems/sudoku-solver/
 *
 * 思路：二维回溯
 *
 */
public class Code37 {

}

class Solution37 {
    public void solveSudoku(char[][] board) {
        backtracking(board);
    }

    // 返回值为boolean，即找到数独的唯一解则立即返回
    private boolean backtracking(char[][] board) {
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i < board.length; i++) {            // 遍历行
            for (int j = 0; j < board[0].length; j++) {     // 遍历列
                if (board[i][j] != '.') continue;           // 跳过原始数字
                for (char k = '1'; k <= '9'; k++) {         // 遍历9个数字
                    if (isValid(i, j, k, board)) {
                        board[i][j] = k;
                        // 如果找到合适一组立刻返回
                        if (backtracking(board)) return true;   // 如果找到一组解则立即返回
                        board[i][j] = '.';
                    }
                }
                // 9个数都试完了，都不行，那么就返回false，不存在解法
                return false;
            }
        }

        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    private boolean isValid(int row, int col, char val, char[][] board) {
        // 检查行
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == val) {
                return false;
            }
        }

        // 检查列
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == val) {
                return false;
            }
        }

        // 检查9宫格
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == val) {
                    return false;
                }
            }
        }

        // 符合条件
        return true;
    }
}