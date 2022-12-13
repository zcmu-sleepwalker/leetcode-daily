package framework.dp;

/**
 * 买卖股票问题
 * 关键词：状态机
 * 我们用三维数组来表示三个维度
 * dp[3][2][1] 的含义就是：今天是第三天，我现在手上持有着股票，至今最多进行 2 次交易，值就是当前的利润。
 * 遍历是从前往后的
 *
 * @Author: LCH
 * @Date: 2021/3/27 10:12 AM
 */
public class StockQuestion {

    /**
     * base case:
     * dp[-1][k][0] = 0 ; i是从0开始的 -1代表还未开始 所有收益肯定是0
     * dp[-1][k][1] = 负无穷 ; 还未开始 肯定无法持有股票
     * dp[i][0][0] = 0 ; k为0意味着无法进行交易 所以收益肯定是0
     * dp[i][0][1] = 负无穷 ; k为0意味着无法进行交易 肯定无法持有股票
     *
     * 状态转移方程:
     * dp[i][k][0] = max (dp[i-1][k][0] , dp[i-1][k][1] + prices[i]); 如果我今天没有持有股票 1.我昨天未持有，今天按兵不动。 2.我昨天持有了，今天卖了。
     * dp[i][k][1] = max (dp[i-1][k][1] , dp[i-1][k-1][0] - prices[i]); 如果我今天持有股票 1.我昨天持有股票，今天按兵不动。 2.我昨天未持有，今天买了。
     */


    /**
     * 买卖股票的最佳时机（只能买一次）
     *
     * @param prices
     * @return
     */
    public int maxProfit_01(int[] prices) {
        // 因为只能买一次 所以k = 1
        // base case: dp[i][0][0] = 0
        // 所以我们可以将三维数组转化成二维数组，将k去掉。
        int n = prices.length;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Integer.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Integer.max(dp[i - 1][1], -prices[i]);
        }

        return dp[n - 1][0];
    }

    /**
     * 买卖股票的最佳时机（只能买一次）
     * 时间复杂度O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit_01_simple(int[] prices) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            dp_i_0 = Integer.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Integer.max(dp_i_1, -prices[i]);
        }
        return dp_i_0;
    }


    /**
     * 买卖股票的最佳时机（无限次购买）
     *
     * @param prices
     * @return
     */
    public int maxProfit_02(int[] prices) {
        // 如果k为正无穷 那么k 和 k - 1 其实是一样的
        // 我们又可以将三维数组转化为二维数组了
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Integer.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Integer.max(dp_i_1, temp - prices[i]);
        }

        return dp_i_0;
    }


    /**
     * 买卖股票的最佳时机（无限次购买，但是每次卖出之后，必须要等一天才能交易）
     *
     * @param prices
     * @return
     */
    public int maxProfit_03(int[] prices) {
        // 状态转移方程需要根据cooldown来进行改写
        // 第i天选择buy的时候，需要从i-2的转移方程来。
        // dp[i][0] = max (dp[i-1][0] , dp[i-1][1] + prices[i])
        // dp[i][1] = max (dp[i-1][1] , dp[i-2][1] - prices[i])

        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;
        int dp_pre_1 = 0;

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Integer.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Integer.max(dp_i_1, dp_pre_1 - prices[i]);
            dp_pre_1 = temp;
        }
        return dp_i_0;
    }

    /**
     * 买卖股票的最佳时机（无限次购买，但是每次卖出之后，需要交手续费）
     *
     * @param prices
     * @return
     */
    public int maxProfit_04(int[] prices, int fee) {
        int n = prices.length;
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = dp_i_0;
            dp_i_0 = Integer.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Integer.max(dp_i_1, temp - prices[i] - fee);
        }
        return dp_i_0;
    }

    /**
     * 买卖股票的最佳时机（只能交易两次）
     *
     * @param prices
     * @return
     */
    public int maxProfit_05(int[] prices) {
        int max_k = 2;
        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = Integer.MIN_VALUE;
                }
                dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k - 1][1] + prices[i]);
                dp[i][k][1] = Integer.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }

        }
        return dp[n][max_k][0];
    }

    /**
     * 买卖股票的最佳时机（只能交易两次）
     *
     * @param prices
     * @return
     */
    public int maxProfit_05_simple(int[] prices) {
        int dp_i_1_0 = 0;
        int dp_i_2_0 = 0;
        int dp_i_1_1 = Integer.MIN_VALUE;
        int dp_i_2_1 = Integer.MIN_VALUE;
        for (int price :prices){
            dp_i_1_0 = Integer.max(dp_i_1_0 , dp_i_1_1 + price);
            dp_i_1_1 = Integer.max(dp_i_1_1 , -price);
            dp_i_2_0 = Integer.max(dp_i_2_0 , dp_i_2_1 + price);
            dp_i_2_1 = Integer.max(dp_i_2_1 , dp_i_1_0 - price);
        }
        return dp_i_2_0;
    }

    /**
     * 买卖股票的最佳时机（只能交易n次）
     *
     * @param prices
     * @return
     */
    public int maxProfit_06(int[] prices, int max_k) {
        int n = prices.length;

        // 这里需要限制一下k的大小
        // 因为买和卖需要两天 如果k超过了n / 2的话，那就没有约束作用了
        if (max_k > n / 2){
            // 这里变成了可以交易无限次
            maxProfit_03(prices);
        }

        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i - 1 == -1) {
                    dp[0][k][0] = 0;
                    dp[0][k][1] = Integer.MIN_VALUE;
                }
                dp[i][k][0] = Integer.max(dp[i - 1][k][0], dp[i - 1][k - 1][1] + prices[i]);
                dp[i][k][1] = Integer.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }

        }
        return dp[n][max_k][0];
    }


    public static void main(String[] args) {
        System.out.println();
    }

}