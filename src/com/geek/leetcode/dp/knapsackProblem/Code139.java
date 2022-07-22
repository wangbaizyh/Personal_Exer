package com.geek.leetcode.dp.knapsackProblem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author G.E.E.K.
 * @create 2022-05-24 17:03
 *
 */
public class Code139 {

}

// 回溯
// 暴力超时
class Solution139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        return backtrack(s, set, 0);
    }

    private boolean backtrack(String s, Set<String> wordDict, int startIndex) {
        if (startIndex == s.length()) {
            return true;
        }

        for (int i = startIndex; i < s.length(); i++) {
            String word = s.substring(startIndex, i + 1);
            if (wordDict.contains(word) && backtrack(s, wordDict, i + 1)) {
                return true;
            }
        }

        return false;
    }
}

// 记忆化搜索
class Solution139_01 {
    private  Set<String> set;
    private int[] memory;

    public boolean wordBreak(String s, List<String> wordDict) {
        set = new HashSet<>(wordDict);
        memory = new int[s.length()];
        return backtrack(s, 0);
    }

    private boolean backtrack(String s,int startIndex) {
        if (startIndex == s.length()) {
            return true;
        }

        if (memory[startIndex] == 1) return false;
        if (memory[startIndex] == 2) return true;

        for (int i = startIndex; i < s.length(); i++) {
            String word = s.substring(startIndex, i + 1);
            if (set.contains(word) && backtrack(s,i + 1)) {
                memory[startIndex] = 2;
                return true;
            }
        }

        // 这里是关键，找遍了startIndex~s.length()也没能完全匹配，标记从startIndex开始不能找到
        memory[startIndex] = 1;
        return false;
    }
}

// 记忆化搜索
class Solution139_02 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int[] memory = new int[s.length()];
        return backtrack(s, set, memory, 0);
    }

    private boolean backtrack(String s, Set<String> wordDict, int[] memory,int startIndex) {
        if (startIndex >= s.length()) {
            return true;
        }

        if (memory[startIndex] == 1) return false;
        if (memory[startIndex] == 2) return true;

        for (int i = startIndex; i < s.length(); i++) {
            String word = s.substring(startIndex, i + 1);
            if (wordDict.contains(word) && backtrack(s, wordDict, memory, i + 1)) {
                memory[startIndex] = 2;
                return true;
            }
        }

        memory[startIndex] = 1;
        return false;
    }
}

// 完全背包（能否装满问题）
class Solution139_03 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        // 状态：
        // dp[i] : 字符串长度为i的话，dp[i]为true，表示可以拆分为一个或多个在字典中出现的单词。
        boolean[] dp = new boolean[s.length() + 1];
        // 初始化：
        dp[0] = true;
        // 遍历顺序
        for (int i = 1; i <= s.length(); i++) {     // 遍历背包
            for (int j = 0; j < i; j++) {           // 遍历物品
                // 递推公式：
                // 如果确定dp[j] 是true，且 [j, i] 这个区间的子串出现在字典里，那么dp[i]一定是true。（j < i ）。
                // 所以递推公式是 if([j, i] 这个区间的子串出现在字典里 && dp[j]是true) 那么 dp[i] = true。
                String word = s.substring(j, i + 1);
                if (set.contains(word) && dp[j]) {
                    dp[i] = true;
                }
            }
        }

        return dp[s.length()];
    }
}