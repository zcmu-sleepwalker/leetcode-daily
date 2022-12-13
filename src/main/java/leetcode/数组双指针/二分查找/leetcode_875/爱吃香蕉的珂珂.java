//珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
//
// 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。
//
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
//
// 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
//
//
//
//
//
//
// 示例 1：
//
// 输入: piles = [3,6,7,11], H = 8
//输出: 4
//
//
// 示例 2：
//
// 输入: piles = [30,11,23,4,20], H = 5
//输出: 30
//
//
// 示例 3：
//
// 输入: piles = [30,11,23,4,20], H = 6
//输出: 23
//
//
//
//
// 提示：
//
//
// 1 <= piles.length <= 10^4
// piles.length <= H <= 10^9
// 1 <= piles[i] <= 10^9
//
// Related Topics 数组 二分查找 👍 232 👎 0

package leetcode.数组双指针.二分查找.leetcode_875;

import java.util.Arrays;

/**
 * @Author: LCH
 * @Date: 2021/12/11 4:42 下午
 */
public class 爱吃香蕉的珂珂 {

    public static void main(String[] args) {
//        int[] nums = new int[]{7, 2, 5, 10, 8, 4, 2, 4, 5, 4, 5, 3, 3, 5, 6, 7, 8};
//        int[] nums = new int[]{3,6,7,11};
        int[] nums = new int[]{312884470};
        System.out.println(minEatingSpeed(nums, 312884469));
    }

    public static int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        int min = 1;
        int max = piles[piles.length - 1];

        while (min <= max) {
            int mid = min + (max - min) / 2;
            int n = getHours(piles,mid);
            if (n == h){
                max = mid - 1;
            }else if (n > h){
                min = mid + 1;
            }else if (n < h){
                max = mid - 1;
            }
        }
        return min;
    }

    private static int getHours(int[] nums, int speed){
        int sum = 0;
        for (int num : nums){
            sum += num/speed;
            if (num % speed != 0){
                sum++;
            }
        }
        return sum;
    }
}
