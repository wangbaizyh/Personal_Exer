package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-15 10:11
 * 45. 跳跃游戏 II
 * https://leetcode.cn/problems/jump-game-ii/
 *
 * 贪心思路：以最小的步数增加最大的覆盖范围，直到覆盖范围覆盖了终点
 *
 */
public class Code45 {

}

// 贪心算法
class Solution45 {
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;
        int curCover = 0;   // 当前覆盖最远距离下标
        int ans = 0;        // 记录走的最大步数
        int nextCover = 0;  // 下一步覆盖最远距离下标

        for (int i = 0; i < nums.length; i++) {
            nextCover = Math.max(i + nums[i], nextCover);   // 更新下一步覆盖最远距离下标
            if (i == curCover) {                            // 遇到当前覆盖最远距离下标
                if (curCover != nums.length - 1) {          // 如果当前覆盖最远距离下标不是终点
                    ans++;                                  // 需要走下一步
                    curCover = nextCover;                   // 更新当前覆盖最远距离下标（相当于加油了）
                    if (curCover >= nums.length - 1) break; // 下一步的覆盖范围已经可以达到终点，结束循环
                } else break;   // 当前覆盖最远距离下标是集合终点，不用做ans++操作了，直接结束
            }
        }

        return ans;
    }
}