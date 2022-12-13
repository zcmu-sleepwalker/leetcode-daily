//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 10⁴
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 字符串 滑动窗口 👍 6560 👎 0

package leetcode.数组双指针.滑动窗口.leetcode_03;

import java.util.HashMap;

/**
 * @Author: LCH
 * @Date: 2021/12/11 5:40 下午
 */
public class 无重复字符串的最长子串 {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring_3("abcfdsjkfkjlfdldimvdsmlafjdlsijldakldsjlfjkdfsnvkljdsfjkahiogjofbjfadiogankjdnlsafsaabcbb"));
    }

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int len = s.length();
        int left = 0;
        int right = 0;
        int resut = 0;
        while (right < len){
            char rightC = s.charAt(right);
            char leftC = s.charAt(left);
            if (map.getOrDefault(rightC,0) == 0){
                map.put(rightC,1);
                right++;
            }else {
                map.put(leftC,map.getOrDefault(leftC,0) - 1);
                left++;
            }
            resut = Math.max(right - left,resut);
        }

        return resut;
    }

    public static int lengthOfLongestSubstring_2(String s) {
        int[] window = new int[256];
        int left = 0;
        int right = 0;
        int resut = 0;
        while (right < s.length()){
            char rightC = s.charAt(right);
            char leftC = s.charAt(left);
            if (window[rightC] == 0){
                window[rightC]++;
                right++;
            }else {
                window[leftC]--;
                left++;
            }
            resut = Math.max(right - left,resut);
        }
        return resut;
    }

    public static int lengthOfLongestSubstring_3(String s) {
        int[] window = new int[256];
        int left = 0;
        int rifht = 0;
        int result = 0;
        while (rifht < s.length()){
            char c = s.charAt(rifht);
            window[c]++;
            rifht++;
            while (window[c] > 1){
                char lc = s.charAt(left);
                window[lc]--;
                left++;
            }
            result = Math.max(result,rifht-left);
        }
        return result;
    }
}
