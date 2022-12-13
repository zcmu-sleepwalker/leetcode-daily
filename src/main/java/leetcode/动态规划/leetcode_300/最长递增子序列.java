//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。
//
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -104 <= nums[i] <= 104
//
//
//
//
// 进阶：
//
//
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 数组 二分查找 动态规划
// 👍 1983 👎 0

package leetcode.动态规划.leetcode_300;

/**
 * @Author: LCH
 * @Date: 2021/11/3 11:28 AM
 */
public class 最长递增子序列 {


    public static void main(String[] args) {
        int[] nums = new int[]{3,5,3,2,5,43,23,2,43,1,12,3123,4,34,1,12,3,1,5,3,412,233,453,6,646,545,654};
        System.out.println(lengthOfLIS(nums));
    }

    /**
     * 主要的思路就是通过dp[i-1]推出dp[i]
     * 两层遍历寻找到当nums[j]<nums[i]时，dp数组里的最大值，我们将值+1即可，初始值为0。
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int max = nums[i];
            int currentMax = 0;
            for (int j = 0; j <= i; j++) {
                if (nums[j] < max) {
                    currentMax = Integer.max(currentMax, dp[j]);
                }
            }
            dp[i] = currentMax + 1;
        }

        // 这样写会比较优雅
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//                if (nums[j] < nums[i]){
//                    dp[i] = Math.max(dp[i],dp[j] + 1);
//                }
//            }
//        }

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            result = Integer.max(dp[i], result);
        }
        return result;
    }
}
