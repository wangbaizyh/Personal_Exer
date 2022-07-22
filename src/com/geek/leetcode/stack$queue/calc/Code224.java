package com.geek.leetcode.stack$queue.calc;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-07-01 15:18
 * 224. 基本计算器
 * https://leetcode.cn/problems/basic-calculator/
 *
 * 思路：栈
 * 难点：
 *      1、符号优先级 （字典存储）
 *      2、一元运算符的转换 （加0）
 *      3、优先级弹出运算
 */
public class Code224 {

}

// 符号栈
class Solution224 {
    public int calculate(String s) {
        // 符号栈(用数字1表示一个符号)
        Deque<Integer> ops = new LinkedList<>();
        // 默认放入一个符号
        ops.push(1);
        // 表示符号正负
        int sign = 1;

        int res = 0;
        int n = s.length();
        int i = 0;

        // 遍历字符
        while (i < n) {
            // 跳过空格
            if (s.charAt(i) == ' ') {
                i++;
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            } else if (s.charAt(i) == '(') {
                // 符号入栈
                ops.push(sign);
                i++;
            } else if (s.charAt(i) == ')') {
                // 符号出栈
                ops.pop();
                i++;
            } else {
                long num = 0;
                while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                res += sign * num;
            }
        }

        return res;
    }
}

// 双栈解法
class Solution224_1 {
    public int calculate(String s) {
        // 存放所有的数字
        Deque<Integer> nums = new LinkedList<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.push(0);
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new LinkedList<>();

        int n = s.length();
        char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peek();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (c >= '0' && c <= '9') {
                    int num = 0;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (i < n && chars[i] >= '0' && chars[i] <= '9') {
                        num = num * 10 + chars[i] - '0';
                        i++;
                    }
                    nums.push(num);
                    i--;
                } else {
                    if (i > 0 && chars[i - 1] == '(') {
                        nums.push(0);
                    }

                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        calc(nums, ops);
                    }
                    ops.push(c);
                }
            }
        }

        // 计算完所有的操作
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peek();
    }

    // 计算一次操作栈和数字栈
    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2 || ops.isEmpty()) return;

        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();

        nums.push(op == '+' ? a + b : a - b);
    }
}

// 进阶
class Solution224_2 {
    // 字典存储优先级
    Map<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
            put('%', 2);
            put('^', 3);
        }
    };

    public int calculate(String s) {
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        int n = s.length();
        char[] chars = s.toCharArray();

        // 存放所有的数字
        Deque<Integer> nums = new LinkedList<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.push(0);
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            char c = chars[i];
            // 左括号入栈
            if (c == '(') {
                ops.push(c);
            // 处理右括号
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peek();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                // 处理数字
                if (c >= '0' && c <= '9') {
                    int num = 0;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (i < n && chars[i] >= '0' && chars[i] <= '9') {
                        num = num * 10 + chars[i] - '0';
                        i++;
                    }
                    nums.push(num);
                    i--;
                // 处理算术符号
                } else {
                    // 为了防止 (+ (- 的情况出现
                    if (i > 0 && chars[i - 1] == '(') {
                        nums.push(0);
                    }

                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 优先级比当前优先级高的都计算了
                    while (!ops.isEmpty() && ops.peek() != '(') {
                        char pre = ops.peek();
                        if (map.get(pre) >= map.get(c)) {
                            calc(nums, ops);
                        } else {
                            break;
                        }

                    }
                    ops.push(c);
                }
            }
        }

        // 计算完所有的操作
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peek();
    }

    // 计算一次操作栈和数字栈
    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2 || ops.isEmpty()) return;

        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        int ans = 0;
        if (op == '+') {
            ans = a + b;
        } else if (op == '-') {
            ans = a - b;
        } else if (op == '*') {
            ans = a * b;
        } else if (op == '/') {
            ans = a / b;
        } else if (op == '%') {
            ans = a % b;
        } else if (op == '^') {
            ans = (int) Math.pow(a, b);
        }

        nums.push(ans);
    }
}