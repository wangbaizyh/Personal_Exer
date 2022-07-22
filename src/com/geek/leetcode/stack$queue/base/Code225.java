package com.geek.leetcode.stack$queue.base;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author G.E.E.K.
 * @create 2022-04-27 14:44
 * 225. 用队列实现栈
 * https://leetcode-cn.com/problems/implement-stack-using-queues/
 *
 * 思路：
 *      - 队列模拟栈，其实一个队列就够了
 *      - 队列是先进先出的规则，把一个队列中的数据导入另一个队列中，
 *      - 数据的顺序并没有变，并没有变成先进后出的顺序。
 *      - 一个队列用来进行备份，用两个队列que1和que2实现队列的功能，que2其实完全就是一个备份的作用
 *      - 把que1最后面的元素以外的元素都备份到que2，然后弹出最后面的元素，再把其他元素从que2导回que1
 *
 */
public class Code225 {

}

class MyStack {
    Queue<Integer> que1;    // 实现栈的队列
    // que2作为缓冲和备份的作用
    Queue<Integer> que2;    // 辅助队列

    public MyStack() {
        que1 = new LinkedList<>();
        que2 = new LinkedList<>();
    }

    public void push(int x) {
        // 都往que1里面放
        que1.offer(x);
    }

    public int pop() {
        // 把que1的元素放到que2，只剩一个
        while (que1.size() > 1) {
            que2.offer(que1.poll());
        }

        // 弹出que1的元素
        int res = que1.poll();

        // 把que2的元素放回que1
        while (!que2.isEmpty()) {
            que1.offer(que2.poll());
        }

        return res;
    }

    public int top() {
        // 把que1的元素放到que2，只剩一个
        while (que1.size() > 1) {
            que2.offer(que1.poll());
        }

        int res = que1.peek();
        que2.offer(que1.poll());

        // 把que2的元素放回que1
        while (!que2.isEmpty()) {
            que1.offer(que2.poll());
        }

        return res;
    }

    public boolean empty() {
        return que1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

class MyStack01 {
    Queue<Integer> que1;    // 实现栈的队列
    // que2作为缓冲和备份的作用
    Queue<Integer> que2;    // 辅助队列

    public MyStack01() {
        que1 = new LinkedList<>();
        que2 = new LinkedList<>();
    }

    public void push(int x) {
        que2.offer(x);

        while (!que1.isEmpty()) {
            que2.offer(que1.poll());
        }

        Queue<Integer> queTmp;
        queTmp = que1;
        que1 = que2;
        que2 = queTmp;
    }

    public int pop() {
        return que1.poll();
    }

    public int top() {
        return que1.peek();
    }

    public boolean empty() {
        return que1.isEmpty();
    }
}

class MyStack02 {
    Deque<Integer> que1;    // 实现栈的队列
    // que2作为缓冲和备份的作用
    Deque<Integer> que2;    // 辅助队列

    public MyStack02() {
        que1 = new LinkedList<>();
        que2 = new LinkedList<>();
    }

    public void push(int x) {
        que1.offerLast(x);
    }

    public int pop() {
        while (que1.size() > 1){
            que2.offerLast(que1.pollFirst());
        }

        int res = que1.pollFirst();

        que1 = que2;

        que2 = new LinkedList<>();

        return res;
    }

    public int top() {
        return que1.peekLast();
    }

    public boolean empty() {
        return que1.isEmpty();
    }
}

// 只用一个队列 Queue
class MyStack03 {
    Queue<Integer> que;    // 实现栈的队列

    public MyStack03() {
        que = new LinkedList<>();
    }

    public void push(int x) {
        que.offer(x);
    }

    public int pop() {
        int num = que.size() - 1;
        while (num-- > 0){
            que.offer(que.poll());
        }

        return que.poll();
    }

    public int top() {
        int num = que.size() - 1;
        while (num-- > 0){
            que.offer(que.poll());
        }

        int res = que.peek();

        que.offer(que.poll());

        return res;
    }

    public boolean empty() {
        return que.isEmpty();
    }
}

// 只用一个队列 Deque
class MyStack04 {
    Deque<Integer> que;    // 实现栈的队列

    public MyStack04() {
        que = new LinkedList<>();
    }

    public void push(int x) {
        que.offerLast(x);
    }

    public int pop() {
        return que.pollLast();
    }

    public int top() {
        return que.peekLast();
    }

    public boolean empty() {
        return que.isEmpty();
    }
}

// 只用一个队列 Queue
class MyStack05 {
    Deque<Integer> que;    // 实现栈的队列

    public MyStack05() {
        que = new LinkedList<>();
    }

    public void push(int x) {
        que.offerLast(x);
    }

    public int pop() {
        int num = que.size() - 1;
        while (num-- > 0){
            que.offerLast(que.pollFirst());
        }

        return que.pollFirst();
    }

    public int top() {
        int num = que.size() - 1;
        while (num-- > 0){
            que.offer(que.pollFirst());
        }

        int res = que.peekFirst();

        que.offer(que.pollFirst());

        return res;
    }

    public boolean empty() {
        return que.isEmpty();
    }
}