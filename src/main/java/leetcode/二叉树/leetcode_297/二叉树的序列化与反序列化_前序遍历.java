//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [1]
//输出：[1]
//
//
// 示例 4：
//
//
//输入：root = [1,2]
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 10⁴] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 716 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉树.leetcode_297;

import java.util.LinkedList;

/**
 * @Author: LCH
 * @Date: 2021/12/23 3:25 下午
 */
public class 二叉树的序列化与反序列化_前序遍历 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(9);
        System.out.println(serialize(root));
        System.out.println(deserialize(serialize(root)));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialized(root,sb);
        return sb.toString();
    }

    private static void serialized(TreeNode treeNode, StringBuilder sb){
        if (treeNode == null){
            sb.append("#,");
            return;
        }else {
            sb.append(treeNode.val + ",");
        }

        serialized(treeNode.left,sb);
        serialized(treeNode.right,sb);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        LinkedList<String> linkedList = new LinkedList<>();
        for (String m : data.split(",")){
            linkedList.offer(m);
        }
        TreeNode node = deserialized(linkedList);
        return node;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialized(LinkedList<String> list) {
        String val = list.pollFirst();
        if ("#".equals(val)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserialized(list);
        root.right = deserialized(list);

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
