package com.geek.leetcode.greed;

/**
 * @author G.E.E.K.
 * @create 2022-05-14 16:51
 * 55. 跳跃游戏
 * https://leetcode.cn/problems/jump-game/
 *
 */
public class Code55 {

}

class Solution55 {
    public boolean canJump(int[] nums) {
        int cover = 0;
        if (nums.length == 1) return true;
        // 在覆盖范围内更新最大的覆盖范围
        for (int i = 0; i <= cover; i++) {
            // 局部最优：当前位置所能cover的最远位置
            cover = Math.max(i + nums[i], cover);
            if (cover >= nums.length - 1) return true;
        }

        return false;
    }
}