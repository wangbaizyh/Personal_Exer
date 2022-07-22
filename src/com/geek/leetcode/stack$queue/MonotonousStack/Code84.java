package com.geek.leetcode.stack$queue.MonotonousStack;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-29 15:54
 * 84. 柱状图中最大的矩形
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 *
 * 思路：求左右的最高柱子
 *
 */
public class Code84 {

}

// 双指针
class Solution84 {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int left = i;

            // 遇到比自己小的就终止
            for (; left >= 0; left--) {
                if (heights[left] < heights[i]) break;
            }

            int right = i;
            for (; right < heights.length; right++) {
                if (heights[right] < heights[i]) break;
            }

            int w = right - left - 1;
            int h = heights[i];

            maxArea = Math.max(maxArea, w * h);
        }

        return maxArea;
    }
}

// dp
// 记录每个柱子 左边第一个小于该柱子的下标 右边第一个小于该柱子的下标
// 效率最高
class Solution84_01 {
    public int largestRectangleArea(int[] heights) {
        int size = heights.length;
        int[] minLeftIndex = new int[size];
        int[] minRightIndex = new int[size];

        // 记录每个柱子 左边第一个小于该柱子的下标
        minLeftIndex[0] = -1;
        for (int i = 1; i < size; i++) {
            int t = i - 1;
            // 不断向左寻找
            while (t >= 0 && heights[t] >= heights[i]) t = minLeftIndex[t];
            minLeftIndex[i] = t;
        }

        // 记录每个柱子 右边第一个小于该柱子的下标
        minRightIndex[size - 1] = size;
        for (int i = size - 2; i >= 0; i--) {
            int t = i + 1;
            // 不断向右寻找
            while (t < size && heights[t] >= heights[i]) t = minRightIndex[t];
            minLeftIndex[i] = t;
        }

        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {

            int w = minRightIndex[i] - minLeftIndex[i] - 1;
            int h = heights[i];

            maxArea = Math.max(maxArea, w * h);
        }

        return maxArea;
    }
}

// 单调栈
// 栈顶和栈顶的下一个元素以及要入栈的三个元素组成了我们要求最大面积的高度和宽度
class Solution84_02 {
    public int largestRectangleArea(int[] heights) {
        // 单调递减栈
        Stack<Integer> stack = new Stack<>();
        // 因为要算的是面积，所以需要对数组进行扩容，头尾加入0。
        // 数组扩容，在头和尾各加入一个元素
        int[] newHeights = new int[heights.length + 2];
        newHeights[0] = 0;
        newHeights[newHeights.length - 1] = 0;
        for (int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }

        heights = newHeights;

        stack.push(0);
        int sum = 0;

        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > heights[stack.peek()]) {
                stack.push(i);
            } else if (heights[i] == heights[stack.peek()]) {
                stack.pop();
                stack.push(i);
            } else {
                while (heights[i] < heights[stack.peek()]) {
                    int mid = stack.pop();
                    int w = i - stack.peek() - 1;
                    int h = heights[mid];
                    sum = Math.max(sum, w * h);
                }

                stack.push(i);
            }
        }

        return sum;
    }
}