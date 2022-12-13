//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
//
//
//
// 注意：
//
//
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。
//
//
//
//
// 示例 1：
//
//
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//
//
// 示例 2：
//
//
//输入：s = "a", t = "a"
//输出："a"
//
//
// 示例 3:
//
//
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。
//
//
//
// 提示：
//
//
// 1 <= s.length, t.length <= 10⁵
// s 和 t 由英文字母组成
//
//
//
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 👍 1483 👎 0

package leetcode.数组双指针.滑动窗口.leetcode_76;

import java.util.HashMap;

/**
 * @Author: LCH
 * @Date: 2021/12/11 10:47 下午
 */
public class 最小覆盖子串 {

    public static void main(String[] args) {
        System.out.println("ADOBECODEBANCAB".substring(1,2));// 左闭右开

    }

    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int vaild = 0;
        int start = 0;
        int end = 0;
        int len = Integer.MAX_VALUE;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    vaild++;
                }
            }
            System.out.println("left:" + left + ",right:" + right);
            while (vaild == need.size()){
                if (right - left < len){
                    start = left;
                    end = right;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)){
                    if (windows.get(d).equals(need.get(d))){
                        vaild--;
                    }
                    windows.put(d,windows.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE?"":s.substring(start,end);
    }
}
