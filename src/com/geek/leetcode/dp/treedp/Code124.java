package com.geek.leetcode.dp.treedp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-07-18 15:01
 * 124. 二叉树中的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 *
 * 思路：后序遍历
 * 核心在于计算结果返回的时候要考虑左右子树，递归返回的时候只能返回较大的一边
 *
 * 1. 「可以从任意节点出发, 到达任意节点」 的路径,
 *    一定是先上升（ 0 ～ n 个）节点, 到达顶点, 后下降（ 0 ～ n 个）节点。
 *    我们可以通过枚举顶点的方式来枚举路径。
 * 2. 我们枚举顶点时, 可以把路径分拆成3部分： 左侧路径、右侧路径和顶点。
 * 3. 如何求两侧路径最大和？ 看一个类似问题：求数组的最大子数组和。
 *    动态规划： dp[i] 代表以 nums[i] 为结尾的子数组的最大和。
 *    转移方程： dp[i] = max(dp[i-1], 0) + nums[i]。
 * 4. 在树上, 设 dp[C] 代表以当前节点为结尾的最大上升路径和,
 *    则我们需要对节点的左右子树做一个选择, 有
 *    dp[C] = max(max(dp[L], 0), max(dp[R], 0)) + C.val
 *    式中, C,L,R 分别代指 当前节点、左子节点、右子节点。
 * 5. 最后, 以当前节点为顶点的路径中, 最大的和为
 *    max(dp[L], 0) + max(dp[R], 0) + C.val。
 *    我们枚举顶点, 并记录最大答案。
 *
 */
public class Code124 {
    @Test
    public void test() {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(-9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(-7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        int ans = new Solution124().maxPathSum(root);
        System.out.println(ans);

        int ans1 = new Solution124_1().maxPathSum(root);
        System.out.println(ans1);
    }
}

/**
 * 树形dp
 * 思路：后序遍历 + 记录本节点联通的最大路径值
 * 核心在于计算结果返回的时候要考虑左右子树，递归返回的时候只能返回较大的一边
 * 注意是任意节点到任意节点，不用到达叶子节点，因此若路径小于0，则不访问
 */
class Solution124 {
    // 存储最大路径和
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        postorder(root);
        return maxSum;
    }

    // 计算该节点的最大路径
    private int postorder(TreeNode root) {
        if (root == null) return 0;
        // 递归计算左右子树的最大路径
        // 只有在最大路径大于 0 时，才会选取对应子节点
        int leftSum = Math.max(postorder(root.left), 0);
        int rightSum = Math.max(postorder(root.right), 0);
        // 当前节点的最大路径和为 两侧路径的最大和 + 节点的值。
        // 更新最大路径和(本节点联通，为顶点)
        maxSum = Math.max(maxSum, leftSum + rightSum + root.val);
        // 返回节点的最大路径
        return root.val + Math.max(leftSum, rightSum);
    }
}

/**
 * 进阶拓展：打印最大路径和的路径
 */
class Solution124_1 {
    // 存储最大路径和
    int maxSum = Integer.MIN_VALUE;
    // 存储最大路径
    List<Integer> maxPath = new ArrayList<>();

    public int maxPathSum(TreeNode root) {
        postorder(root);
        System.out.println(maxPath);
        return maxSum;
    }

    // 计算该节点的最大路径
    private Pair postorder(TreeNode root) {
        // 新路径
        if (root == null) return new Pair(0, new ArrayList<>());
        // 递归计算左右子树的最大路径
        // 只有在最大路径大于 0 时，才会选取对应子节点
        Pair leftPair = postorder(root.left);
        Pair rightPair = postorder(root.right);

        int cur = root.val;
        List<Integer> curPath = new ArrayList<>();

        // 返回当前节点的最大路径值和路径
        if (leftPair.sum > 0 && leftPair.sum > rightPair.sum) {
            curPath.addAll(leftPair.path);
            curPath.add(root.val);
            cur = cur + leftPair.sum;
        } else if (rightPair.sum > 0 && rightPair.sum > leftPair.sum) {
            curPath.addAll(rightPair.path);
            curPath.add(root.val);
            cur = cur + rightPair.sum;
        } else {
            curPath.add(root.val);
        }

        // 当前节点的最大路径和为 两侧路径的最大和 + 节点的值, 以及最大路径。
        // 更新最大路径和(本节点联通，为顶点)和路径
        // 左右子树不为0的情况下，以当前节点为顶点，联通路径，计算最大值
        if (leftPair.sum + rightPair.sum + root.val > maxSum) {
            List<Integer> temp;
            // 四种情况
            if (leftPair.sum > 0 && rightPair.sum > 0) {
                maxSum = leftPair.sum + rightPair.sum + root.val;
                temp = new ArrayList<>(leftPair.path);
                temp.add(root.val);
                temp.addAll(rightPair.path);
            } else if (leftPair.sum > 0) {
                maxSum = leftPair.sum + root.val;
                temp = new ArrayList<>(leftPair.path);
                temp.add(root.val);
            } else if (rightPair.sum > 0) {
                maxSum = rightPair.sum + root.val;
                temp = new ArrayList<>(rightPair.path);
                temp.add(root.val);
            } else {
                maxSum = root.val;
                temp = new ArrayList<>();
                temp.add(root.val);
            }
            maxPath = temp;
        }

        // 返回节点的最大路径值和路径
        return new Pair(cur, curPath);
    }

    // 存储当前节点的最大路径值和路径
    static class Pair{
        int sum;
        List<Integer> path;

        Pair(int sum, List<Integer> path) {
            this.sum = sum;
            this.path = path;
        }
    }
}
