package com.geek.leetcode.stack$queue.string;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-04-27 18:38
 * 1047. 删除字符串中的所有相邻重复项
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * 思路：使用栈解决
 *
 */
public class Code1047 {

    @Test
    public void test() {
        String s = new Solution1047().removeDuplicates("abbaca");
        System.out.println(s);
        char[] test = {'a','b','c'};
        String str = new String(test, 0, 0);
        System.out.println(str);
        System.out.println(str.length());
    }

}

class Solution1047 {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        char ch;

        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        if (stack.isEmpty()) return "";

        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }

        // 反转字符串
        return sb.reverse().toString();
    }
}

class Solution1047_01 {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        String str = "";
        // 前拼
        while (!stack.isEmpty()){
            str = stack.pop() + str;
        }

        return str;
    }
}

class Solution1047_02 {
    public String removeDuplicates(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }

        StringBuilder str = new StringBuilder();

        // 0插入
        while (!stack.isEmpty()){
            str.insert(0, stack.pop());
        }

        return str.toString();
    }
}

// 字符串直接做栈
class Solution1047_03 {
    public String removeDuplicates(String s) {
        // 将 res 当做栈
        StringBuilder res = new StringBuilder();
        int top = -1;
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            // 当 top > 0, 即栈中有字符时，当前字符如果和栈中字符相等，弹出栈顶字符，同时 top--
            if (top >= 0 && res.charAt(top) == ch) {
                res.deleteCharAt(top);
                top--;
                // 否则，将该字符 入栈，同时top++
            } else {
                res.append(ch);
                top++;
            }
        }

        return res.toString();
    }
}

// 双指针
// 主要思想：
// 遇到前后相同的值，慢指针后退，快指针继续前进，实现两个单位的删除
class Solution1047_04 {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        int slow = 0;

        for (int fast = 0; fast < chars.length; fast++) {
            // 直接用fast指针覆盖slow指针的值
            chars[slow] = chars[fast];

            // 遇到前后相同值的，就跳过，即slow指针后退一步，下次循环就可以直接被覆盖掉了
            if (slow > 0 && chars[slow] == chars[slow - 1]){
                slow--;
            } else {
                slow++;
            }

        }

        return new String(chars,0,slow);
    }
}
