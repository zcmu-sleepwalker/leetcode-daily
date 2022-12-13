package framework.dp;


/**
 * 打家劫舍问题
 * dp[i] 代表从第i间开始抢 最多可以抢多少钱
 * 这里遍历是从后往前的
 *
 * @Author: LCH
 * @Date: 2021/3/27 2:53 PM
 */
public class HouseRobber {

    /**
     * 不能连着偷两家
     *
     * @param nums
     * @return
     */
    public int rob_01(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 2];

        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    /**
     * 不能连着偷两家
     *
     * @param nums
     * @return
     */
    public int rob_01_simple(int[] nums) {
        int n = nums.length;
        int dp_i = 0;
        int dp_i_1 = 0;
        int dp_i_2 = 0;

        for (int i = n - 1; i >= 0; i--) {
            dp_i = Integer.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }

    /**
     * 不能连着偷两家 而且围成了一个圈
     *
     * @param nums
     * @return
     */
    public int rob_02_main(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        return Integer.max(rob_02(nums, 1, n - 1),
                rob_02(nums, 0, n - 2));
    }

    public int rob_02(int[] nums, int start, int end) {
        int[] dp = new int[nums.length + 2];

        for (int i = end; i >= start; i--) {
            dp[i] = Integer.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    public int rob_02_simple(int[] nums, int start, int end) {
        int dp_i = 0;
        int dp_i_1 = 0;
        int dp_i_2 = 0;

        for (int i = end; i >= start; i--) {
            dp_i = Integer.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp_i;
        }
        return dp_i;
    }
}
