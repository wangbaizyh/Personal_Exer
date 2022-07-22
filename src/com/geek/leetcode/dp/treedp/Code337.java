package com.geek.leetcode.dp.treedp;

import java.util.HashMap;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-05-25 14:53
 * 337. 打家劫舍 III
 * https://leetcode.cn/problems/house-robber-iii/
 *
 * 思路：树形dp
 *
 */
public class Code337 {

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// 暴力递归
class Solution337 {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        // 偷父节点
        int val1 = root.val;
        if (root.left != null) val1 += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val1 += rob(root.right.left) + rob(root.right.right);
        // 不偷父节点
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }
}

// 记忆化搜索
class Solution337_01 {
    Map<TreeNode, Integer> memory = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;

        // 取记忆
        if (memory.containsKey(root)) return memory.get(root);

        // 偷父节点
        int val1 = root.val;
        if (root.left != null) val1 += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val1 += rob(root.right.left) + rob(root.right.right);
        // 不偷父节点
        int val2 = rob(root.left) + rob(root.right);

        // 存记忆
        memory.put(root, Math.max(val1, val2));

        return Math.max(val1, val2);
    }
}

// 树形dp(后序遍历)
// 动态规划其实就是使用状态转移容器来记录状态的变化，
// 这里可以使用一个长度为2的数组，记录当前节点偷与不偷所得到的的最大金钱。
class Solution337_02 {

    public int rob(TreeNode root) {
        // 状态：
        // dp[0]: 下标为0记录不偷该节点所得到的的最大金钱，
        // dp[1]: 下标为1记录偷该节点所得到的的最大金钱。
        int[] dp = robTree(root);
        return Math.max(dp[0], dp[1]);
    }

    // 长度为2的数组，0：不偷，1：偷
    private int[] robTree(TreeNode cur) {
        // 如果遇到空节点的话，很明显，无论偷还是不偷都是0，所以就返回
        // 相当于dp数组的初始化
        if (cur == null) return new int[]{0, 0};
        // 遍历顺序 -- 后序遍历
        // 通过递归左节点，得到左节点偷与不偷的金钱。
        // 通过递归右节点，得到右节点偷与不偷的金钱。
        int[] left = robTree(cur.left);     // 左
        int[] right = robTree(cur.right);   // 右
        // 偷当前节点，那么左右孩子就不能偷
        int val1 = cur.val + left[0] + right[0];
        // 不偷当前节点，那么左右孩子就可以偷，至于到底偷不偷一定是选一个最大的
        int val2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // 最后当前节点的状态就是{val2, val1}
        // 即：{不偷当前节点得到的最大金钱，偷当前节点得到的最大金钱}
        return new int[]{val2, val1};       // 中
    }
}