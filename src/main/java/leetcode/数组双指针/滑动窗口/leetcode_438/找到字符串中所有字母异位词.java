//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
//
//
//
// 示例 1:
//
//
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
//
//
// 示例 2:
//
//
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
//
//
//
//
// 提示:
//
//
// 1 <= s.length, p.length <= 3 * 10⁴
// s 和 p 仅包含小写字母
//
// Related Topics 哈希表 字符串 滑动窗口 👍 731 👎 0

package leetcode.数组双指针.滑动窗口.leetcode_438;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: LCH
 * @Date: 2021/12/13 2:34 下午
 */
public class 找到字符串中所有字母异位词 {

    public static void main(String[] args) {
        findAnagrams_2("abaacbabc","abc").stream().forEach((Integer a)->
                System.out.println(a)
        );
    }

    /**
     * 自己写的垃圾代码
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<>();
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();
        for (char c :p.toCharArray()){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s.length()){
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    valid++;
                }else {
                    while (windows.get(c) > need.get(c)){
                        char w = s.charAt(left);
                        left++;
                        if (windows.get(w).equals(need.get(w))){
                            valid--;
                        }
                        windows.put(w,windows.get(w) - 1);
                    }
                }
            }else {
                left = right;
                valid = 0;
                windows.clear();
            }
            if (valid == need.size()){
                list.add(left);
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)){
                    if (windows.get(d).equals(need.get(d))){
                        valid--;
                    }
                    windows.put(d,windows.get(d) - 1);
                }
            }
        }
        return list;
    }

    public static List<Integer> findAnagrams_2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();
        for (char c :p.toCharArray()){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s.length()){
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            while (right - left >= p.length()){
                if (valid == need.size()){
                    list.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)){
                    if (windows.get(d).equals(need.get(d))){
                        valid--;
                    }
                    windows.put(d,windows.get(d) - 1);
                }
            }
        }
        return list;
    }
}
