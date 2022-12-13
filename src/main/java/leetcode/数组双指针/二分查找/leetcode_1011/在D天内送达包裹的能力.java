//传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
//
// 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
//
// 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
//
//
//
// 示例 1：
//
//
//输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
//输出：15
//解释：
//船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
//第 1 天：1, 2, 3, 4, 5
//第 2 天：6, 7
//第 3 天：8
//第 4 天：9
//第 5 天：10
//
//请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
//0) 是不允许的。
//
//
// 示例 2：
//
//
//输入：weights = [3,2,2,4,1,4], D = 3
//输出：6
//解释：
//船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
//第 1 天：3, 2
//第 2 天：2, 4
//第 3 天：1, 4
//
//
// 示例 3：
//
//
//输入：weights = [1,2,3,1,1], D = 4
//输出：3
//解释：
//第 1 天：1
//第 2 天：2
//第 3 天：3
//第 4 天：1, 1
//
//
//
//
// 提示：
//
//
// 1 <= D <= weights.length <= 5 * 10⁴
// 1 <= weights[i] <= 500
//
// Related Topics 贪心 数组 二分查找 👍 410 👎 0

package leetcode.数组双指针.二分查找.leetcode_1011;

/**
 * @Author: LCH
 * @Date: 2021/12/11 5:21 下午
 */
public class 在D天内送达包裹的能力 {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 5, 10, 8, 4, 2, 4, 5, 4, 5, 3, 3, 5, 6, 7, 8};
//        int[] nums = new int[]{3,6,7,11};
//        int[] nums = new int[]{312884470};
        System.out.println(shipWithinDays(nums, 20));
    }


    public static int shipWithinDays(int[] wights, int days) {
        int min = getMin(wights);
        int max = getMax(wights);

        while (min <= max) {
            int mid = min + (max - min) / 2;
            int n = getHours(wights,mid);
            if (n == days){
                max = mid - 1;
            }else if (n > days){
                min = mid + 1;
            }else if (n < days){
                max = mid - 1;
            }
        }
        return min;
    }

    private static int getHours(int[] nums, int speed){
        int sum = 0;
        int count = 1;
        for (int num : nums) {
            if (sum + num > speed) {
                count++;
                sum = num;
            } else {
                sum += num;
            }
        }
        return count;
    }

    private static int getMax(int[] wights){
        int sum = 0;
        for (int wight : wights){
            sum += wight;
        }
        return sum;
    }
    private static int getMin(int[] wights){
        int min = 0;
        for (int wight : wights){
            min = Math.max(wight,min);
        }
        return min;
    }

}
