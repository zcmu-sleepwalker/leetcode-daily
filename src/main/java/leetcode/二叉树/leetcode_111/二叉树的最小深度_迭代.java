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


import java.util.ArrayDeque;

/**
 * @Author: LCH
 * @Date: 2021/12/24 1:41 下午
 */
public class 二叉树的最小深度_迭代 {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        ArrayDeque<TreeNode> deque = new ArrayDeque();
        deque.addLast(root);
        int depth = 1;
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 1; i <= size; i++) {
                TreeNode current = deque.pollFirst();
                if (current.left == null && current.right == null) {
                    return depth;
                }
                if (current.left != null) {
                    deque.addLast(current.left);
                }
                if (current.right != null) {
                    deque.addLast(current.right);
                }
            }
            depth++;
        }
        return depth;
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
