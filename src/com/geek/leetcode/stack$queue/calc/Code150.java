package com.geek.leetcode.stack$queue.calc;

import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-04-28 11:04
 * 150. 逆波兰表达式求值
 * https://leetcode-cn.com/problems/evaluate-reverse-polish-notation/
 *
 * 思路：逆波兰表达式：是一种后缀表达式，所谓后缀就是指算符写在后面。
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 逆波兰表达式主要有以下两个优点：
 *      - 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 *      - 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
 *
 * 其实逆波兰表达式相当于是二叉树中的后序遍历。
 * 大家可以把运算符作为中间节点，按照后序遍历的规则画出一个二叉树。
 *
 * 逆波兰很取决于输入的形式，需要对输入进行处理。
 * （字符串数组 或者 一个字符串（需要进行拆分））
 */
public class Code150 {

}

class Solution150 {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            // 符号
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                if (token.equals("+")) stack.push(num2 + num1);
                if (token.equals("-")) stack.push(num2 - num1);
                if (token.equals("*")) stack.push(num2 * num1);
                if (token.equals("/")) stack.push(num2 / num1);
            } else {
                // 数字入栈
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }
}