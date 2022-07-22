package com.geek.leetcode.ranklist.weak298;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-06-19 10:44
 * 2310. 个位数字为 K 的整数之和
 * https://leetcode.cn/problems/sum-of-numbers-with-units-digit-k/
 *
 * 思路：模拟、BFS、完全背包
 *
 */
public class Code2310 {
    public static void main(String[] args) {
        Solution2310 back = new Solution2310();
        int ans = back.minimumNumbers(2, 8);
        System.out.println(ans);
    }
}

// 模拟
class Solution2310 {

    public int minimumNumbers(int num, int k) {
        // 特判
        if (num == 0) return 0;
        if (k == 0) {
            if (num % 10 == 0) return 1;
            return -1;
        }

        for (int i = 1; i * k <= num; i++) {
            if ((num - i * k) % 10 == 0) {
                return i;
            }
        }

        return -1;
    }
}

// BFS (可以解决极端情况) 层序遍历
// BFS 默认进行了剪枝操作 层序遍历的方式
class Solution2310_1 {
    // 记录需要BFS的集合
    Set<Integer> parts = new HashSet<>();
    // 记录得到结果需要的数字个数
    Map<Integer, Integer> numMap = new HashMap<>();

    public int minimumNumbers(int num, int k) {
        // 特判
        if (num == 0) return 0;

        // 层序队列
        Queue<Integer> queue = new ArrayDeque<>();

        // 初始化
        for (int i = k; i <= num; i += 10) {
            // 初始化个数
            numMap.put(i, 1);
            // 记录集合
            parts.add(i);
            // 初始入队，初始化BFS的第一层
            queue.offer(i);
        }

        // 剪枝：找到则立即退出
        boolean find = false;
        // 开始层序遍历
        while (!queue.isEmpty()) {
            int currentNum = queue.poll();

            for (Integer part : parts) {
                // 当前合
                int nextNum = part + currentNum;
                // 当前个数
                int nextCount = numMap.get(currentNum) + 1;

                // 符合条件的情况
                if (nextNum <= num && !numMap.containsKey(nextNum)) {
                    queue.add(nextNum);
                    numMap.put(nextNum, nextCount);

                    // 找到长度最小的条件，剪枝
                    if (nextNum == num) {
                        find = true;
                        break;
                    }
                }
            }
            if (find) break;
        }

        return numMap.getOrDefault(num, -1);
    }
}

// dp 完全背包
class Solution2310_2 {
    public int minimumNumbers(int num, int k) {
        // 特判
        if (num == 0) return 0;
        if (k == 0) {
            if (num % 10 == 0) return 1;
            return -1;
        }
        if (num < k) return -1;
        if (num % 2 == 1 && k % 2 == 0) return -1;

        // 寻找物品和计算物品的个数
        List<Integer> parts = new ArrayList<>();
        for (int i = k; i <= num; i += 10) {
            parts.add(i);
        }
        int len = parts.size();
        // 状态定义:
        // dp[j]前i个选择组成j的最少次数
        int[] dp = new int[num + 1];
        // 初始化: 因为求的是最少次数
        for (int j = 0; j <= num; j++) {
            dp[j] = Integer.MAX_VALUE;
        }
        // 当和为0时，组合的个数为0
        dp[0] = 0;

        for (int i = 0; i < len; i++) {                        // 遍历物品
            for (int j = parts.get(i); j <= num; j++) {        // 遍历背包
                // 当dp[j - parts.get(i)]不可达时候，不dp
                if (dp[j - parts.get(i)] != Integer.MAX_VALUE) {
                    // 不使用物品i || 使用物品i
                    dp[j] = Math.min(dp[j], dp[j - parts.get(i)] + 1);
                }
            }
        }

        if (dp[num] == Integer.MAX_VALUE) return -1;
        return dp[num];
    }
}

// dp 完全背包 (处理方法二)
class Solution2310_2_1 {
    public int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        if (k == 0) {
            if (num % 10 == 0) return 1;
            return -1;
        }
        if (num < k) return -1;
        if (num % 2 == 1 && k % 2 == 0) return -1;

        // 寻找物品和计算物品的个数
        List<Integer> parts = new ArrayList<>();
        for (int i = k; i <= num; i+=10) {
            parts.add(i);
        }
        int len = parts.size();
        // dp[j]前i个选择组成j的最少次数
        int[] dp = new int[num + 1];
        // 初始化
        for (int j = 0; j <= num; j++) {
            dp[j] = Integer.MAX_VALUE / 2;
        }

        dp[0] = 0;

        for (int i = 0; i < len; i++) {                        // 遍历物品
            for (int j = parts.get(i); j <= num; j++) {        // 遍历背包
                dp[j] = Math.min(dp[j], dp[j - parts.get(i)] + 1);
            }
        }

        if (dp[num] == Integer.MAX_VALUE / 2) return -1;
        return dp[num];
    }
}


// 回溯 ( DFS超时! 遇到3000 1 这类极端情况会超时不奏效)
class Solution2310_3 {
    int ans = Integer.MAX_VALUE;
    int len;

    public int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        if (num < k) return -1;
        if (k == 0) {
            if (num < 10) return -1;
            if (num % 10 == 0) return 1;
            else return -1;
        }

        int bound = num / k + 1;
        backtrack(num, k, bound);

        if (ans == Integer.MAX_VALUE) return -1;
        return ans;
    }

    private void backtrack(int targetNum, int k, int bound) {
        // 回溯结束条件
        if (targetNum == 0) {
            ans = Math.min(ans, len);
            return;
        } else if (targetNum < 0) {
            return;
        }

        for (int i = 0; i < bound && k <= targetNum; i++) {
            k = k + i * 10;
            len++;
            backtrack(targetNum - k, k, bound);
            len--;
            k = k - i * 10;
        }
    }
}