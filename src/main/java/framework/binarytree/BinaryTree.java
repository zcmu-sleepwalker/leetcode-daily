package framework.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树模板
 * @Author: LCH
 * @Date: 2021/12/27 5:16 下午
 */
public class BinaryTree {

    /**
     * 前序遍历
     * @param root
     */
    void traverse_pre(TreeNode root) {
        if (root == null) return;

        // 前序遍历的代码

        traverse_pre(root.left);
        traverse_pre(root.right);
    }


    /**
     * 后续遍历
     * 如果当前节点要做的事情需要通过左右子树的计算结果推导出来，就要用到后序遍历。
     * @param root
     */
    void traverse_post(TreeNode root) {
        if (root == null) return;
        traverse_post(root.left);
        traverse_post(root.right);

        // 后序遍历的代码
    }

    /**
     * 层级遍历
     * @param root
     */
    void traverse(TreeNode root) {
        if (root == null) return;
        // 初始化队列，将 root 加入队列
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode cur = q.poll();

            /* 层级遍历代码位置 */
            System.out.println(root.val);
            /*****************/

            if (cur.left != null) {
                q.offer(cur.left);
            }

            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }

    public class TreeNode {
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
