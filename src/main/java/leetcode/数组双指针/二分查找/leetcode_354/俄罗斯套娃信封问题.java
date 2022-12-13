//给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
//
// 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
//
// 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
//
// 注意：不允许旋转信封。
//
//
// 示例 1：
//
//
//输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
//输出：3
//解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
//
// 示例 2：
//
//
//输入：envelopes = [[1,1],[1,1],[1,1]]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= envelopes.length <= 5000
// envelopes[i].length == 2
// 1 <= wi, hi <= 10⁴

package leetcode.数组双指针.二分查找.leetcode_354;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: LCH
 * @Date: 2021/12/3 2:08 下午
 */
public class 俄罗斯套娃信封问题 {

    public static void main(String[] args) {
        System.out.println(maxEnvelopes(new int[][]{{7, 3}, {3, 5}, {6, 4}, {7, 5}, {9, 9}, {6, 2}}));
    }

    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return -1;
        }
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });
        int[] nums = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            nums[i] = envelopes[i][1];
        }
        return newLengthOfLIS(nums);
    }

    private static int newLengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] top = new int[n];
        int piles = 0;// 总共有几个堆

        for (int i = 0; i < n; i++) {
            int poker = nums[i];
            int left = 0;
            int right = piles;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (top[mid] < poker) {
                    left = mid + 1;
                } else if (top[mid] >= poker) {
                    right = mid;
                }
            }

            if (left == piles) {
                piles++;
            }
            top[left] = poker;
        }
        return piles;
    }
}
