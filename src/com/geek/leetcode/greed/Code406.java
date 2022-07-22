package com.geek.leetcode.greed;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author G.E.E.K.
 * @create 2022-05-15 20:01
 * 406. 根据身高重建队列
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 *
 */
public class Code406 {

}

// 贪心算法
// 局部最优：优先按身高高的people的k来插入。插入操作过后的people满足队列属性
// 全局最优：最后都做完插入操作，整个队列满足题目队列属性
class Solution406 {
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });

        LinkedList<int[]> que = new LinkedList<>();

        for (int[] person : people) {
            que.add(person[1], person);
        }

        return que.toArray(new int[people.length][]);
    }
}