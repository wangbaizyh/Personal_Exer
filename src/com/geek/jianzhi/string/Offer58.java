package com.geek.jianzhi.string;

/**
 * @author G.E.E.K.
 * @create 2022-04-25 18:48
 * 剑指 Offer 58 - II. 左旋转字符串
 * https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/
 *
 * 字符串
 *
 * 总结：
 * 344.反转字符串→第一次用到双指针进行反转字符串。
 * 541. 反转字符串II→固定规律反转，for循环上做文章。
 * 151.翻转字符串里的单词→对一句话里的单词顺序进行反转，先整体反转再局部反转 是一个很妙的思路。
 * 剑指 Offer 58 - II. 左旋转字符串→本题也是先整体反转再局部反转 或者 先局部反转再整体反转
 *
 */
public class Offer58 {

}

class Solution58 {
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        swap(chars,0,chars.length - 1);
        swap(chars, chars.length - n, chars.length - 1);
        swap(chars, 0, chars.length - n - 1);

        return new String(chars);
    }

    // 反转字符串
    private void swap(char[] chars,int left,int right){
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
    }
}

class Solution58_01 {
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        swap(chars,0,n - 1);
        swap(chars, n, chars.length - 1);
        swap(chars, 0, chars.length - 1);

        return new String(chars);
    }

    // 反转字符串
    private void swap(char[] chars,int left,int right){
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;

            left++;
            right--;
        }
    }
}

class Solution58_02 {
    public String reverseLeftWords(String s, int n) {
        int len = s.length();
        StringBuilder sb = new StringBuilder(s);
        swap(sb,0,n - 1);
        swap(sb, n, len - 1);

        return sb.reverse().toString();
    }

    // 反转字符串
    private void swap(StringBuilder sb,int left,int right){
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, tmp);

            left++;
            right--;
        }
    }
}
