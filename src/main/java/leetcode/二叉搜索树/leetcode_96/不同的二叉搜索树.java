//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 19
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1456 👎 0
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
//
//
//
// 示例 1：
//
//
//输入：n = 3
//输出：5
//
//
// 示例 2：
//
//
//输入：n = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= n <= 19
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1456 👎 0

package leetcode.二叉搜索树.leetcode_96;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: LCH
 * @Date: 2021/12/27 5:07 下午
 */
public class 不同的二叉搜索树 {

    Map<String,Integer> map = new HashMap<>();
    public int numTrees(int n) {
        return build(1,n);
    }

    public int build(int start, int end) {
        if (map.get(start + "-" + end) != null){
            return map.get(start + "-" + end);
        }
        if (start > end){
            return 1;
        }
        int res = 0;
        for (int i = start; i <= end; i++) {
            int leftNodes = build(start,i-1);
            int rightNodes = build(i+1,end);
            res += leftNodes * rightNodes;
        }
        map.put(start + "-" + end,res);
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
