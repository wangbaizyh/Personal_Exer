package com.geek.leetcode.dp.knapsackProblem;

import org.junit.Test;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-05-21 14:54
 * 494. 目标和
 * https://leetcode.cn/problems/target-sum/
 *
 * 思路：分为两个集合: 加法总和 减法总和
 *      left - right = target
 *      left + right = sum
 *      left - (sum - left) = target
 *      left = (target + left) / 2
 *
 *      背包：组合问题
 */
public class Code494 {
    @Test
    public void test() {
        int ans = new Solution494_02().findTargetSumWays(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1}, 1);
        System.out.println(ans);
    }

}

// 01背包
// 一维dp
class Solution494 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum) return 0;
        if ((sum + target) % 2 == 1) return 0;
        // 背包容量
        int bagSize = (sum + target) / 2;
        // 状态：
        // dp[j] 表示：填满j（包括j）这么大容积的包，有dp[j]种方法
        int[] dp = new int[bagSize + 1];
        // 初始化：容积为0的包有1种方法
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {             // 遍历数字
            for (int j = bagSize; j >= nums[i]; j--) {      // 遍历背包
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[bagSize];
    }
}

// 回溯法
class Solution494_01 {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum) return 0;
        if ((sum + target) % 2 == 1) return 0;
        // 背包容量
        int bagSize = (sum + target) / 2;

        Arrays.sort(nums);          // 回溯必须排序
        backtracking(nums, bagSize, 0, 0);

        return result.size();
    }

    public void backtracking(int[] nums, int target, int sum, int startIndex) {
        if (sum == target) {
            result.add(new ArrayList<>(path));
        }

        for (int i = startIndex; i < nums.length && sum + nums[i] <= target; i++) {
            sum += nums[i];
            path.add(nums[i]);
            backtracking(nums, target, sum, i + 1);
            path.remove(path.size() - 1);
            sum -= nums[i];
        }
    }
}


// 二维dp
class Solution494_02 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (Math.abs(target) > sum) return 0;
        if ((sum + target) % 2 == 1) return 0;
        // 背包容量
        int bagSize = (sum + target) / 2;
        // 状态：
        // dp[i][j] 考虑前 i 个数，当前计算结果为 j 的方案数
        int[][] dp = new int[nums.length][bagSize + 1];
        // 初始化：
        // 容积为0的包有1种方法
        dp[0][0] = 1;

        // 只有物品0装包的表达式数目
        // 必须倒序赋值，否则会重复用到本层数据
        for (int j = bagSize; j >= nums[0]; j--) {
            dp[0][j] += dp[0][j - nums[0]];
        }

        for (int i = 1; i < nums.length; i++) {       // 遍历数字
            for (int j = 0; j <= bagSize; j++) {      // 遍历背包
                // 不选i
                dp[i][j] += dp[i - 1][j];

                // 选i
                if (j >= nums[i]) {
                    dp[i][j] += dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[nums.length - 1][bagSize];
    }
}

// 暴力 DFS （二叉树的后序遍历）
// 接收返回值处理 （后序遍历）
class Solution494_03 {

    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }

    public int dfs(int[] nums, int target, int sum, int startIndex) {
        if (startIndex == nums.length) {
            return sum == target ? 1 : 0;
        }

        int left = dfs(nums, target, sum + nums[startIndex], startIndex + 1);
        int right = dfs(nums, target, sum - nums[startIndex], startIndex + 1);

        return left + right;
    }
}

// 暴力 DFS （二叉树的前序遍历）
// 使用全局变量维护 （前序遍历）
class Solution494_04 {
    int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, target, 0, 0);
        return ans;
    }

    public void dfs(int[] nums, int target, int sum, int startIndex) {
        if (startIndex == nums.length) {
            ans += sum == target ? 1 : 0;
            return ;
        }

        dfs(nums, target, sum + nums[startIndex], startIndex + 1);
        dfs(nums, target, sum - nums[startIndex], startIndex + 1);
    }
}

// 记忆化搜索 （只能使用后序遍历，即接收返回值的形式）
class Solution494_05 {
    // 记忆化搜索容器
    Map<String, Integer> cache = new HashMap<>();

    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums, target, 0, 0);
    }

    public int dfs(int[] nums, int target, int sum, int startIndex) {
        // 设计容器的key值，存储搜索过的路径
        String key = startIndex + "_" + sum;
        // 如果搜索过，直接返回
        if (cache.containsKey(key)) return cache.get(key);

        if (startIndex == nums.length) {
            cache.put(key, sum == target ? 1 : 0);
            return cache.get(key);
        }

        int left = dfs(nums, target, sum + nums[startIndex], startIndex + 1);
        int right = dfs(nums, target, sum - nums[startIndex], startIndex + 1);

        // 存储搜索结果
        cache.put(key, left + right);
        return cache.get(key);
    }
}



