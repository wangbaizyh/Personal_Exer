package com.geek.leetcode.dp.treedp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-07-18 16:35
 * 2246. 相邻字符不同的最长路径
 * https://leetcode.cn/problems/longest-path-with-different-adjacent-characters/
 *
 * 思路：树形dp
 * 不过根节点的路径问题,采用求两边最优再进行合并的解法
 * 求的是符合条件的路径长度
 *
 * 拓展：返回合法路径
 * 思路：使用一个类进行返回，记录：长度、路径，迭代更新
 *
 */
public class Code2246 {

}

class Solution2246 {
    // 存储节点i的孩子节点,i为父节点
    List<Integer>[] children;
    // 节点个数
    int n;
    String s;
    // 根节点编号默认为0
    final int root = 0;
    // 结果
    int ans;


    public int longestPath(int[] parent, String s) {
        this.s = s;
        n = parent.length;
        children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        // 初始化孩子节点表
        for (int i = 1; i < n; i++) {
            // parent数组的意义:索引i->子节点;parent[i]->i的父节点
            children[parent[i]].add(i);
        }
        // 开启dfs
        dfs(root);
        return ans;
    }

    // 返回以编号root为起点符合条件的最长路径长度
    // 这个以root为起点的路径只向root的孩子节点方向进行延伸,而不考虑root父节点的方向的路径
    private int dfs(int root) {
        // 越过叶子返回0, 空节点不组成任何路径
        if (root >= n) return 0;
        // max1和max2为root子节点为起点的路径中最长的两条,这两部分最后会直接影响res
        int max1 = 0, max2 = 0;
        // 遍历root的每个叶子节点并求出其符合条件的最长路径
        for (int child : children[root]) {
            // 当前子节点child的最长合法路径长度
            int childLen = dfs(child);
            // 更新max1与max2
            if (childLen > max1 && s.charAt(child) != s.charAt(root)) {
                // childLen最大且能与root拼接
                max2 = max1;
                max1 = childLen;
            } else if (childLen > max2 && s.charAt(child) != s.charAt(root)) {
                // childLen次大且能与root拼接
                max2 = childLen;
            }
        }
        // 将经过root的合法路径长度更新进去res->左边+root+右边=经过root的合法路径长度(root为顶点)
        ans = Math.max(ans, max1 + max2 + 1);
        // 最后返回以root为起点的合法路径最长长度,显然为最长的那一条长度max1+root本身长度1
        return max1 + 1;
    }
}
