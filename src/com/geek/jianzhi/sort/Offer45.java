package com.geek.jianzhi.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-06-23 15:42
 * 剑指 Offer 45. 把数组排成最小的数
 * https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 *
 * 思路：排序判断规则 指定 快速排序（快排规则为字典序）
 *
 *
 */
public class Offer45 {
    @Test
    public void test() {
        String ans = new Solution45().minNumber(new int[]{0, 9, 8, 7, 6, 5, 4, 3, 2, 1});
        System.out.println(ans);
    }
}

class Solution45 {
    public String minNumber(int[] nums) {
        // 转换成字符串的形式（字典排序）
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        quickSort(strs, 0, strs.length - 1);

        // 拼接字符串
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }

        return sb.toString();
    }

    // 快速排序
    // 排序判断规则：字典序
    public void quickSort(String[] strs, int begin, int end) {
        if (begin >= end) return;

        String base = strs[begin];
        int left = begin;
        int right = end;

        while (left < right) {
            // 跟base比较
            while (left < right && (strs[right] + base).compareTo(base + strs[right]) >= 0) {
                right--;
            }
            strs[left] = strs[right];

            // 跟base比较
            while (left < right && (strs[left] + base).compareTo(base + strs[left]) <= 0) {
                left++;
            }
            strs[right] = strs[left];
        }

        strs[left] = base;
        quickSort(strs, begin, left - 1);
        quickSort(strs, left + 1, end);
    }
}

// 内置函数
class Solution45_01 {
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++){
            strs[i] = String.valueOf(nums[i]);
        }
        // 快速排序
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));

        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
}