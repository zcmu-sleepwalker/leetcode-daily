//中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
//
// 例如，
//
// [2,3,4] 的中位数是 3
//
// [2,3] 的中位数是 (2 + 3) / 2 = 2.5
//
// 设计一个支持以下两种操作的数据结构：
//
//
// void addNum(int num) - 从数据流中添加一个整数到数据结构中。
// double findMedian() - 返回目前所有元素的中位数。
//
//
// 示例：
//
// addNum(1)
//addNum(2)
//findMedian() -> 1.5
//addNum(3)
//findMedian() -> 2
//
// 进阶:
//
//
// 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
// 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
//
// Related Topics 设计 双指针 数据流 排序 堆（优先队列） 👍 600 👎 0

package leetcode.数学算法.leetcode_295;

import java.util.PriorityQueue;

/**
 * @Author: LCH
 * @Date: 2021/12/28 10:03 上午
 */
public class 数据流的中位数 {

    public 数据流的中位数() {

    }

    public static void main(String[] args) {
        addNum(4);
        addNum(3);
        addNum(2);
        addNum(10);
        addNum(5);
        addNum(8);
        addNum(6);
        addNum(9);
        addNum(7);
        addNum(1);
        System.out.println(findMedian());
    }

    static PriorityQueue<Integer> large = new PriorityQueue<>();
    static PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> {
        return b - a;
    });
    public static void addNum(int num) {
        if (large.size() > small.size()){
            large.add(num);
            small.add(large.poll());
        }else {
            small.add(num);
            large.add(small.poll());
        }
    }

    public static double findMedian() {
        if (large.size() < small.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            return large.peek();
        }
        return (large.peek() + small.peek()) / 2.0;
    }
}
