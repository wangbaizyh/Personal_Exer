package com.geek.jianzhi.stack;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-31 14:14
 * 剑指 Offer 09. 用两个栈实现队列
 * https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 *
 */
public class Offer09 {

}

class CQueue {
    Stack<Integer> In;
    // 辅助栈反转实现队列
    Stack<Integer> Out;

    public CQueue() {
        this.In = new Stack<>();
        this.Out = new Stack<>();
    }

    public void appendTail(int value) {
        In.push(value);
    }

    public int deleteHead() {
        // 如果辅助栈为空，把入栈队列全部导入辅助栈
        if (Out.isEmpty()) {
            while (!In.isEmpty()) {
                Out.push(In.pop());
            }
        }

        if (Out.isEmpty()) return -1;
        else return Out.pop();
    }
}