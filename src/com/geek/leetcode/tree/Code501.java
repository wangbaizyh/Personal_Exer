package com.geek.leetcode.tree;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-05-08 16:05
 * 501. 二叉搜索树中的众数
 * https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
 *
 */
public class Code501 {

}

// 普通二叉树的解法
class Solution501 {
    public int[] findMode(TreeNode root) {
        // map存储节点的频率
        HashMap<Integer, Integer> map = new HashMap<>();
        // 遍历整棵树
        inorder(root, map);

        // 优先队列进行排序
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                (o1, o2) -> map.get(o2) - map.get(o1)
        );

        for (Integer key : map.keySet()) {
            queue.offer(key);
        }

        List<Integer> list = new ArrayList<>();
        int target = queue.poll();
        int value = map.get(target);
        list.add(target);

        while (!queue.isEmpty()) {
            target = queue.poll();
            if (map.get(target) != value) break;
            list.add(target);
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    public void inorder(TreeNode root, HashMap<Integer,Integer> map) {
        if (root == null) return;

        inorder(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        inorder(root.right, map);
    }
}

// 利用搜索二叉树的特性
class Solution501_01 {
    List<Integer> result = new ArrayList<>();
    int maxCount = 0;
    int count = 0;
    TreeNode pre = null;

    private void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);

        int value = node.val;
        // 计数
        if (pre == null || value != pre.val){
            // 第一个数 || 当前值跟前节点的值不同，重置频率
            count = 1;
        } else {
            count++;
        }
        // 更新结果以及maxCount
        // 频率大于最大频率
        if (count > maxCount) {
            // 清空之前列表
            result.clear();
            result.add(value);
            // 更新最大频率
            maxCount = count;
        } else if (count == maxCount) {
            result.add(value);
        }
        pre = node;

        inorder(node.right);
    }


    public int[] findMode(TreeNode root) {
        inorder(root);
        int len = result.size();
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = result.get(i);
        }

        return ans;
    }
}
