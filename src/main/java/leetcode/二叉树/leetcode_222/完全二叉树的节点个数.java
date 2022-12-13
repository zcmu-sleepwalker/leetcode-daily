//给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
//
// 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层
//为第 h 层，则该层包含 1~ 2ʰ 个节点。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,4,5,6]
//输出：6
//
//
// 示例 2：
//
//
//输入：root = []
//输出：0
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：1
//
//
//
//
// 提示：
//
//
// 树中节点的数目范围是[0, 5 * 10⁴]
// 0 <= Node.val <= 5 * 10⁴
// 题目数据保证输入的树是 完全二叉树
//
//
//
//
// 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
// Related Topics 树 深度优先搜索 二分查找 二叉树 👍 592 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉树.leetcode_222;

import java.util.LinkedList;

/**
 * @Author: LCH
 * @Date: 2021/12/25 3:17 下午
 */
public class 完全二叉树的节点个数 {

    /**
     * 精巧的解法
     * @param root
     * @return
     */
    public int countNodes_2(TreeNode root) {
        if (root == null) return 0;
        int lh = 1;
        int rh = 1;
        TreeNode l = root;
        TreeNode r = root;
        while (l.left != null){
            l = l.left;
            lh++;
        }
        while (l.right != null){
            r = r.right;
            rh++;
        }
        if (lh == rh){
            return (int) Math.pow(2,lh) - 1;
        }
        return 1 + countNodes_2(root.left) + countNodes_2(root.right);
    }

    /**
     * 个人解法
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i=0;i<size;i++){
                TreeNode cur = queue.poll();
                count++;
                if (cur.left != null){
                    queue.offer(cur.left);
                }
                if (cur.right != null){
                    queue.offer(cur.right);
                }
            }
        }
        return count;
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
