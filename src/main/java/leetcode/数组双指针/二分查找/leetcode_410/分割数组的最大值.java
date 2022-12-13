//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
//
// 设计一个算法使得这 m 个子数组各自和的最大值最小。
//
//
//
// 示例 1：
//
//
//输入：nums = [7,2,5,10,8], m = 2
//输出：18
//解释：
//一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4,5], m = 2
//输出：9
//
//
// 示例 3：
//
//
//输入：nums = [1,4,4], m = 3
//输出：4
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 10⁶
// 1 <= m <= min(50, nums.length)
//
// Related Topics 贪心 数组 二分查找 动态规划 👍 587 👎 0

package leetcode.数组双指针.二分查找.leetcode_410;

/**
 * @Author: LCH
 * @Date: 2021/12/11 11:21 上午
 */
public class 分割数组的最大值 {


    public static void main(String[] args) {

    }

    public static int splitArray(int[] nums, int m) {
        int min = getMin(nums);
        int max = getMax(nums);
        for (int i = min; i <= max; i++) {
            int n = getSplitCount(nums, i);
            if (n <= m) {
                return i;
            }
        }
        return -1;
    }

    private static int getSplitCount(int[] nums, int m) {
        int sum = 0;
        int count = 1;
        for (int num : nums) {
            if (sum + num > m) {
                count++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return count;
    }

    private static int getMax(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max += num;
        }
        return max;
    }

    private static int getMin(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Integer.max(max, num);
        }
        return max;
    }
}
