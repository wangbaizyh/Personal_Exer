package com.geek.leetcode.stack$queue.calc;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-07-06 11:54
 * 227. 基本计算器 II
 * https://leetcode.cn/problems/basic-calculator-ii/
 *
 * 思路：栈
 * 只考虑二元运算符 而且没有括号
 *
 */
public class Code227 {

}

class Solution227 {
    // 存储优先级
    Map<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
        }
    };

    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        int n = s.length();
        char[] chars = s.toCharArray();

        Deque<Integer> nums = new LinkedList<>();
        Deque<Character> ops = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char c = chars[i];
            // 处理数字
            if (c >= '0' && c <= '9') {
                int num = 0;
                while (i < n && chars[i] >= '0' && chars[i] <= '9') {
                    num = num * 10 + chars[i] - '0';
                    i++;
                }
                nums.push(num);
                i--;
            // 处理运算符号
            } else {
                // 有一个操作符入栈弹出运算优先级大的所有元素
                while (!ops.isEmpty()) {
                    char pre = ops.peek();
                    // 优先级大 运算
                    if (map.get(pre) >= map.get(c)) {
                        calc(nums, ops);
                    } else {
                        break;
                    }
                }

                ops.push(c);
            }
        }

        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peek();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2 || ops.isEmpty()) return;

        int b = nums.pop(), a = nums.pop();
        int op = ops.pop();
        int ans = 0;

        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        }

        nums.push(ans);
    }
}