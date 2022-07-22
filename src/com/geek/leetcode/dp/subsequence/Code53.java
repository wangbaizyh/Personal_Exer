package com.geek.leetcode.dp.subsequence;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author G.E.E.K.
 * @create 2022-05-27 21:27
 * 53. 最大子数组和
 * https://leetcode.cn/problems/maximum-subarray/
 *
 * 思路：贪心、dp、分治
 *
 */
public class Code53 {
    @Test
    public void test() {
        int ans = new Solution53_02().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        ans = new Solution53_04().maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
        System.out.println(ans);
    }
}

// 贪心算法
class Solution53 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count > result) {
                result = count;
            }
            // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            if (count <= 0) count = 0;
        }

        return result;
    }
}

// 动态规划（一维dp）
class Solution53_01 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        // 一维dp数组
        // 状态：dp[i]表示包括i之前的最大连续子序列和
        int[] dp = new int[nums.length];
        // 初始状态：
        dp[0] = nums[0];
        int result = dp[0];

        for (int i = 1; i < nums.length; i++) {
            // 状态转移方程:
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > result) result = dp[i];
        }

        return result;
    }
}

/**
 * 分治法
 * 分治法的思路是这样的，其实也是分类讨论。
 * 连续子序列的最大和主要由这三部分子区间里元素的最大和得到：
 * 第 1 部分：子区间 [left, mid]；
 * 第 2 部分：子区间 [mid + 1, right]；
 * 第 3 部分：包含子区间[mid , mid + 1]的子区间，即 nums[mid] 与nums[mid + 1]一定会被选取。
 * 对它们三者求最大值即可。
 *
 */
class Solution53_05 {
    public int maxSubArray(int[] nums) {
        return merge(nums, 0, nums.length - 1);
    }

    private int merge(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        // 拆分子问题（三个区间）
        int mid = start + ((end - start) >> 1);
        // 左右区间最大值
        int leftMax = merge(nums, start, mid);
        int rightMax = merge(nums, mid + 1, end);

        // 计算跨区间最大值
        // 包含左子序列最后一个元素的子序列最大值
        int leftCrossMax = Integer.MIN_VALUE;
        int leftCrossSum = 0;
        for (int i = mid; i >= start; i--) {
            leftCrossSum += nums[i];
            leftCrossMax = Math.max(leftCrossMax, leftCrossSum);
        }
        // 包含右子序列第一个元素的子序列最大值
        int rightCrossMax = Integer.MIN_VALUE;
        int rightCrossSum = 0;
        for (int i = mid + 1; i <= end; i++) {
            rightCrossSum += nums[i];
            rightCrossMax = Math.max(rightCrossMax, rightCrossSum);
        }
        // 计算跨中心的子序列的最大值
        int crossMax = leftCrossMax + rightCrossMax;
        // 归并
        return Math.max(crossMax, Math.max(leftMax, rightMax));
    }
}

/**
 * 拓展：打印最大子序和
 */
// 贪心打印
class Solution53_02 {
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        int count = 0;
        int start = 0;
        int[] index = new int[2];

        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count > result) {
                result = count;
                index[0] = start;
                index[1] = i;
            }
            if (count <= 0) {
                count = 0;
                start = i + 1;
            }
        }

        System.out.println(index[1] - index[0] + 1);
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, index[0], index[1] + 1)));

        for (int i = index[0]; i <= index[1]; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();

        return result;
    }
}

// 动态规划打印
class Solution53_04 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        // 一维dp数组
        // 状态：dp[i]表示包括i之前的最大连续子序列和
        int[] dp = new int[nums.length];
        // 初始状态：
        dp[0] = nums[0];
        int result = dp[0];
        int[] index = new int[2];
        int start = 0, len = 1;

        for (int i = 1; i < nums.length; i++) {
            // 状态转移方程:
            if (dp[i - 1] + nums[i] > nums[i]) {
                dp[i] = dp[i - 1] + nums[i];
                len++;
            } else {
                dp[i] = nums[i];
                start = i;
                len = 1;
            }
            if (dp[i] > result) {
                result = dp[i];
                index[0] = start;
                index[1] = len;
            }
        }

        System.out.println(index[1]);
        System.out.println(Arrays.toString(Arrays.copyOfRange(nums, index[0], index[0] + index[1])));

        return result;
    }
}
