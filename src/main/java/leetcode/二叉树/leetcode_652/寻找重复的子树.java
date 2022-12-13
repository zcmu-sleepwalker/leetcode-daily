//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。
//
// 示例 1：
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
//
//
// 下面是两个重复的子树：
//
//       2
//     /
//    4
//
//
// 和
//
//     4
//
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 349 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉树.leetcode_652;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: LCH
 * @Date: 2021/12/25 5:38 下午
 */
public class 寻找重复的子树 {

    HashMap<String, Integer> map = new HashMap<>();
    HashSet<String> set = new HashSet<>();
    private String encode(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String leftStr = encode(root.left);
        String rightStr = encode(root.right);
        String key = leftStr + "," + rightStr + "," + root.val;
        int flag = map.getOrDefault(key, 0);
        if (flag == 1){
            set.add(key);
        }
        map.put(key, map.getOrDefault(key, 0) + 1);

        return key;
    }

    private TreeNode decode(LinkedList<String> mlist){
        String cur = mlist.pollLast();
        if ("#".equals(cur) || cur == null){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.right = decode(mlist);
        root.left = decode(mlist);
        return root;
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        encode(root);
        List<TreeNode> list = new ArrayList<>();
        for (String m : set){
            LinkedList<String> linkedList = new LinkedList<>();
            for (String str : m.split(",")){
                linkedList.add(str);
            }
            TreeNode treeNode = decode(linkedList);
            list.add(treeNode);
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
