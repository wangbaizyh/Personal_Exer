package com.geek.leetcode.greed;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author G.E.E.K.
 * @create 2022-05-15 10:40
 * 1005. K 次取反后最大化的数组和
 * https://leetcode.cn/problems/maximize-sum-of-array-after-k-negations/
 *
 * 思路：两次贪心
 *
 */
public class Code1005 {
    @Test
    public void test() {
        int ans = new Solution1005_03().largestSumAfterKNegations(new int[]{4, 2, 3}, 1);
        System.out.println(ans);
    }

}

class Solution1005 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();

        for (int i = 0; i < nums.length; i++) {
            // 从前向后遍历，遇到负数将其变为正数，同时K--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将K用完
        if (k % 2 == 1) nums[nums.length - 1] = -nums[nums.length - 1];
        return Arrays.stream(nums).sum();
    }
}

class Solution1005_01 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
        nums = Arrays.stream(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue)
                .toArray();

        for (int i = 0; i < nums.length; i++) {
            // 从前向后遍历，遇到负数将其变为正数，同时k--
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将k用完
        if (k % 2 == 1) nums[nums.length - 1] = -nums[nums.length - 1];
        return Arrays.stream(nums).sum();
    }
}

// 拓展思路
// 计数数组：桶排序（使用哈希表）
class Solution1005_02 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 使用哈希表进行桶排序
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 求和
        int ans = Arrays.stream(nums).sum();

        // 处理负数
        for (int i = -100; i < 0; i++) {
            if (map.containsKey(i)) {
                int ops = Math.min(k, map.get(i));
                ans += (-i) * 2 * ops;
                map.put(i, map.get(i)- ops);
                map.put(-i, map.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }

        if (k > 0 && k % 2 == 1 && !map.containsKey(0)) {
            for (int i = 1; i <= 100; i++) {
                if (map.containsKey(i)) {
                    ans -= i * 2;
                    break;
                }
            }
        }

        return ans;
    }
}

// 拓展思路
// 计数数组：桶排序（使用数组）
class Solution1005_03 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 使用数组进行桶排序
        int[] map = new int[201];
        for (int num : nums) {
            map[num + 100]++;
        }

        // 求和
        int ans = Arrays.stream(nums).sum();

        // 处理负数
        for (int i = 0; i < 100; i++) {
            if (map[i] != 0) {
                int ops = Math.min(k, map[i]);
                ans += -(i - 100) * 2 * ops;
                map[i] = map[i] - ops;
                map[200 - i] = ops;
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }

        if (k > 0 && k % 2 == 1 && map[100] == 0) {
            for (int i = 101; i <= 200; i++) {
                if (map[i] != 0) {
                    ans -= (i - 100) * 2;
                    break;
                }
            }
        }

        return ans;
    }
}

// 使用优先队列
class Solution1005_04 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        // 记录长度和绝对值最小的下标
        int n = nums.length, idx = 0;
        // 默认小根堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        // 负数下标入堆
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) queue.add(i);
            if (Math.abs(nums[i]) < Math.abs(nums[idx])) idx = i;
        }

        if (k <= queue.size()) {
            while (k-- > 0) nums[queue.peek()] = -nums[queue.poll()];
        } else {
            while (!queue.isEmpty()) nums[queue.peek()] = -nums[queue.poll()];
            if (nums[idx] != 0 && k % 2 == 1) nums[idx] = -nums[idx];
        }

        return Arrays.stream(nums).sum();
    }
}



