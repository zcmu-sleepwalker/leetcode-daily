//根据一棵树的中序遍历与后序遍历构造二叉树。
//
// 注意:
//你可以假设树中没有重复的元素。
//
// 例如，给出
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3]
//
// 返回如下的二叉树：
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 637 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

package leetcode.二叉树.leetcode_106;


/**
 * @Author: LCH
 * @Date: 2021/12/20 2:18 下午
 */
public class 从中序与后序遍历序列构造二叉树 {

    public static void main(String[] args) {
        int[] inorder = new int[]{9,3,15,20,7};
        int[] postorder = new int[]{9,15,7,20,3};
        TreeNode node = buildTree(inorder,postorder);
        System.out.println(node);
    }

    //Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
    //Output: [3,9,20,null,null,15,7]
    // 1.post -> inorder 寻找pre[i]在inorder[]里的index
    // 2.进行取舍 post直接拿掉一个-> [9,15,7,20,]  inorder直接行切割分成左右两个节点left[9] right[15,20,7]
    // 3.递归调用
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    public static TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (postStart > postEnd){
            return null;
        }
        int postCur = postorder[postEnd];
        int inIndex = 0;
        for (int i=inStart;i<=inEnd;i++){
            if (inorder[i] == postCur){
                inIndex = i;
                break;
            }
        }

        int leftSize = inIndex - inStart;
        TreeNode root = new TreeNode(postCur);
        // inStart + leftSize 可以替换成 index - 1
        root.left = build(inorder,inStart,inStart + leftSize,postorder,postStart ,postStart + leftSize -1);
        root.right = build(inorder,inIndex + 1,inEnd,postorder,postStart + leftSize ,postEnd - 1);
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
