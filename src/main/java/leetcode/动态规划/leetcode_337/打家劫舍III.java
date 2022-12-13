//小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 root 。 
//
// 除了 root 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接
//相连的房子在同一天晚上被打劫 ，房屋将自动报警。 
//
// 给定二叉树的 root 。返回 在不触动警报的情况下 ，小偷能够盗取的最高金额 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: root = [3,2,3,null,3,null,1]
//输出: 7 
//解释: 小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7 
//
// 示例 2: 
//
// 
//
// 
//输入: root = [3,4,5,1,3,null,1]
//输出: 9
//解释: 小偷一晚能够盗取的最高金额 4 + 5 = 9
// 
//
// 
//
// 提示： 
//
// 
//
// 
// 树的节点数在 [1, 10⁴] 范围内 
// 0 <= Node.val <= 10⁴ 
// 
// Related Topics 树 深度优先搜索 动态规划 二叉树 👍 1137 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package leetcode.动态规划.leetcode_337;


import java.util.HashMap;

/**
 * @Author: LCH
 * @Date: 2022/2/15 5:50 下午
 */
public class 打家劫舍III {

    HashMap<TreeNode, Integer> memo = new HashMap<>();

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (memo.get(root) != null) {
            return memo.get(root);
        }
        // 抢
        int do_it = root.val + (root.left == null ? 0 : rob(root.left.left)) + (root.left == null ? 0 : rob(root.left.right))
                + (root.right == null ? 0 : rob(root.right.left)) + (root.right == null ? 0 : rob(root.right.right));
        // 不抢
        int not_do = rob(root.left) + rob(root.right);

        memo.put(root, Math.max(do_it, not_do));
        return memo.get(root);
    }

    // 使用二维数组，速度更快，不需要memo缓存了。虽然时间复杂度一样，但是更快
    int rob_01(TreeNode root) {
        int[] res = dp(root);
        return Math.max(res[0], res[1]);
    }

    // 返回一个大小为 2 的数组 arr
    // arr[0] 表示不抢 root 的话，得到的最大钱数
    // arr[1] 表示抢 root 的话，得到的最大钱数
    private int[] dp(TreeNode root) {
        if (root == null){
            return new int[2];
        }

        int[] left = dp(root.left);
        int[] right = dp(root.right);

        // 抢，下家就不能抢了
        int do_it = root.val + left[0] + right[0];
        // 不抢，下家可抢可不抢，取决于收益大小
        int not_do = Math.max(left[0], left[1])
                + Math.max(right[0], right[1]);

        return new int[]{not_do,do_it};
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
