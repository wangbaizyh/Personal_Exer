package com.geek.leetcode.stack$queue.calc;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-04-27 16:22
 * 20. 有效的括号
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * 思路：括号匹配是使用栈解决的经典问题。
 *
 *
 *
 */
public class Code20 {
    @Test
    public void test() {
        boolean valid = new Solution20().isValid("()");
        System.out.println(valid);
    }

}

class Solution20 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char c ;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            switch (c){
                case '(': case '[':case '{':
                    stack.push(c);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '('){
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '['){
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{'){
                        return false;
                    }
                    break;
            }
        }

        return stack.isEmpty();
    }
}

class Solution20_01 {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        char ch;

        /**
         * 思想：
         * 所有左括号 入栈一个对应的 右括号
         * 所有右括号 出栈 比较是否相等
         */
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '{') {
                stack.push('}');
            } else if (ch == '[') {
                stack.push(']');
            } else if (stack.isEmpty() || stack.pop() != ch){
                return false;
            }
        }

        return stack.isEmpty();
    }
}

