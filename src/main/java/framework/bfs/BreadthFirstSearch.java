package framework.bfs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * BFS模板
 * <p>
 * 主要适用于找最短路径，代价就是空间复杂度比DFS大很多
 * 本质就是在一副图中找到start到target的最近距离
 * <p>
 * 广义的变种：走迷宫、两个单词替换、连连看
 *
 * @Author: LCH
 * @Date: 2020/12/12 12:18 AM
 */
public class BreadthFirstSearch {

    /**
     * 节点
     */
    static class TreeNode {
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

    static class BinaryTree {
        /**
         * 求二叉树的最小高度
         *
         * @param root
         * @return
         */
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            Queue<TreeNode> queues = new LinkedList<>();
            queues.offer(root);
            int depth = 1;

            while (!queues.isEmpty()) {
                int size = queues.size();
                for (int i = 0; i < size; i++) {
                    TreeNode current = queues.poll();

                    if (current.left == null && current.right == null) {
                        return depth;
                    }
                    if (current.left != null) {
                        queues.offer(current.left);
                    }
                    if (current.right != null) {
                        queues.offer(current.right);
                    }
                }
                depth++;
            }
            return depth;
        }
    }


    /**
     * 打开转盘锁
     *
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     *
     * @param deadends
     * @param target
     * @return
     */
    static int openLock(String[] deadends, String target) {

        Queue<String> queues = new LinkedList<>();
        Set<String> deadSet = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();

        queues.offer("0000");
        visited.add("0000");
        int step = 0;

        while (!queues.isEmpty()) {

            int size = queues.size();
            for (int i = 0; i < size; i++) {

                String lockNumber = queues.poll();
                if (lockNumber.equalsIgnoreCase(target)) {
                    return step;
                }
                if (deadSet.contains(lockNumber)) {
                    continue;
                }
                for (int j = 0; j < 4; j++) {
                    String upNumber = upOne(lockNumber, j);
                    if (!visited.contains(upNumber)) {
                        queues.offer(upNumber);
                        visited.add(upNumber);
                    }
                    String downNumber = downOne(lockNumber, j);
                    if (!visited.contains(downNumber)) {
                        queues.offer(downNumber);
                        visited.add(downNumber);
                    }
                }
            }
            step++;
        }
        return step;
    }

    static String upOne(String lockNumber, int j){
        char[] str = lockNumber.toCharArray();

        if (str[j] == '9'){
            str[j] = '0';
        }else {
            str[j] += 1;
        }
        return new String(str);
    }

    static String downOne(String lockNumber, int j){
        char[] str = lockNumber.toCharArray();

        if (str[j] == '0'){
            str[j] = '9';
        }else {
            str[j] -= 1;
        }

        return new String(str);
    }

}
