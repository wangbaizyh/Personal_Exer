package com.geek.jianzhi.traceback;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-06-03 16:50
 * 剑指 Offer 13. 机器人的运动范围
 * https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 *
 * 思路：从原点开始出发，出发点固定，可以使用BFS或者DFS
 *
 */
public class Offer13 {

}

/**
 * 方法一：BFS
 * 使用队列来实现
 */
class Solution13 {
    public int movingCount(int m, int n, int k) {
        // 只有原点
        if (k == 0) return 1;
        // 队列存储当前矩阵所在的坐标
        Queue<int[]> queue = new LinkedList<>();
        // 向下和向右的方向数组
        int[] dx = {0, 1};
        int[] dy = {1, 0};
        // 访问数组
        boolean[][] used = new boolean[m][n];
        // 初始化：从开始原点开始移动
        queue.offer(new int[]{0, 0});
        used[0][0] = true;
        int ans = 1;

        // 遍历访问过的节点
        while (!queue.isEmpty()) {
            // 获取当前位置
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            // 遍历向右和向下移动
            for (int i = 0; i < 2; i++) {
                int curX = dx[i] + x;
                int curY = dy[i] + y;
                // 剪枝：越界 + 已访问 + 不可行
                if (curX >= m || curY >= n || used[curX][curY] || get(curX) + get(curY) > k ) {
                    continue;
                }

                // 当前位置进队列
                queue.offer(new int[]{curX, curY});
                used[curX][curY] = true;
                ans++;
            }
        }

        return ans;
    }

    // 求各个位数之和
    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }

        return res;
    }
}

class Solution13_2 {
    // 方向数组
    private static final int[][] dirs = {{0, 1}, {1 ,0}};

    public int movingCount(int m, int n, int k) {
        // 只有原点
        if (k == 0) return 1;
        // 队列存储当前矩阵所在的坐标
        Queue<int[]> queue = new LinkedList<>();
        // 访问数组
        boolean[][] used = new boolean[m][n];
        // 初始化：从开始原点开始移动
        queue.offer(new int[]{0, 0});
        used[0][0] = true;
        int ans = 1;

        // 遍历访问过的节点
        while (!queue.isEmpty()) {
            // 获取当前位置
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            // 遍历向下和向右移动
            for (int[] dir : dirs) {
                int curX = x + dir[0], curY = y + dir[1];
                // 剪枝：越界 + 已访问 + 不可行
                if (curX >= m || curY >= n || used[curX][curY] || get(curX) + get(curY) > k ) {
                    continue;
                }

                // 当前位置进队列
                queue.offer(new int[]{curX, curY});
                used[curX][curY] = true;
                ans++;
            }
        }

        return ans;
    }

    // 求各个位数之和
    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }

        return res;
    }
}

/**
 * 方法二：DFS 深度优先遍历（回溯）
 * 使用递归
 */
class Solution13_01 {
    int ans = 1;
    boolean[][] used;

    public int movingCount(int m, int n, int k) {
        // 只有原点
        if (k == 0) return 1;
        // 访问数组
        used = new boolean[m][n];
        // 深度优先遍历
        dfs(0, 0, m, n, k);

        return ans;
    }

    private void dfs(int x, int y, int m, int n, int k) {
        // 剪枝：越界 + 已访问 + 不可行
        if (x >= m || y >= n || used[x][y] || get(x) + get(y) > k) return;

        // 标记已访问
        used[x][y] = true;
        ans++;

        // 向右移动
        dfs(x, y + 1, m, n, k);
        // 向下移动
        dfs(x + 1, y, m, n, k);
    }

    // 求各个位数之和
    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }

        return res;
    }
}

class Solution13_03 {
    // 方向数组
    private static final int[][] dirs = {{0, 1}, {1 ,0}};
    int ans = 1;
    boolean[][] used;
    int m, n, k;

    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;

        // 只有原点
        if (k == 0) return 1;
        // 访问数组
        used = new boolean[m][n];
        // 深度优先遍历
        dfs(0, 0);

        return ans;
    }

    private void dfs(int x, int y) {
        for (int[] dir : dirs) {
            int nextX = x + dir[0], nextY = y + dir[1];
            // 剪枝：越界 + 已访问 + 不可行
            if (nextX >= m || nextY >= n || used[nextX][nextY] || get(nextX) + get(nextY) > k) {
                continue;
            }

            used[nextX][nextY] = true;
            dfs(nextX, nextY);
            ans++;
        }
    }

    // 求各个位数之和
    private int get(int x) {
        int res = 0;
        while (x != 0) {
            res += x % 10;
            x /= 10;
        }

        return res;
    }
}