//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 3：
//
//
//输入：nums = [1,2,3]
//输出：3
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 1000
//
// Related Topics 数组 动态规划 👍 925 👎 0
package leetcode.动态规划.leetcode_213;

/**
 * @Author: LCH
 * @Date: 2022/2/15 5:50 下午
 */
public class 打家劫舍II {
    /**
     * 个人解法：
     *  1.拆分成两个区间，分别求出最大值，并返回
     *      0 -> len-2
     *      1 -> len-1
     */
    public int rob(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        if (nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }
        int len = nums.length;
        int[] dp_1 = new int[len-1];
        int[] dp_2 = new int[len];
        dp_1[0] = nums[0];
        dp_1[1] = Math.max(nums[0],nums[1]);
        dp_2[0] = 0;
        dp_2[1] = nums[1];
        dp_2[2] = Math.max(nums[2],nums[1]);
        for (int i = 2; i < dp_1.length; i++) {
            dp_1[i] = Math.max(dp_1[i-1] , dp_1[i-2] + nums[i]);
        }
        for (int i = 3; i < dp_2.length; i++) {
            dp_2[i] = Math.max(dp_2[i-1] , dp_2[i-2] + nums[i]);
        }
        return Math.max(dp_1[len-2],dp_2[len-1]);
    }


    /**
     * 示例代码，很优雅
     * 思路也是将数组分为两段，分别求出最值，最后取最大值
     */
    public int rob_01(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        return Math.max(robRange(nums, 0, n - 2),
                robRange(nums, 1, n - 1));
    }
    // 仅计算闭区间 [start,end] 的最优结果
    int robRange(int[] nums, int start, int end) {
        int n = nums.length;
        int dp_i_1 = 0, dp_i_2 = 0;
        int dp_i = 0;
        for (int i = end; i >= start; i--) {
            dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
