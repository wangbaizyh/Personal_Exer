package com.geek.jianzhi.stateMachine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author G.E.E.K.
 * @create 2022-06-10 21:02
 * 剑指 Offer 20. 表示数值的字符串
 * https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 *
 * 思路：有限状态自动机
 * 字符类型：
 * 空格 「 」、数字「 0—9 」 、正负号 「 +− 」 、小数点 「 . 」 、幂符号 「 eE 」 。
 *
 * 状态定义：
 * 按照字符串从左到右的顺序，定义以下 9 种状态。
 * 0.开始的空格
 * 1.幂符号前的正负号
 * 2.小数点前的数字
 * 3.小数点、小数点后的数字
 * 4.当小数点前为空格时，小数点、小数点后的数字
 * 5.幂符号
 * 6.幂符号后的正负号
 * 7.幂符号后的数字
 * 8.结尾的空格
 *
 * 结束状态：
 * 合法的结束状态有 2, 3, 7, 8 。
 *
 * 算法流程：
 * 初始化：
 *      状态转移表 states ：设 states[i] ，其中 i 为所处状态，states[i] 使用哈希表存储可转移至的状态。
 *      键值对 (key, value) 含义：若输入 key ，则可从状态 i 转移至状态 value 。
 *
 *      当前状态 p ： 起始状态初始化为 p = 0 。
 * 状态转移循环： 遍历字符串 s 的每个字符 c 。
 *      记录字符类型 t ： 分为四种情况。
 *          当 c 为正负号时，执行 t = 's' ;
 *          当 c 为数字时，执行 t = 'd' ;
 *          当 c 为 e , E 时，执行 t = 'e' ;
 *          当 c 为 . , 空格 时，执行 t = c （即用字符本身表示字符类型）;
 *          否则，执行 t = '?' ，代表为不属于判断范围的非法字符，后续直接返回 false 。
 *      终止条件： 若字符类型 t 不在哈希表 states[p] 中，说明无法转移至下一状态，因此直接返回 False 。
 *      状态转移： 状态 p 转移至 states[p][t] 。
 * 返回值： 跳出循环后，若状态 p∈2,3,7,8 ，说明结尾合法，返回 True ，否则返回 False 。
 *
 */
public class Offer20 {

}

// 有限状态自动机
class Solution20 {
    public boolean isNumber(String s) {
        // 状态转移表
        Map[] states = {
                new HashMap<Character,Integer>() {{put(' ', 0); put('s', 1); put('d', 2); put('.', 4);}},
                new HashMap<Character,Integer>() {{put('d', 2); put('.', 4); }},
                new HashMap<Character,Integer>() {{put('d', 2); put('.', 3); put('e', 5); put(' ', 8);}},
                new HashMap<Character,Integer>() {{put('d', 3); put('e', 5); put(' ', 8);}},
                new HashMap<Character,Integer>() {{put('d', 3);}},
                new HashMap<Character,Integer>() {{put('s', 6); put('d', 7);}},
                new HashMap<Character,Integer>() {{put('d', 7);}},
                new HashMap<Character,Integer>() {{put('d', 7); put(' ', 8);}},
                new HashMap<Character,Integer>() {{put(' ', 8);}}
        };

        // 初始状态
        int p = 0;
        // 字符类型
        // 分为六种情况
        // ' ':空格
        // 'd':数字0-9
        // 's':正负号
        // '.':小数点
        // 'e':幂符号
        // '?':不合法字符
        char t;

        // 状态转移循环
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') t = 'd';
            else if (c == '+' || c == '-') t = 's';
            else if (c == 'e' || c == 'E') t = 'e';
            else if (c == '.' || c == ' ') t = c;
            else t = '?';

            // 不存在转移状态
            if (!states[p].containsKey(t)) return false;

            // 状态转移
            p = (int) states[p].get(t);
        }

        return p == 2 || p == 3 || p == 7 || p == 8;
    }
}