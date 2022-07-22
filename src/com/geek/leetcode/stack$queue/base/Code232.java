package com.geek.leetcode.stack$queue.base;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-04-27 11:16
 * 232. 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 * 模拟题
 *
 */
public class Code232 {

}

class MyQueue {
    private final Stack<Integer> stackIn;
    private final Stack<Integer> stackOut;

    public MyQueue() {
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    public void push(int x) {
        stackIn.push(x);
    }

    public int pop() {
        // 输出栈如果为空，就把进栈数据全部导入进来，再从出栈弹出数据
        // 如果输出栈不为空，则直接从出栈弹出数据。
        dumpStackIn();

        return stackOut.pop();
    }

    public int peek() {
        dumpStackIn();

        return stackOut.peek();
    }

    public boolean empty() {
        return stackIn.empty() && stackOut.empty();
    }

    // 如果stackOut为空，那么将stackIn中的元素全部放到stackOut中
    // 转换顺序 成为队列
    private void dumpStackIn() {
        if (stackOut.empty()){
            while (!stackIn.empty()){
                stackOut.push(stackIn.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */