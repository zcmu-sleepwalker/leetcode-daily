//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而
//"aec"不是）。
//
// 进阶：
//
// 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代
//码？
//
// 致谢：
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
//
//
//
// 示例 1：
//
//
//输入：s = "abc", t = "ahbgdc"
//输出：true
//
//
// 示例 2：
//
//输入：s = "axc", t = "ahbgdc"
//输出：false
//
// 提示：
//
// 0 <= s.length <= 100
// 0 <= t.length <= 10^4
// 两个字符串都只由小写字符组成。

package leetcode.数组双指针.二分查找.leetcode_392;

import java.util.ArrayList;

/**
 * @Author: LCH
 * @Date: 2021/12/6 3:30 下午
 */
public class 判断子序列 {

    public static void main(String[] args) {
        System.out.println(isSubsequence_3("acb", "ahbgdc"));
    }

    /**
     * 个人垃圾代码
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence(String s, String t) {
        char[] charsS = s.toCharArray();
        char[] charsT = t.toCharArray();
        int start = 0;
        for (int i = 0; i < charsS.length; i++) {
            char source = charsS[i];
            boolean flag = false;
            for (int j = start; j < charsT.length; j++) {
                char target = charsT[j];
                if (source == target) {
                    start = j + 1;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }


    /**
     * 双指针
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence_2(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    /**
     * 二分查找
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubsequence_3(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        // 对t字符串进行处理
        ArrayList<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);
        }
        int current = 0;
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            if (index[c] == null){
                return false;
            }
            int result = getLeftBound(index[c],current);
            // 过界了
            if (result == index[c].size()){
                return false;
            }
            current = index[c].get(result) + 1;
        }
        return true;
    }

    private static int getLeftBound(ArrayList<Integer> arrayList,int target){
        int left = 0;
        int right = arrayList.size() - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (arrayList.get(mid) == target){
                right = mid - 1;
            }else if (arrayList.get(mid) > target){
                right = mid - 1;
            }else if (arrayList.get(mid) < target){
                left = mid + 1;
            }
        }
        return left;
    }
}
