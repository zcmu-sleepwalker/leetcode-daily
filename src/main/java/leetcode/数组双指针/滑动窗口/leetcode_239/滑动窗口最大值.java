//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
//你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。
//
// 返回滑动窗口中的最大值。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
// 示例 2：
//
//
//输入：nums = [1], k = 1
//输出：[1]
//
//
// 示例 3：
//
//
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
//
//
// 示例 4：
//
//
//输入：nums = [9,11], k = 2
//输出：[11]
//
//
// 示例 5：
//
//
//输入：nums = [4,-2], k = 2
//输出：[4]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 10⁵
// -10⁴ <= nums[i] <= 10⁴
// 1 <= k <= nums.length
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1302 👎 0

package leetcode.数组双指针.滑动窗口.leetcode_239;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: LCH
 * @Date: 2021/12/17 4:00 下午
 */
public class 滑动窗口最大值 {

    public static void main(String[] args) {

    }

    /**
     * 自己写的垃圾代码--超时过不了
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int left = 0;
        int right = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int count = 0;
        List<Integer> list = new ArrayList<>();
        while (right < nums.length){
            int c = nums[right];
            if (count < k){
                priorityQueue.add(c);
                count++;
                right++;
                if (right == nums.length){
                    int res = priorityQueue.peek();
                    list.add(res);
                }
                continue;
            }

            int res = priorityQueue.peek();
            list.add(res);

            int d = nums[left];
            priorityQueue.remove(d);
            count--;
            left++;
        }
        int[] result = new int[list.size()];
        for (int i = 0;i<list.size();i++){
            result[i] = list.get(i);
        }
        return result;
    }

    /* 单调队列的实现 */
    class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();
        public void push(int n) {
            // 将⼩于 n 的元素全部删除
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            // 然后将 n 加⼊尾部
            q.addLast(n);
        }
        public int max() {
            return q.getFirst();
        }
        public void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }
    /* 解题函数的实现 */
    public int[] maxSlidingWindow_2(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先填满窗⼝的前 k - 1
                window.push(nums[i]);
            } else {
                // 窗⼝向前滑动，加⼊新数字
                window.push(nums[i]);
                // 记录当前窗⼝的最⼤值
                res.add(window.max());
                // 移出旧数字
                window.pop(nums[i - k + 1]);
            }
        }
// 需要转成 int[] 数组再返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

}
