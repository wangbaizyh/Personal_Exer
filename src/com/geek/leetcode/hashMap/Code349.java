package com.geek.leetcode.hashMap;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author G.E.E.K.
 * @create 2022-04-17 11:37
 * 349. 两个数组的交集
 * https://leetcode-cn.com/problems/intersection-of-two-arrays/
 *
 * 哈希表: HashSet
 */
public class Code349 {

}

class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 使用两个集合
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();

        for (int i : nums1) {
            set.add(i);
        }

        for (int i : nums2) {
            if (set.contains(i)) {
                result.add(i);
            }
        }

        int[] resArr = new int[result.size()];
        int index = 0;

        for (int i : result) {
            resArr[index++] = i;
        }

        return resArr;
    }
}

class Solution349_01 {
    public int[] intersection(int[] nums1, int[] nums2) {
        // 使用一个集合和一个列表
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : nums1) {
            set.add(i);
        }

        for (int i : nums2) {
            if (set.contains(i)) {
                list.add(i);
                set.remove(i);
            }
        }

        // 将列表转换为数组，使用Java8的stream
        return list.stream().mapToInt(t -> t).toArray();
    }
}
