//给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
//
// 例如，
//
//
//给定二叉搜索树:
//
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//和值: 2
//
//
// 你应该返回如下子树:
//
//
//      2
//     / \
//    1   3
//
//
// 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
// Related Topics 树 二叉搜索树 二叉树 👍 231 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
package leetcode.二叉搜索树.leetcode_700;

import com.lch.algorithm.leetcode.二叉搜索树.leetcode_450.删除二叉搜索树中的节点;

/**
 * @Author: LCH
 * @Date: 2022/1/21 10:50 上午
 */
public class 二叉搜索树中的搜索 {

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val > val){
            return searchBST(root.left,val);
        }
        if (root.val < val){
            return searchBST(root.right,val);
        }
        return root;
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
