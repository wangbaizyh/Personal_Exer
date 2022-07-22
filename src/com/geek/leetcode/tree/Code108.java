package com.geek.leetcode.tree;

import org.junit.Test;

/**
 * @author G.E.E.K.
 * @create 2022-05-09 15:59
 * 108. 将有序数组转换为二叉搜索树
 * https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
 *
 * 思路：平衡二叉搜索树
 * 构造一棵二叉树，本质就是寻找分割点，分割点作为当前节点，然后递归左区间和右区间。
 *
 */
public class Code108 {
    @Test
    public void test() {
        TreeNode node = new Solution108().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
    }

}

// 分治法
// 左闭右开
class Solution108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length);
    }

    public TreeNode buildBST(int[] nums, int left, int right) {
        if (left == right) return null;

        // 注意使用移位操作需要加括号
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = buildBST(nums, left, mid);
        root.right = buildBST(nums, mid + 1, right);

        return root;
    }
}
