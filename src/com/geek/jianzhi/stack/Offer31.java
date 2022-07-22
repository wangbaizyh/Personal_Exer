package com.geek.jianzhi.stack;

import org.junit.Test;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-06-17 21:01
 * 剑指 Offer 31. 栈的压入、弹出序列
 * https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
 *
 * 思路：栈
 *
 */
public class Offer31 {
    @Test
    public void test() {
        boolean ans = new Solution31().validateStackSequences(new int[]{1, 0}, new int[]{1, 0});
        System.out.println(ans);
    }

}

class Solution31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;

        for (int num : pushed) {
            // 入栈
            stack.push(num);

            // 循环判断与出栈
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }

        return stack.isEmpty();
    }
}