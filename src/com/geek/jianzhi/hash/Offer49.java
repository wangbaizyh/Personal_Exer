package com.geek.jianzhi.hash;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author G.E.E.K.
 * @create 2022-06-24 16:34
 * 剑指 Offer 49. 丑数
 * https://leetcode.cn/problems/chou-shu-lcof/
 *
 * 思路：dp
 *
 */
public class Offer49 {

}

// 小根堆
class Solution49 {
    int[] nums = {2, 3, 5};
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        Queue<Long> queue = new PriorityQueue<>();
        // 初始化
        set.add(1L);
        queue.offer(1L);

        for (int i = 1; i < n; i++) {
            long ugly = queue.poll();

            for (int num : nums) {
                long input = num * ugly;
                if (!set.contains(input)) {
                    set.add(input);
                    queue.offer(input);
                }
            }
        }

        return queue.poll().intValue();
    }
}

// 多路归并（多指针）
class Solution49_01 {
    public int nthUglyNumber(int n) {
        // ans 用作存储已有丑数（从下标 1 开始存储，第一个丑数为 1）
        int[] ans = new int[n + 1];
        ans[1] = 1;
        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        for (int i2 = 1, i3 = 1, i5 = 1, idx = 2; idx <= n; idx++) {
            // 由 ans[iX] * X 可得当前有序序列指向哪一位
            int a = ans[i2] * 2, b = ans[i3] * 3, c = ans[i5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            int min = Math.min(a, Math.min(b, c));
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (min == a) i2++;
            if (min == b) i3++;
            if (min == c) i5++;

            ans[idx] = min;
        }

        return ans[n];
    }
}


