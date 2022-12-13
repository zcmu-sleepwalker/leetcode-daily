//给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
//
// 二叉搜索树的定义如下：
//
//
// 任意节点的左子树中的键值都 小于 此节点的键值。
// 任意节点的右子树中的键值都 大于 此节点的键值。
// 任意节点的左子树和右子树都是二叉搜索树。
//
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//输出：20
//解释：键值为 3 的子树是和最大的二叉搜索树。
//
//
// 示例 2：
//
//
//
//
//输入：root = [4,3,null,1,2]
//输出：2
//解释：键值为 2 的单节点子树是和最大的二叉搜索树。
//
//
// 示例 3：
//
//
//输入：root = [-4,-2,-5]
//输出：0
//解释：所有节点键值都为负数，和最大的二叉搜索树为空。
//
//
// 示例 4：
//
//
//输入：root = [2,1,3]
//输出：6
//
//
// 示例 5：
//
//
//输入：root = [5,4,8,3,null,6,3]
//输出：7
//
//
//
//
// 提示：
//
//
// 每棵树有 1 到 40000 个节点。
// 每个节点的键值在 [-4 * 10^4 , 4 * 10^4] 之间。
//
// Related Topics 树 深度优先搜索 二叉搜索树 动态规划 二叉树 👍 78 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package leetcode.二叉搜索树.leetcode_1373;

/**
 * @Author: LCH
 * @Date: 2022/1/21 11:33 上午
 */
public class 二叉搜索树的最大键值和 {

    /**
     * 1、首先我想知道以我为根的这棵树是不是 BST，所以我肯定得知道左右子树是不是合法的 BST。因为如果这俩儿子有一个不是 BST，以我为根的这棵树肯定不会是 BST 的，对吧。
     * 2、如果左右子树都是合法的 BST，我得瞅瞅左右子树加上自己还是不是合法的 BST 了。因为按照 BST 的定义，当前节点的值应该大于左子树的最大值，小于右子树的最小值，否则就破坏了 BST 的性质。
     * 3、因为题目要计算最大的节点之和，如果左右子树加上我自己还是一棵合法的 BST，也就是说以我为根的整棵树是一棵 BST，那我需要知道我们这棵 BST 的所有节点值之和是多少，方便和别的 BST 子树争个高下，对吧。
     * @param root
     * @return
     */
    int maxValue = 0;

    public int maxSumBST(TreeNode root) {
        build(root);
        return maxValue;
    }

    public int[] build(TreeNode root){
        if (root == null){
            // 当到叶子节点的时候 左右返回这个值
            // 那么叶子节点需要大于左子树的最大值 也就是Integer.MIN_VALUE
            // 小于右子树的最小值，也就是Integer.MAX_VALUE
            // 就相当于对叶子结点不做任何限制
            return new int[]{1,Integer.MAX_VALUE,Integer.MIN_VALUE,0};
        }
        int[] left = build(root.left);
        int[] right = build(root.right);
        // int[0]：以root为根的二叉树是否是 BST，若为 1 则说明是 BST，若为 0 则说明不是 BST
        // int[1]：root为根的二叉树所有节点中的最小值
        // int[2]：root为根的二叉树所有节点中的最大值
        // int[3]：以root为根的二叉树所有节点值之和
        int[] res = new int[4];
        if (left[0] == 1 && right[0] == 1 && root.val > left[2] && root.val < right[1]) {
            res[0] = 1;
            res[1] = Math.min(left[1],root.val);
            res[2] = Math.max(right[2],root.val);
            res[3] = left[3] + right[3] + root.val;

            maxValue = Math.max(maxValue,res[3]);
        }else {
            res[0] = 0;
        }
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
}
