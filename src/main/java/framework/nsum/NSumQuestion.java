package framework.nsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * N个数之和问题
 * 两数之和：使用双指针，判断和sum的大小，从两端相向而行。
 * N个数之和：使用递归
 *
 * @Author: LCH
 * @Date: 2021/3/29 2:38 PM
 */
public class NSumQuestion {


    /**
     * 两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;
        while (low < high){
            int sum = nums[low] + nums[high];
            if (sum == target){
                return new int[]{low,high};
            }else if (sum > target){
                high--;
            }else {
                low++;
            }
        }
        return new int[]{};
    }

    public List<List<Integer>> twoSum_02(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 数组必须要有序的
        Arrays.sort(nums);
        int low = 0;
        int high = nums.length - 1;

        while (low < high){
            int left = nums[low];
            int right = nums[high];
            int sum = left + right;
            if (sum == target){
                List<Integer> list = new ArrayList<Integer>();
                list.add(left);
                list.add(right);
                result.add(list);
                // 如果存在重复的数字就需要全部跳过
                while (low < high && left == nums[low]){
                    low++;
                }
                while (low < high && right == nums[high]){
                    high--;
                }
            }else if (sum > target){
                while (low < high && right == nums[high]){
                    high--;
                }
            }else {
                while (low < high && left == nums[low]){
                    low++;
                }
            }
        }
        return result;
    }

    /**
     * 三数之和
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums, Integer target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            List<List<Integer>> temp = twoSum_utils(nums,target - nums[i],i + 1);
            for (int j = 0; j < temp.size(); j++) {
                List<Integer> list = temp.get(j);
                list.add(nums[i]);
                result.add(list);
            }
            while (i < nums.length -1 && nums[i] == nums[i+1]){
                i++;
            }
        }

        return result;
    }

    public static List<List<Integer>> twoSum_utils(int[] nums, int target,int start) {
        List<List<Integer>> result = new ArrayList<>();
        // 数组必须要有序的
        Arrays.sort(nums);
        int low = start;
        int high = nums.length - 1;

        while (low < high){
            int left = nums[low];
            int right = nums[high];
            int sum = left + right;
            if (sum == target){
                List<Integer> list = new ArrayList<Integer>();
                list.add(left);
                list.add(right);
                result.add(list);
                // 如果存在重复的数字就需要全部跳过
                while (low < high && left == nums[low]){
                    low++;
                }
                while (low < high && right == nums[high]){
                    high--;
                }
            }else if (sum > target){
                while (low < high && right == nums[high]){
                    high--;
                }
            }else {
                while (low < high && left == nums[low]){
                    low++;
                }
            }
        }
        return result;
    }

    /**
     * nSum问题合集
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> nSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSumUtils(nums,4,target,0);
    }

    public static List<List<Integer>> nSumUtils(int[] nums, int n, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        if (n < 2 || nums.length < n){
            return result;
        }

        if (n == 2){
            int low = start;
            int high = nums.length - 1;
            while (low < high){
                int left = nums[low];
                int right = nums[high];
                int sum = left + right;
                if (sum == target){
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(left);
                    list.add(right);
                    result.add(list);
                    // 如果存在重复的数字就需要全部跳过
                    while (low < high && left == nums[low]){
                        low++;
                    }
                    while (low < high && right == nums[high]){
                        high--;
                    }
                }else if (sum > target){
                    while (low < high && right == nums[high]){
                        high--;
                    }
                }else {
                    while (low < high && left == nums[low]){
                        low++;
                    }
                }
            }
        }else {
            for (int i = start; i < nums.length; i++) {
                List<List<Integer>> temp = nSumUtils(nums,n - 1,target - nums[i],i + 1);
                for (List<Integer> list : temp) {
                    list.add(nums[i]);
                    result.add(list);
                }
                while (i < nums.length -1 && nums[i] == nums[i+1]){
                    i++;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] aa = new int[]{-1,0,1,2,-1,-4};
        int[] aaaa = new int[]{2,1,0,-1};
        List<List<Integer>> list = nSum(aaaa,2);
        System.out.println(list.toArray());
    }
}
