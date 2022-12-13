//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
//
//
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：[[1]]
//
//
//
//
// 提示：
//
//
// 1 <= n <= 8
//
//
//
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树 👍 1081 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉搜索树.leetcode_95;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LCH
 * @Date: 2021/12/27 5:07 下午
 */
public class 不同的二叉搜索树2 {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return build(1, n);
    }

    public List<TreeNode> build(int start, int end) {
        List<TreeNode> list = new ArrayList<>();

        if (start > end){
            list.add(null);
            return list;
        }

        for (int i = start; i <= end; i++) {
            List<TreeNode> leftNodes = build(start,i-1);
            List<TreeNode> rightNodes = build(i+1,end);
            for (TreeNode left : leftNodes){
                for (TreeNode right : rightNodes){
                    TreeNode node = new TreeNode(i);
                    node.left = left;
                    node.right = right;
                    list.add(node);
                }
            }
        }
        return list;
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
