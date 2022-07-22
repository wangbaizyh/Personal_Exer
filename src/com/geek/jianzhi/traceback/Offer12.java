package com.geek.jianzhi.traceback;

/**
 * @author G.E.E.K.
 * @create 2022-06-03 16:02
 * 剑指 Offer 12. 矩阵中的路径
 * https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/
 *
 * 思路：深度优先搜索（DFS） + 剪枝
 * 经典的矩阵搜索问题
 *
 * 深度优先搜索：
 *      可以理解为暴力法遍历矩阵中所有字符串可能性。
 *      DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
 * 剪枝：
 *      在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况
 *      （例如：此矩阵元素和目标字符不同、此元素已被访问），
 *      则应立即返回，称之为 可行性剪枝 。
 *
 * DFS解析：
 *      递归参数：
 *          当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
 *      终止条件：
 *          返回 false ： (1) 行或列索引越界
 *                       (2) 当前矩阵元素与目标字符不同
 *                      (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。
 *          返回 true ： k = len(word) - 1 ，即字符串 word 已全部匹配。
 * 递推工作：
 *      标记当前矩阵元素：
 *          将 board[i][j] 修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。
 *      搜索下一单元格：
 *          朝当前元素的 上、下、左、右 四个方向开启下层递归，
 *          使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），
 *          并记录结果至 res 。
 *      还原当前矩阵元素：
 *          将 board[i][j] 元素还原至初始值，即 word[k] 。
 *
 * 返回值：
 *      返回布尔量 res ，代表是否搜索到目标字符串。
 *
 */
public class Offer12 {

}

// 回溯 + 剪枝
class Solution12 {
    boolean[][] used;

    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        int n = board.length, m = board[0].length;
        // 标记已经访问过
        used = new boolean[n][m];
        // 遍历以各个位置为起始点的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }

        return false;
    }

    // 寻找一条路径就返回，因而有返回值
    private boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        // 回溯结束条件
        // 剪枝：越界 + 不相等 + 已经使用
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[k] || used[i][j]) return false;
        // 抵达字符串终点
        if (k == words.length - 1) return true;

        used[i][j] = true;
        // 递归遍历：下、上、左、右
        boolean ans = dfs(board, words, i + 1, j, k + 1) || dfs(board, words, i - 1, j, k + 1) ||
                dfs(board, words, i, j + 1, k + 1) || dfs(board, words, i, j - 1, k + 1);
        used[i][j] = false;

        return ans;
    }
}

// 回溯 + 剪枝
class Solution12_1 {
    // 方向
    private static final int[][] dirs = {{0, 1},{0 ,-1}, {1, 0}, {-1, 0}};
    boolean[][] used;
    int n, m;
    char[][] board;
    char[] words;

    public boolean exist(char[][] board, String word) {
        words = word.toCharArray();
        n = board.length;
        m = board[0].length;
        this.board = board;

        // 标记已经访问过
        used = new boolean[n][m];
        // 遍历以各个位置为起始点的情况
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == words[0]) {
                    used[i][j] = true;
                    if (dfs(i, j, 1)) return true;
                    used[i][j] = false;
                }
            }
        }

        return false;
    }

    // 寻找一条路径就返回，因而有返回值
    private boolean dfs(int i, int j, int k) {
        // 抵达字符串终点
        if (k == words.length) return true;
        boolean ans = false;

        for (int[] dir : dirs) {
            int nextI = i + dir[0], nextJ = j + dir[1];
            // 剪枝：越界 + 不相等 + 已经使用
            if (nextI >= n || nextI < 0 || nextJ >= m || nextJ < 0 || board[nextI][nextJ] != words[k] || used[nextI][nextJ]) {
                continue;
            }

            used[nextI][nextJ] = true;
            ans = ans || dfs(nextI, nextJ, k + 1);
            used[nextI][nextJ] = false;
        }

        return ans;
    }
}
