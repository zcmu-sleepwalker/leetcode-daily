//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
//
// 假设你总是可以到达数组的最后一个位置。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 示例 2:
//
//
//输入: nums = [2,3,0,1,4]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 10⁴
// 0 <= nums[i] <= 1000
//
// Related Topics 贪心 数组 动态规划 👍 1403 👎 0
package leetcode.动态规划.leetcode_45;

/**
 * @Author: LCH
 * @Date: 2022/2/15 11:03 上午
 */
public class 跳跃游戏II {
    /**
     * 个人垃圾代码
     * 个人思路：
     *  直接定义一个dp数组，因为nums[i]里放的是最大步数，那么直接每次遍历所有的值，推导出dp[i + nums[i]]
     *  会后直接返回dp[]数组的最后一个值
     */
    public int jump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            while (num > 0) {
                if (i + num < len){
                    dp[i + num] = Math.min(dp[i] + 1, dp[i + num]);
                }
                num--;
            }
        }
        return dp[len - 1];
    }
}
