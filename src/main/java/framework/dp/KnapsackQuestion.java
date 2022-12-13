package framework.dp;


/**
 * 背包问题
 *
 * @Author: LCH
 * @Date: 2021/3/29 5:08 PM
 */
public class KnapsackQuestion {


    /**
     * 0-1背包问题
     * 给你一个可装载重量为W的背包和N个物品，每个物品有重量和价值两个属性。
     * 其中第i个物品的重量为wt[i]，价值为val[i]，现在让你用这个背包装物品，最多能装的价值是多少？
     *
     * @param W   重量
     * @param N   物品数量
     * @param wt  重量数组
     * @param val 物品价值数组
     * @return
     */
    public static int knapsack(int W, int N, int[] wt, int[] val) {
        int[][] dp = new int[N + 1][W + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new int[W + 1];
        }

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= W; w++) {
                if (w - wt[i] < 0) {
                    dp[i][w] = dp[i - 1][w];
                } else {
                    dp[i][w] = Integer.max(dp[i - 1][w],
                            dp[i - 1][w - wt[i - 1]] + val[i]);
                }
            }
        }

        return dp[N][W];
    }

    /**
     * 子集背包问题
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                dp[i][j] = false;
            }
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = true;
        }

        // 这里只用到了 i 和 i-1
        // 所以可以进行状态压缩
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }


    /**
     * 完全背包问题
     * 有一个背包，最大容量为amount，有一系列物品coins，每个物品的重量为coins[i]，每个物品的数量无限。
     * 请问有多少种方法，能够把背包恰好装满？
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;

        int[][] dp = new int[n + 1][amount + 1];

        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // 使用第i件物品 背包容量为j时 有dp[i][j]种方法可以将背包装满
        // 因为是从1开始算的，但是我们数组内是从0开始的 所以是coins[i - 1]
        // 因为dp数组的转移只和dp[i][..]和dp[i-1][..]有关，所以可以压缩状态，进一步降低算法的空间复杂度
        // 完全背包问题不能逆序，如果逆序了就没法利用以前第n个放入背包的空间
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][amount];
    }


    public static void main(String[] args) {
//        System.out.println(knapsack(10, 5, new int[]{2, 3, 4, 5, 6, 7}, new int[]{1, 2, 3, 4, 5, 6}));
//        System.out.println(canPartition(new int[]{4, 4, 4, 4, 4, 9}));
        System.out.println(canPartition(new int[]{4, 6}));

    }
}
