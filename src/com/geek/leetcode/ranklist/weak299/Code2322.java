package com.geek.leetcode.ranklist.weak299;

import java.util.ArrayList;
import java.util.List;

/**
 * @author G.E.E.K.
 * @create 2022-07-01 14:15
 * 2322. 从树中删除边的最小分数
 * https://leetcode.cn/problems/minimum-score-after-removals-on-a-tree/
 *
 * 思路：DFS时间戳
 * 假设根节点为 0
 *
 */
public class Code2322 {

}

/**
 *  DFS时间戳 + 枚举新树根节点 + 异或性质运用:
 *  题目是将整棵树切成3部分,我们就假设0为树原来的根节点(确定根节点方便求异或),切割之后另外两棵子树根节点为x与y
 *  假设x是y的根节点,由于枚举的是不同的点,因此不存在互相包含的情况
 *  此时有:in[x]<in[y]<=out[y]<=out[x] 其中in[y]<=out[y]必定成立,因此x是y的根节点等价于in[x]<in[y]&&out[y]<=out[x]
 *  利用时间戳的可以用来快速判断x与y节点之间的父子关系(以0为原始根节点)
 *  我们枚举删除边后产生的两个新的根节点,设a,b,c分别为0,x,y为根的切断后子树异或和,有以下3种情况:
 *  1.x是y的根节点且位于初始根节点0的同侧->异或和分别为:c=xor[y],b=xor[x]^xor[y],a=xor[0]^xor[x]
 *  2.y是x的根节点且位于初始根节点0的同侧->异或和分别为:b=xir[x],c=xor[y]^xor[x],a=xor[0]^xor[y]
 *  3.y与x分局初始根节点0的异侧->异或和分别为:c=xor[y],b=xor[x],a=xr[0]^xor[x]^xor[y]
 *  分别枚举x∈[1,n],y∈[x+1,n],维护异或和差值的最小值
 *
 */

// 无向图的遍历
// DFS时间戳
// 使用时钟来记录递归的顺序
class Solution2322 {
    int[] nums, xor, in, out; // xor[i]为以i为根的子树异或和,in和out分别是节点i dfs递归进栈和出栈的时间点
    int clock = 0;            // 全局时间戳(以0为根的dfs序)
    List<Integer>[] list;     // 存边

    public int minimumScore(int[] nums, int[][] edges) {
        this.nums = nums;
        int n = nums.length;
        xor = new int[n];
        in = new int[n];
        out = new int[n];
        int res = Integer.MAX_VALUE;

        // 统计各个节点的直连节点
        list = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            list[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            list[a].add(b);
            list[b].add(a);
        }
        // 初始化各个节点时间戳以及求每个节点为根的异或和
        // 遍历一遍无向连通图
        dfs(0, -1);
        // 枚举删除边后产生的两个新的根节点x与y
        // 设a,b,c分别为0,x,y为根切断后子树的异或和
        int a, b, c;
        // 遍历所有节点
        for (int x = 1; x < n; x++) {
            for (int y = x + 1; y < n; y++) {
                if (isParent(x, y)) {           // x是y根节点
                    a = xor[0] ^ xor[x];
                    b = xor[x] ^ xor[y];
                    c = xor[y];
                } else if (isParent(y, x)) {    // y是x根节点
                    a = xor[0] ^ xor[y];
                    b = xor[x];
                    c = xor[y] ^ xor[x];
                } else {                        // x与y分布异侧
                    a = xor[0] ^ xor[x] ^ xor[y];
                    b = xor[x];
                    c = xor[y];
                }
                // 维护异或和最大差值的最小值
                int max = Math.max(a, Math.max(b, c)), min = Math.min(a, Math.min(b, c));
                res = Math.min(res, max - min);
                if (res == 0) return 0;     // 为0提前退出
            }
        }

        return res;
    }

    // 判断x是否为y的父节点(不重合)
    // 通过时间戳
    private boolean isParent(int x, int y) {
        return in[x] < in[y] && out[y] <= out[x];
    }

    // 求节点时间戳和异或和:其中i为当前节点索引,fa为其父节点(用于避免走回头路)
    private void dfs(int i, int fa) {
        xor[i] = nums[i];   // xor[i]初始化为节点i的值
        in[i] = ++clock;    // 记录i进栈的时间戳
        for (int next : list[i]) {
            if (next != fa) {           // 不走回头路
                dfs(next, i);           // 求出以next为根的异或和与时间戳
                xor[i] ^= xor[next];    // xor[i]添上xor[next]
            }
        }
        out[i] = clock;     // 记录i出栈的时间戳
    }
}