package com.geek.leetcode.stack$queue.MonotonousStack;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-29 10:49
 * 739. 每日温度
 * https://leetcode.cn/problems/daily-temperatures/
 *
 * 思路：单调栈
 * 通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，
 * 此时我们就要想到可以用单调栈了。
 *
 * 本质：空间换时间
 *
 * 在使用单调栈的时候首先要明确如下几点：
 * 单调栈里存放的元素是什么？
 * 单调栈里只需要存放元素的下标i就可以了，如果需要使用对应的元素，直接T[i]就可以获取。
 * 单调栈里元素是递增呢？ 还是递减呢？
 * 顺序为从栈头到栈底的顺序。 递增顺序
 *
 * 使用单调栈主要有三个判断条件。
 * 当前遍历的元素T[i]小于栈顶元素T[st.top()]的情况
 * 当前遍历的元素T[i]等于栈顶元素T[st.top()]的情况
 * 当前遍历的元素T[i]大于栈顶元素T[st.top()]的情况
 *
 *
 */
public class Code739 {

}

class Solution739 {
    public int[] dailyTemperatures(int[] temperatures) {
        // 单调递增栈
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];
        // 单调栈存储元素下标
        stack.push(0);
        for (int i = 1; i < temperatures.length; i++) {
            // 小于等于栈顶，入栈
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else  {
                // 大于栈顶，出栈
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                   result[stack.peek()] = i - stack.pop();
                }
                stack.push(i);
            }
        }

        return result;
    }
}