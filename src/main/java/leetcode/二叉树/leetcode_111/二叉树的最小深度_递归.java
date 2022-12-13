//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 10⁵] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 645 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉树.leetcode_111;


/**
 * @Author: LCH
 * @Date: 2021/12/24 1:41 下午
 */
public class 二叉树的最小深度_递归 {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDep = minDepth(root.left);
        int rightDep = minDepth(root.right);
        if (leftDep != 0 && rightDep != 0){
            return Integer.min(leftDep,rightDep) + 1;
        }else if (leftDep == 0 && rightDep != 0){
            return rightDep + 1;
        }else if (rightDep == 0 && leftDep != 0){
            return leftDep + 1;
        }
        return 1;
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
