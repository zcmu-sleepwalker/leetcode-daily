package framework.recursion;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 递归算法
 * @Author: LCH
 * @Date: 2020/12/7 7:56 PM
 */
public class Recursion {

    private int memo[];

    /**
     * 打家劫舍（不能连着偷两家）
     * @param nums
     * @return
     */
    public int rob(int[] nums){
        memo = new int[nums.length];
        Arrays.fill(memo , -1);
        return dp(nums,0);
    }

    private int dp(int[] nums, int start){
        if (start > nums.length){
            return 0;
        }
        if (memo[start] != -1) {
            return memo[start];
        }
        int res = Integer.max(dp(nums,start + 1),
                dp(nums,start + 2) + nums[start]);
        memo[start] = res;

        return res;
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

    HashMap<TreeNode,Integer> treeMemo = new HashMap<>();
    /**
     * 打家劫舍（二叉树）
     * @param root
     * @return
     */
    public int treeRob(TreeNode root){
        if (root == null){
            return 0;
        }
        if (treeMemo.containsKey(root)){
            return treeMemo.get(root);
        }

        // 抢 然后取下下家
        int do_it = root.val +
                (root.left == null?0:treeRob(root.left.left) + treeRob(root.left.right)) +
                (root.right == null?0:treeRob(root.right.left) + treeRob(root.right.right));

        // 不抢 然后取下家
        int not_do = (root.left == null?0:treeRob(root.left)) +
                (root.right == null?0:treeRob(root.right));

        int res = Integer.max(do_it , not_do);
        treeMemo.put(root,res);
        return res;
    }

    public int treeRob_02(TreeNode root) {
        int[] res = treeRob_simple(root);
        return Math.max(res[0], res[1]);
    }

    public int[] treeRob_simple(TreeNode root){
        if (root == null){
            return new int[]{0,0};
        }

        int[] left = treeRob_simple(root);
        int[] right = treeRob_simple(root);

        // 抢 然后取下下家
        int do_it = root.val + left[0] + right[0];

        // 不抢 然后取下家
        int not_do = Integer.max(left[0], left[1]) + Integer.max(right[0], right[1]);

        return new int[]{not_do, do_it};
    }
}
