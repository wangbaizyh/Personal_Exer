package com.geek.leetcode.greed;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-05-13 15:40
 * 376. 摆动序列
 * https://leetcode.cn/problems/wiggle-subsequence/
 *
 */
public class Code376 {
    @Test
    public void test() {
        int ans = new Solution376().wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5});
        System.out.println(ans);
    }

}

// 贪心思路：保持区间波动，只需要把单调区间上的元素移除就可以了。
class Solution376 {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int curDiff;
        int preDiff = 0;
        int result = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];

            // curDiff不能为0
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                result++;
                preDiff = curDiff;
            }
        }

        return result;
    }
}

// 动态规划(二维dp)
class Solution376_01 {
    public int wiggleMaxLength(int[] nums) {
        // dp[i][0] 作为波峰的最大长度
        // dp[i][1] 作为波谷的最大长度
        int[][] dp = new int[nums.length][2];
        // 初始状态
        dp[0][0] = dp[0][1] = 1;

        for (int i = 1; i < nums.length; i++) {
            //i 自己可以成为波峰或者波谷
            dp[i][0] = dp[i][1] = 1;

            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    // i 是波谷
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
                if (nums[j] < nums[i]) {
                    // i 是波峰
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                }
            }
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}