package framework.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法模板
 * <p>
 * 解决回溯问题，其实就是一个决策树遍历的过程：
 * 1.路径：指的是已经做出的选择。
 * 2.选择列表：当前可以做出的选择。
 * 3.结束条件：已经达到决策树的底层，无法继续做出选择。
 * <p>
 * backtrack开头写出满足结束条件
 * 核心思想就是一个for循环，递归之前做出选择，递归之后撤销选择。
 * for 选择 in 选择列表:
 * # 做选择
 * 将该选择从选择列表移除
 * 路径.add(选择)
 * backtrack(路径, 选择列表)
 * # 撤销选择
 * 路径.remove(选择)
 * 将该选择再加入选择列表
 *
 * @Author: LCH
 * @Date: 2020/12/7 9:58 PM
 */
public class BackTracking {

    /**
     * 全排列问题
     */
    static class PermuteAll {

        static List<List<Integer>> result = new ArrayList<>();

        public static List<List<Integer>> permute1(int[] nums) {
            // 记录「路径」
            LinkedList<Integer> track = new LinkedList<>();
            // 「路径」中的元素会被标记为 true，避免重复使用
            boolean[] used = new boolean[nums.length];

            backtrack(nums, track, used);

            return result;
        }

        // 路径：记录在 track 中
        // 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
        // 结束条件：nums 中的元素全都在 track 中出现
        static void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {

            if (track.size() == nums.length) {
                result.add(new LinkedList<>(track));
//                result.add(track);
                return;
            }

            for (int i = 0; i < nums.length; i++) {
                // 排除不合法的选择
                if (used[i]) {// 之前使用track.contains(nums[i])进行判断时间复杂度是O(n)，现在是O(1)
                    // nums[i] 已经在 track 中，跳过
                    continue;
                }
                // 做选择
                track.add(nums[i]);
                used[i] = true;
                // 进入下一层决策树
                backtrack(nums, track,used);
                used[i] = false;
                // 取消选择
                track.removeLast();
            }

        }

        public static void main(String[] args) {
            System.out.println(PermuteAll.permute1(new int[]{1, 2, 3}));
        }

    }


    /**
     * N皇后问题
     */
    static class NQueues {

        static List<String[][]> result = new ArrayList<>();
        static List<List<List<String>>> res = new ArrayList<>();

        public static List<List<String>> solveNQueens(int n) {

            String[][] queens = new String[n][n];

            for (int i = 0; i < queens.length; i++) {
                for (int j = 0; j < queens[i].length; j++) {
                    queens[i][j] = ".";
                }
            }

            backtrack(queens, 0);

            System.out.println(res.size());
            return null;
        }

        public static void backtrack(String[][] queens, int row) {
            if (queens.length == row) {
                //转成list
                List<List<String>> tmp = new ArrayList<>();
                for (int j = 0; j < queens.length; j++) {
                    List<String> tmp1 = new ArrayList<>();
                    for (int k = 0; k < queens[j].length; k++) {
                        tmp1.add(queens[j][k]);
                    }
                    tmp.add(tmp1);
                }
                res.add(tmp);
                return;
            }
            Integer size = queens[row].length;
            for (int col = 0; col < size; col++) {

                if (isValid(queens, row, col)) {
                    continue;
                }
                queens[row][col] = "Q";
                backtrack(queens, row + 1);
                queens[row][col] = ".";
            }
        }

        private static boolean isValid(String[][] queens, int row, int col) {

            for (int i = 0; i < row; i++) {
                if (queens[i][col] == "Q") {
                    return true;
                }
            }

            // 向左上移动，row要减少，col也要减少
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (queens[i][j] == "Q") {
                    return true;
                }
            }

            // 向右上移动，row要减少，但是col要增加
            for (int i = row - 1, j = col + 1; i >= 0 && j < queens.length; i--, j++) {
                if (queens[i][j] == "Q") {
                    return true;
                }
            }

            return false;
        }

        public static void main(String[] args) {
            System.out.println(NQueues.solveNQueens(13));
        }
    }

}
