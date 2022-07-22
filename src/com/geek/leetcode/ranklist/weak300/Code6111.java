package com.geek.leetcode.ranklist.weak300;

/**
 * @author G.E.E.K.
 * @create 2022-07-03 11:19
 * 6111. 螺旋矩阵 IV
 * https://leetcode.cn/contest/weekly-contest-300/problems/spiral-matrix-iv/
 *
 * 思路：指针
 *
 */
public class Code6111 {
}

class Solution6111 {
    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int count = 0;
        while (count < m * n) {
            for (int i = left; i <= right; i++) {
                if (head == null) ans[top][i] = -1;
                else {
                    ans[top][i] = head.val;
                    head = head.next;
                }
                count++;
            }
            top++;
            if (count == m * n) break;

            for (int i = top; i <= bottom; i++) {
                if (head == null) ans[i][right] = -1;
                else {
                    ans[i][right] = head.val;
                    head = head.next;
                }
                count++;
            }
            right--;
            if (count == m * n) break;

            for (int i = right; i >= left; i--) {
                if (head == null) ans[bottom][i] = -1;
                else {
                    ans[bottom][i] = head.val;
                    head = head.next;
                }
                count++;
            }
            bottom--;
            if (count == m * n) break;

            for (int i = bottom; i >= top; i--) {
                if (head == null) ans[i][left] = -1;
                else {
                    ans[i][left] = head.val;
                    head = head.next;
                }
                count++;
            }
            left++;
            if (count == m * n) break;
        }

        return ans;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}