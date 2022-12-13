//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
//
// 你可以认为每种硬币的数量是无限的。
//
//
//
// 示例 1：
//
//
//输入：coins = [1, 2, 5], amount = 11
//输出：3
//解释：11 = 5 + 5 + 1
//
// 示例 2：
//
//
//输入：coins = [2], amount = 3
//输出：-1
//
// 示例 3：
//
//
//输入：coins = [1], amount = 0
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= coins.length <= 12
// 1 <= coins[i] <= 2³¹ - 1
// 0 <= amount <= 10⁴
//
// Related Topics 广度优先搜索 数组 动态规划 👍 1722 👎 0
package leetcode.动态规划.leetcode_322;

import java.util.HashMap;

/**
 * @Author: LCH
 * @Date: 2022/2/16 5:45 下午
 */
public class 零钱兑换 {

    /**
     * 相当于暴力算法，加了缓存
     */
    HashMap<Integer,Integer> memo = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (memo.get(amount) != null) {
            return memo.get(amount);
        }
        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int v = coinChange(coins, amount - coin);
            if (v == -1) {
                continue;
            }
            res = Math.min(res, v + 1);
        }
        memo.put(amount, res == Integer.MAX_VALUE ? -1 : res);
        return memo.get(amount);
    }



}
