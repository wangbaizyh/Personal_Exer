package com.geek.leetcode.stack$queue.path;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author G.E.E.K.
 * @create 2022-04-28 21:26
 * 71. 简化路径
 * https://leetcode-cn.com/problems/simplify-path/
 *
 * 双端队列
 *
 * 思路：先根据 / 分割字符，形成一个数组
 * 数组中包含的字符只能为以下几种：
 *      - 空字符串。例如当出现多个连续的 /，就会分割出空字符串；
 *      - 一个点 .；
 *      - 两个点 ..；
 *      - 只包含英文字母、数字或 _ 的目录名。
 *
 *
 */
public class Code71 {

}

class Solution71 {
    public String simplifyPath(String path) {
        // 分割路径
        String[] names = path.split("/");
        Deque<String> deque = new LinkedList<>();

        for (String name : names) {
            // 返回上一级
            // 弹出一级路径
            if ("..".equals(name)) {
                if (!deque.isEmpty()) {
                    deque.pollLast();
                }
            // 入栈路径 （非空 非"."）
            } else if (name.length() != 0 && !".".equals(name)) {
                deque.offerLast(name);
            }
        }

        // 拼接路径
        StringBuilder result = new StringBuilder();
        if (deque.isEmpty()) {
            result.append('/');
        } else {
            while (!deque.isEmpty()) {
                result.append('/');
                result.append(deque.pollFirst());
            }
        }

        return result.toString();
    }
}
