package com.geek.leetcode.stack$queue.MonotonousStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author G.E.E.K.
 * @create 2022-05-29 12:53
 * 496. 下一个更大元素 I
 * https://leetcode.cn/problems/next-greater-element-i/
 *
 */
public class Code496 {

}

class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调递增栈
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);
        stack.push(0);
        // 遍历nums2
        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                    for (int j = 0; j < nums1.length; j++) {
                        if (nums1[j] == nums2[stack.peek()]) {
                            result[j] = nums2[i];
                            break;
                        }
                    }
                    stack.pop();
                }
                stack.push(i);
            }
        }

        return result;
    }
}

class Solution496_01 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调递增栈
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums1.length];
        Arrays.fill(result, -1);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map.put(nums1[i], i);
        }

        stack.push(0);
        // 遍历nums2
        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                    if (map.containsKey(nums2[stack.peek()])) {
                        int index = map.get(nums2[stack.peek()]);
                        result[index] = nums2[i];
                    }
                    stack.pop();
                }
                stack.push(i);
            }
        }

        return result;
    }
}