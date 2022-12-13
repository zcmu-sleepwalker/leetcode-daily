package framework.dp;

import java.util.Arrays;

/**
 * 子序列问题
 * <p>
 * 第一种思路：一维数组
 * 1.1 在子数组array[0..i]中，以array[i]结尾的目标子序列（最长递增子序列）的长度是dp[i]。
 * <p>
 * 第二种思路：二维数组
 * 2.1 涉及两个字符串/数组时
 * 在子数组arr1[0..i]和子数组arr2[0..j]中，我们要求的子序列（最长公共子序列）长度为dp[i][j]。
 * 2.2 只有一个字符串/数组时
 * 在子数组array[i..j]中，我们要求的子序列（最长回文子序列）的长度为dp[i][j]。
 *
 * @Author: LCH
 * @Date: 2021/3/31 4:48 PM
 */
public class SubSequece {


    /**
     * 最长子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Integer.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Integer.max(res, dp[i]);
        }

        return dp[n];
    }

    public int longestPalindromeSubseq(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i <= n; i++) {
            dp[i][i] = 1;
        }

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (nums[i] == nums[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Integer.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][n - 1];
    }


    /**
     * 最大子序列之和
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;

        int[] dp = new int[n];

        dp[0] = nums[0];
        int max = nums[0];

        for (int i = 1; i < n; i++) {
//            if (dp[i - 1] + nums[i] > max) {
//                max = dp[i - 1] + nums[i];
//            }
//            dp[i] = Integer.max(max, nums[i]);
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
//        return dp[n-1];
    }

    public static void main(String[] args) {
        int[] aa = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(aa));
    }

}
