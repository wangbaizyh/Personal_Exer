package com.geek.jianzhi.stack;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-06-15 22:09
 * 剑指 Offer 30. 包含min函数的栈
 * https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
 *
 * 思路：辅助栈 非严格降序元素 单调栈
 */
public class Offer30 {

}

class MinStack {
    // 数据栈 辅助栈
    Stack<Integer> stack, backup;
    /** initialize your data structure here. */
    public MinStack() {
        this.stack = new Stack<>();
        this.backup = new Stack<>();
    }

    // 保持辅助栈的元素是非严格递减的
    public void push(int x) {
        // 数据栈入栈
        stack.push(x);
        // 单调栈入栈
        if (backup.isEmpty() || backup.peek() >= x) {
            backup.push(x);
        }
    }

    public void pop() {
        // 数据栈出栈
        int peek = stack.peek();
        stack.pop();
        // 单调栈出栈
        if (peek == backup.peek()) {
            backup.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return backup.peek();
    }
}