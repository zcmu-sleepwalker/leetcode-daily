package framework.binarysearch;

/**
 * 二分查找模板
 * 我们要记牢记搜索区间的概念
 *
 * @Author: LCH
 * @Date: 2020/12/18 2:14 PM
 */
public class BinarySearch {


    /**
     * 此算法缺陷：当数组里有大量重复元素的时候想得到最左或者最右元素的时候就没办法了
     *
     * @param nums
     * @param target
     * @return
     */
    public int binarySearch_01(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 这里用的是 <= 而不是 < ，原因就在于我们right初始化的时候是length - 1，而不是length。
        // 前者是[left,right] ，而后者是[left,right)
        // left <= right 的终止条件是 left = right + 1
        // left < right 的终止条件是 left = right
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 这里为什么是mid - 1 而不是mid
                // 我们通过前面区间的概念可知[left,right]
                // 此时mid != right 我们就需要将mid排除
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return 0;
    }

    /**
     * 寻找左侧边界的二分搜索
     */
    static class leftBoundSearch{

        /**
         * 也是为了学习另一种左闭右开的写法
         *
         * @param nums
         * @param target
         * @return
         */
        public int leftBound_01(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length; // 注意这里是length，而不是length - 1

            while (left < right) { // 注意这里 < 而不是 <=
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    // 这里不需要返回 而是减小搜索区间
                    right = mid;
                } else if (nums[mid] > target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    // 注意这里是mid 而不是mid + 1 ，因为我们区间是[left,right)
                    // 所以其实mid复制给right后是不包含mid的
                    right = mid;
                }
            }

            if (left > nums.length || target != nums[left]){
                return -1;
            }
            // 因为while里面终止条件是left = right，所以这里返回left还是right都是一样的。
            return left;
        }

        public int leftBound_02(int[] nums, int target){
            if (nums.length == 0){
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;

            while (left <= right){
                int mid = left + (right - left) / 2;
                if (nums[mid] == target){
                    right = mid;
                }else if (nums[mid] < target){
                    left = mid + 1;
                }else if (nums[mid] > target){
                    right = mid - 1;
                }
            }

            // 由于while退出的条件是left = right + 1
            // 所以当target大于全部的元素的时候，left会越界
            if (left > nums.length || target != nums[left]){
                return -1;
            }
            return left;
        }
    }

    /**
     * 寻找右侧边界的二分搜索
     */
    static class rightBoundSearch{

        /**
         * 也是为了学习另一种左闭右开的写法
         *
         * @param nums
         * @param target
         * @return
         */
        public int rightBound_01(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            int left = 0;
            int right = nums.length; // 注意这里是length，而不是length - 1

            while (left < right) { // 注意这里 < 而不是 <=
                int mid = left + (right - left) / 2;
                if (nums[mid] == target) {
                    // 这里不需要返回 而是减小搜索区间
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    left = mid + 1;
                } else if (nums[mid] < target) {
                    // 注意这里是mid 而不是mid + 1 ，因为我们区间是[left,right)
                    // 所以其实mid复制给right后是不包含mid的
                    right = mid;
                }
            }

            // 因为left区间是[0,nums.length]
            if (left == 0 || nums[left] != target){
                return -1;
            }
            // 因为while里面终止条件是left = right，所以这里返回left还是right都是一样的。
            // 为什么要减一？
            // 因为当nums[mid] == target时，我们减小搜索区间left = mid + 1
            // 所以while结束的时候nums[left]不一定等于target
            return left - 1;
        }

        public int rightBound_02(int[] nums, int target){
            if (nums.length == 0){
                return -1;
            }
            int left = 0;
            int right = nums.length - 1;

            while (left <= right){
                int mid = left + (right - left) / 2;
                if (nums[mid] == target){
                    left = mid + 1;
                }else if (nums[mid] < target){
                    left = mid + 1;
                }else if (nums[mid] > target){
                    right = mid - 1;
                }
            }

            // 由于while退出的条件是left = right + 1
            // 所以当target小于全部的元素的时候，right会越界
            if (right < 0 || target != nums[right]){
                return -1;
            }
            return right;
        }
    }
}
