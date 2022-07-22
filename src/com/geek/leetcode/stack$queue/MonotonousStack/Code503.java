package com.geek.leetcode.stack$queue.MonotonousStack;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-29 13:22
 * 503. 下一个更大元素 II
 * https://leetcode.cn/problems/next-greater-element-ii/
 *
 * 思路：处理循环数组
 *
 */
public class Code503 {
    @Test
    public void test() {
        int[] ans = new Solution503().nextGreaterElements(new int[]{1, 2, 3, 2, 1});
        System.out.println(ans);
    }
}

class Solution503 {
    public int[] nextGreaterElements(int[] nums) {
        // 单调递增栈
        Stack<Integer> stack = new Stack<>();
        int size = nums.length;
        int[] result = new int[size];
        Arrays.fill(result, -1);
        // 边界判断
        if (size <= 1) return result;

        // 直接遍历两遍数组
        for (int i = 0; i < size * 2; i++) {
            while (!stack.isEmpty() && nums[i % size] > nums[stack.peek()]) {
                result[stack.peek()] = nums[i % size];
                stack.pop();
            }

            stack.push(i % size);
        }

        return result;
    }
}
