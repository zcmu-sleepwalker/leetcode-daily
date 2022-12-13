package leetcode.数组双指针.二分查找.leetcode_34;

import java.util.Arrays;

/**
 * @Author: LCH
 * @Date: 2021/12/3 3:35 下午
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {


    public static void main(String[] args) {
        int[] nums = new int[]{5,7,7,8,8,10};
        int[] result = searchRange(nums,8);
        System.out.println(Arrays.toString(result));
    }

    public static int[] searchRange(int[] nums, int target) {
        return new int[]{leftBound(nums,target),rightBound(nums,target)};
    }

    private static int leftBound(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) /2;
            if (nums[mid] == target){
                right = mid -1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        if (left >= nums.length || nums[left] != target){
            return -1;
        }
        return left;
    }

    private static int rightBound(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) /2;
            if (nums[mid] == target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid -1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        if (left < 0 || nums[right] != target){
            return -1;
        }
        return right;
    }


    /**
     * 个人垃圾代码===========================================>
     */
    public static int[] searchRange2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        if (left == right && nums[0] == target){
            return new int[]{0,0};
        }
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] == target){
                int leftmid = mid;
                int rightmid = mid;
                while (leftmid-1 != -1 && nums[leftmid-1] == target){
                    leftmid--;
                }
                while (rightmid+1 != nums.length && nums[rightmid+1] == target){
                    rightmid++;
                }
                return new int[]{leftmid,rightmid};
            }else if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] < target){
                left = mid + 1;
            }
        }
        return new int[]{-1,-1};
    }
}
