package com.geek.leetcode.stack$queue.heap;

import org.junit.Test;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-04-28 15:33
 * 347. 前 K 个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 *
 * Java的优先队列默认是小顶堆
 *
 * 三种方法：
 *         1、优先队列（小根堆）-- Comparator的设计可以有多种：
 *              - Map.Entry<Integer,Integer>（存entry） -- 比较map.Entry::getValue
 *              - Integer（存key） -- 比较 map::get
 *              - []int -- 比较 o -> o[1] 第一个元素（存的频次）
 *         2、快速排序（不推荐） 实现比较复杂
 *              - 只排序大的那一边（取到k个就停止）
 *              - 基准随机选取
 *         3、桶排序 -- 构造一个List<Integer>[] 数组，下标为频次，利用 数组 + 列表的结合来实现：
 *              - 通过数组下标进行自动排序
 *              - 取的时候，因为取前k大，从后往前取
 *
 * 优先队列：时间复杂度 O(N log k)
 *
 *
 */
public class Code347 {
    @Test
    public void test() {
        int[] res = new Solution347_04().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(res));
    }

}

// 堆排序（Map.Entry）
class Solution347_01 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // 频率字典
        HashMap<Integer, Integer> map = new HashMap<>();
        // 统计元素出现频率, 使用HashMap
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(
                // (o1, o2) -> o1.getValue() - o2.getValue()
                Comparator.comparingInt(Map.Entry::getValue)
        );

        // 构建容量为k的小顶堆
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            priorityQueue.offer(entry);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        // 返回频率前k高的元素
        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll().getKey();
        }

        return result;
    }
}

// 堆排序
class Solution347_01_1 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // 频率字典
        HashMap<Integer, Integer> map = new HashMap<>();
        // 统计元素出现频率, 使用HashMap
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(
                // (o1, o2) -> o1.getValue() - o2.getValue()
                Comparator.comparingInt(Map.Entry::getValue)
        );

        // 构建容量为k的小顶堆
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if (priorityQueue.size() == k) {
                if (priorityQueue.peek().getValue() < entry.getValue()) {
                    priorityQueue.poll();
                    priorityQueue.offer(entry);
                }
            } else {
                priorityQueue.offer(entry);
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll().getKey();
        }

        return result;
    }
}

// 堆排序（Integer）
// 比较函数的另一种写法
class Solution347_01_2 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // 频率字典
        HashMap<Integer, Integer> map = new HashMap<>();
        // 统计元素出现频率, 使用HashMap
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                // (o1, o2) -> map.get(o1) - map.get(o2)
                Comparator.comparingInt(map::get)
        );

        // 构建容量为k的小顶堆
        for (Integer key: map.keySet()) {
            if (priorityQueue.size() == k) {
                if (map.get(priorityQueue.peek()) < map.get(key)) {
                    priorityQueue.poll();
                    priorityQueue.offer(key);
                }
            } else {
                priorityQueue.offer(key);
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll();
        }

        return result;
    }
}

// 堆排序（int[]）
// 使用数组来排序
class Solution347_02 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // 频率字典
        HashMap<Integer, Integer> map = new HashMap<>();
        // 统计元素出现频率, 使用HashMap
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(
                // (o1, o2) -> o1[1] - o2[1]
                Comparator.comparingInt(o -> o[1])
        );

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();

            if (priorityQueue.size() == k) {
                if (priorityQueue.peek()[1] < count) {
                    priorityQueue.poll();
                    priorityQueue.offer(new int[]{num,count});
                }
            } else {
                priorityQueue.offer(new int[]{num,count});
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll()[0];
        }

        return result;
    }
}

// 快速排序
class Solution347_03 {
    public int[] topKFrequent(int[] nums, int k) {
        // 统计元素出现频率, 使用HashMap
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 使用列表来存储 元素以及出现的频次
        List<int[]> values = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }

        int[] ret = new int[k];
        // 快排并返回频次前k的元素
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        // 随机选取一个为基准进行排序
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        // 交换元素到开头
        Collections.swap(values, picked, start);

        // 基准
        int base = values.get(start)[1];
        int[] tmp = values.get(start);
        // 初始化大于等于base的元素应该的下标
        int left = start, right = end;
        while (left < right) {
            // 找到小于base的数
            while (left < right && values.get(right)[1] <= base) right--;
            // 覆盖到左边
            values.set(left, values.get(right));

            // 找到大于base的数
            while (left < right && values.get(left)[1] >= base) left++;
            // 覆盖到右边
            values.set(right, values.get(left));
        }
        values.set(left, tmp);

        // 当left包含的多余k个时，虽然此时左边都比left的大，左区间仍是乱序的，
        // 对于k而言相当于没排序
        if (k <= left - start) {
            // 缩小范围，向左
            qsort(values, start, left - 1, ret, retIndex, k);
        } else {
            // 当left包含元素小于k时，起码left左边的都大于left，
            // 因此left左边的也大于k，这些数字可以放入结果中
            for (int i = start; i <= left; i++) {
                ret[retIndex++] = values.get(i)[0];
            }
            // 当left和起点间的个数小于k时，
            // 则从left到end再继续找剩下的前（k - （left - start + 1））大的元素
            if (k > left - start + 1) {
                qsort(values, left + 1, end, ret, retIndex, k - (left - start + 1));
            }
        }
    }
}

// 基于桶排序求解「前 K 个高频元素」
class Solution347_04 {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // 桶排序
        // 将频率作为数组下标，对于出现频率不同的数字集合，存入对应的数组下标
        List<Integer>[] list = new ArrayList[nums.length + 1];
        for(int key : map.keySet()){
            // 获取出现的次数作为下标
            int value = map.get(key);

            if(list[value] == null){
                list[value] = new ArrayList<>();
            }

            list[value].add(key);
        }

        // 倒序遍历数组获取出现顺序从大到小的排列
        int j = 0;
        boolean flag = false;

        for(int i = list.length - 1; i >= 0 ;i--){
            if (flag) break;

            if (list[i] == null) continue;

            for (int num : list[i]) {
                if (j == k) {
                    flag = true;
                    break;
                }

                result[j] = num;
                j++;
            }
        }

        return result;
    }
}


