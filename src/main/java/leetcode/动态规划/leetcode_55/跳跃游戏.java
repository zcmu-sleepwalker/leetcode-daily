//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 判断你是否能够到达最后一个下标。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
//
//
// 示例 2：
//
//
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 10⁴
// 0 <= nums[i] <= 10⁵
//
// Related Topics 贪心 数组 动态规划 👍 1636 👎 0
package leetcode.动态规划.leetcode_55;

/**
 * @Author: LCH
 * @Date: 2022/2/15 11:04 上午
 */
public class 跳跃游戏 {
    // 每一步都计算一下从当前位置最远能够跳到哪里，然后和一个全局最优的最远位置farthest做对比，
    // 通过每一步的最优解，更新全局最优解，这就是贪心。
    boolean canJump(int[] nums) {
        int len = nums.length;
        int maxIndex = 0;
        // 为什么这里是到len-1
        // 如果直接给我们一个数的话，就不走for循环了
        // 直接后面进行判断
        for (int i = 0; i < len - 1 ; i++) {
            maxIndex = Math.max(maxIndex,nums[i] + i);
            if (maxIndex <= i){
                return false;
            }
        }
        // 当前maxIndex是否大于等于最后的index
        return maxIndex >= len -1;
    }
}
