//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
//
// 换句话说，s1 的排列之一是 s2 的 子串 。
//
//
//
// 示例 1：
//
//
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
//
//
// 示例 2：
//
//
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= s1.length, s2.length <= 10⁴
// s1 和 s2 仅包含小写字母
//
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 512 👎 0

package leetcode.数组双指针.滑动窗口.leetcode_567;

import java.util.HashMap;

/**
 * @Author: LCH
 * @Date: 2021/12/17 3:20 下午
 */
public class 字符串的排列 {

    public static void main(String[] args) {
        System.out.println(checkInclusion("hello","ooolleooolehl"));
        System.out.println(checkInclusion("qwer","qqqwweerrtttrereqwqwe"));
    }


    public static boolean checkInclusion(String s1, String s2) {
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();
        for (char c : s1.toCharArray()){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < s2.length()){
            char c = s2.charAt(right);
            right++;

            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }else {
                left = right;
                valid = 0;
                windows.clear();
            }
            if (right == 12 || left == 7) {
                System.out.println();
            }

            while ((right - left) >= s1.length()){
                if (valid == need.size()){
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)){
                    if (windows.get(d).equals(need.get(d))){
                        valid--;
                    }
                    windows.put(d,windows.get(d) - 1);
                }
            }
        }
        return false;
    }
}
