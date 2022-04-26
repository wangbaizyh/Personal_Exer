package com.geek.leetcode.string;

/**
 * @author G.E.E.K.
 * @create 2022-04-25 16:34
 * 151. 颠倒字符串中的单词
 * https://leetcode-cn.com/problems/reverse-words-in-a-string/
 *
 * 双指针法：快慢指针法
 *
 */
public class Code151 {

}

class Solution151_01 {
    /**
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords(String s) {
        // 去除多余空格，保证单词之间之只有一个空格，且字符串首尾没空格。
        char[] chars = removeSpace(s);
        // 先整体反转一次
        swap(0, chars.length - 1,chars);
        int start = 0;
        for (int i = 0; i <= chars.length; i++) {
            // 到达空格或者串尾，说明一个单词结束。进行翻转。
            if (i == chars.length || chars[i] == ' ') {
                swap(start, i - 1, chars);
                // 更新下一个单词的开始下标start
                start = i + 1;
            }
        }

        return new String(chars);
    }


    // 去除所有空格并在相邻单词之间添加空格, 快慢指针法。
    private char[] removeSpace(String s) {
        // 慢指针
        int slow = 0;
        char[] chars = s.toCharArray();

        for (int fast = 0; fast < chars.length; fast++) {
            //遇到非空格就处理，即删除所有空格。
            if (s.charAt(fast) != ' '){
                // 手动控制空格，给单词之间添加空格。
                // slow != 0 说明不是第一个单词，需要在单词前添加空格。
                if (slow != 0) {
                    chars[slow++] = ' ';
                }

                // 补上该单词，遇到空格说明单词结束。
                while (fast < chars.length && chars[fast] != ' ') {
                    chars[slow++] = chars[fast++];
                }
            }
        }

        // slow的大小即为去除多余空格后的大小。
        return new String(chars).substring(0, slow).toCharArray();
    }

    // 反转字符串
    private void swap(int left,int right, char[] chars){
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
    }
}

// 方法二：使用额外的内存空间来进行处理，使用StringBuilder
