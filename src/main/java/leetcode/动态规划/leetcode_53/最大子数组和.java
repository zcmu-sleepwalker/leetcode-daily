//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
// 子数组 是数组中的一个连续部分。
//
//
//
// 示例 1：
//
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：1
//
//
// 示例 3：
//
//
//输入：nums = [5,4,-1,7,8]
//输出：23
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
//
//
//
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
// Related Topics 数组 分治 动态规划 👍 4350 👎 0
package leetcode.动态规划.leetcode_53;

/**
 * @Author: LCH
 * @Date: 2022/2/15 4:40 下午
 */
public class 最大子数组和 {

    // Time Limit Exceeded
    public int maxSubArray(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = 0;
            for (int j = i; j < nums.length; j++) {
                num += nums[j];
                max = Math.max(max,num);
            }
        }
        return max;
    }

    // 以nums[i]为结尾的「最大子数组和」为dp[i]
    // 动态转移方程：dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
    public int maxSubArray_dp(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i] ,nums[i]);
        }
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    public int maxSubArray_dp_01(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int dp_0 = nums[0];
        int dp_1 = 0;
        int res = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp_1 = Math.max(dp_0 + nums[i] ,nums[i]);
            dp_0 = dp_1;
            res = Math.max(dp_1,res);
        }
        return res;
    }
}
