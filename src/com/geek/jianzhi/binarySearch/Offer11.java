package com.geek.jianzhi.binarySearch;

/**
 * @author G.E.E.K.
 * @create 2022-05-31 14:56
 * 剑指 Offer 11. 旋转数组的最小数字
 * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 *
 * 思路：二分查找
 *
 */
public class Offer11 {

}

class Solution11 {
    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (numbers[mid] < numbers[high]) {
                high = mid;
            } else if (numbers[mid] > numbers[high]) {
                low = mid + 1;
            } else {
                high--;
            }
        }

        return numbers[low];
    }
}