package com.geek.leetcode.stack$queue.MonotonousStack;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-29 14:13
 * 42. 接雨水
 * https://leetcode.cn/problems/trapping-rain-water/
 *
 * 思路：双指针、dp、单调栈
 * 按列接雨水（双指针、dp）
 * 按行接雨水（单调栈）
 *
 */
public class Code42 {

}

// 双指针法（最好理解）
// 按照列进行计算
// 如果按照列来计算的话，宽度一定是1了，我们再把每一列的雨水的高度求出来就可以了。
// 每一列雨水的高度，取决于，该列 左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度。
// 第一个柱子和最后一个柱子不接雨水。
// 时间复杂度为O(n^2)。 空间复杂度为O(1)。
class Solution42 {
    public int trap(int[] height) {
        int sum = 0;

        for (int i = 0; i < height.length; i++) {
            // 第一个柱子和最后一个柱子不接雨水
            if (i == 0 || i == height.length - 1) continue;

            // 左侧最高的柱子
            int left = height[i];
            for (int l = i - 1; l >= 0; l--) {
                left = Math.max(left, height[l]);
            }

            // 右侧最高的柱子
            int right = height[i];
            for (int r = i + 1; r < height.length; r++) {
                right = Math.max(right, height[r]);
            }

            // 本列能接的雨水
            int h = Math.min(left, right) - height[i];
            sum += h;
        }

        return sum;
    }
}

// dp (对双指针的改进) 效率最高
// 计算每列能接的雨水
class Solution42_01 {
    public int trap(int[] height) {
        int size = height.length;
        if (size <= 2) return 0;

        int[] maxLeft = new int[size];
        int[] maxRight = new int[size];

        // 记录每个柱子左边柱子最大高度
        maxLeft[0] = height[0];
        for (int i = 1; i < size; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }

        // 记录每个柱子右边柱子最大高度
        maxRight[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }


        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            // 第一个柱子和最后一个柱子不接雨水
            if (i == 0 || i == height.length - 1) continue;

            int h = Math.min(maxLeft[i], maxRight[i]) - height[i];
            sum += h;
        }

        return sum;
    }
}

// 单调栈
// 单调栈是按照行方向来计算雨水
// 栈顶和栈顶的下一个元素以及要入栈的三个元素来接水(凹槽)
class Solution42_02 {
    public int trap(int[] height) {
        // 单调递增栈（从栈顶到栈底）
        Stack<Integer> stack = new Stack<>();
        // 将下标0的柱子加入到栈中
        stack.push(0);
        int sum = 0;

        // 开始从下标1开始遍历所有的柱子
        for (int i = 1; i < height.length; i++) {
            // 比栈顶小时：递增（入栈）
            if (height[i] < height[stack.peek()]) {
                stack.push(i);
            // 跟栈顶相同时：相同元素，弹出重新入栈下标（计算宽度使用最右边相同高度的柱子）
            } else if (height[i] == height[stack.peek()]) {
                stack.pop();
                stack.push(i);
            // 比栈顶大时
            } else {
                // 注意这里是while，持续更新栈顶元素
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    // 凹槽的底部（中间位置）
                    int mid = stack.pop();

                    if (!stack.isEmpty()) {
                        // 雨水高度：min(凹槽左边高度, 凹槽右边高度) - 凹槽底部高度
                        int h = Math.min(height[stack.peek()], height[i]) - height[mid];
                        // 雨水宽度：凹槽右边的下标 - 凹槽左边的下标 - 1
                        int w = i - stack.peek() - 1;
                        sum += h * w;
                    }
                }

                stack.push(i);
            }
        }

        return sum;
    }
}