package framework.dp;

/**
 * 动态规划模板
 * 动态规划一把都是求最值为题，核心思想就是穷举。
 *
 * @Author: LCH
 * @Date: 2020/12/3 4:41 PM
 */
public class DynamicPrograming {

    /**
     * 斐波那契数列
     */
    static class Fibonacci {
        /**
         * 递归解法
         *
         * @param n
         * @return
         */
        private static int fibonacciSequence(int n) {
            if (n == 1 || n == 2) {
                return 1;
            }
            return fibonacciSequence(n - 1) + fibonacciSequence(n - 2);
        }

        /**
         * 递归 + 缓存
         *
         * @param n
         * @return
         */
        private static int fibonacciSequence2(int n) {
            if (n <= 0) {
                return 0;
            }
            int[] memo = new int[n + 1];

            return fibonacciHelper(n, memo);
        }

        private static int fibonacciHelper(int n, int[] memo) {
            if (n == 1 || n == 2) {
                return 1;
            }
            if (memo[n] != 0) {
                return memo[n];
            }
            memo[n] = fibonacciHelper(n - 1, memo) + fibonacciHelper(n - 2, memo);
            return memo[n];
        }

        /**
         * 迭代解法
         *
         * @param n
         * @return
         */
        private static int fibonacciSequence3(int n) {
            int[] res = new int[n + 1];
            res[1] = res[2] = 1;

            for (int i = 3; i <= n; i++) {
                res[i] = res[i - 1] + res[i - 2];
            }
            return res[n];
        }
    }

    /**
     * 凑零钱问题
     * <p>
     * 给你 k 种⾯值的硬币，⾯值分别为 c1, c2 ... ck ，
     * 每种硬币的数量⽆限，再给⼀个总⾦额 amount ，
     * 问你最少需要⼏枚硬币凑出这个⾦额，如果不可能凑出，算法返回 -1 。
     * <p>
     * 要符合「最优⼦结构」，⼦问题间必须互相独⽴。
     * 比如考出最高成绩只需要每门都考到最高就好了，各科互不干扰。如果加上条件语文和数学成绩相互制约，那么就不是最优子结构了。
     * <p>
     * base case
     * ⽬标⾦额为 0 时，所需硬币数量为 0；当⽬标⾦额⼩于 0 时，⽆解，返回 -1
     */
    static class CoinChangeSolution {

        /**
         * 递归算法 + 缓存
         *
         * @param coins
         * @param amount
         * @return
         */
        private static int coinChange(int[] coins, int amount) {
            if (amount < 0) {
                return -1;
            }

            int[] memo = new int[amount + 1];
            for (int i=0;i<memo.length;i++){
                memo[i] = -1;
            }

            return coinChangeHelper(coins, amount, memo);
        }

        private static int coinChangeHelper(int[] coins, int amount, int[] memo) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }

            if (memo[amount] >= 0) {
                return memo[amount];
            }

            int res = Integer.MAX_VALUE;

            for (int coin : coins) {
                int temp = coinChangeHelper(coins, amount - coin, memo);
                if (temp == -1) {
                    continue;
                }
                res = Math.min(res, temp + 1);
            }

            memo[amount] = res != Integer.MAX_VALUE?res:-1;
            return memo[amount];
        }

        /**
         * 动态规划
         * @param coins
         * @param amount
         * @return
         */
        private static int coinChange2(int[] coins, int amount) {
            if (amount < 0){
                return -1;
            }

            int[] dp = new int[amount+1];
            for (int i=0;i<dp.length;i++){
                dp[i] = amount+1;
            }
            dp[0] = 0;

            for (int i = 1; i < dp.length; i++) {

                for (int coin : coins){
                    if (i - coin < 0){
                        continue;
                    }
                    dp[i] = Math.min(dp[i] , 1 + dp[i-coin]);
                }
            }

            return dp[amount] == amount+1 ? -1 :dp[amount];
        }

    }


    public static void main(String[] args) {

//        System.out.println(Fibonacci.fibonacciSequence3(10));

        int[] coins = new int[]{186,419,83,408};

        System.out.println(CoinChangeSolution.coinChange2(coins, 6249));

    }


}
