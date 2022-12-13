//给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,1,4,null,2], k = 1
//输出：1
//
//
// 示例 2：
//
//
//输入：root = [5,3,6,2,4,null,null,1], k = 3
//输出：3
//
//
//
//
//
//
// 提示：
//
//
// 树中的节点数为 n 。
// 1 <= k <= n <= 10⁴
// 0 <= Node.val <= 10⁴
//
//
//
//
// 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 544 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉搜索树.leetcode_230;

/**
 * @Author: LCH
 * @Date: 2021/12/27 5:26 下午
 */
public class 二叉搜索树中第K小的元素 {

    public static int kthSmallest(TreeNode root, int k) {
        encode(root,k);
        return res;
    }

    static int res = 0;
    static int rank = 0;

    public static void encode(TreeNode root, int k) {
        if (root == null ){
            return ;
        }
        encode(root.left,k);
        // 操作
        rank++;
        if (rank == k){
            res = root.val;
            return;
        }
        encode(root.right,k);
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
