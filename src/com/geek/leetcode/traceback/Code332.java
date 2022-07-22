package com.geek.leetcode.traceback;

import org.junit.Test;

import java.util.*;

/**
 * @author G.E.E.K.
 * @create 2022-05-12 22:16
 * 332. 重新安排行程
 * https://leetcode.cn/problems/reconstruct-itinerary/
 *
 * 思路：
 *
 */
public class Code332 {
    @Test
    public void test() {
        List<String> s1 = new ArrayList<>();
        s1.add("MUC");
        s1.add("LHR");
        List<String> s2 = new ArrayList<>();
        s2.add("JFK");
        s2.add("MUC");
        List<String> s3 = new ArrayList<>();
        s3.add("LHR");
        s3.add("SFO");
        List<List<String>> test = new ArrayList<>();
        test.add(s1);
        test.add(s2);
        test.add(s3);

        List<String> ans = new Solution332().findItinerary(test);
        System.out.println(ans);
    }

}

class Solution332 {
    // 需要升序，因此使用TreeMap，保持key的字典顺序
    private final HashMap<String, TreeMap<String, Integer>> targets = new HashMap<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>();

        for (List<String> ticket : tickets) {
            TreeMap<String, Integer> value = targets.getOrDefault(ticket.get(0), new TreeMap<>());
            value.put(ticket.get(1), value.getOrDefault(ticket.get(1), 0) + 1);
            targets.put(ticket.get(0), value);
        }

        result.add("JFK");

        backtracking(tickets.size(), result);

        return result;
    }

    public boolean backtracking(int ticketNum, List<String> result) {
        if (result.size() == ticketNum + 1) {
            return true;
        }

        // 获取本次出发的机场
        String last = result.get(result.size() - 1);

        // 如果有对应的航班，则进行处理
        if (targets.containsKey(last)) {    // 防止出现null，空指针溢出
            // 遍历航班（已使用TreeMap存储按字典序排序）
            for (Map.Entry<String, Integer> entry : targets.get(last).entrySet()) {
                int count = entry.getValue();
                // 如果航班未飞行
                if (count > 0) {
                    result.add(entry.getKey());
                    entry.setValue(count- 1);
                    if (backtracking(ticketNum, result)) return true;
                    entry.setValue(count);
                    result.remove(result.size() - 1);
                }
            }
        }

        return false;
    }
}