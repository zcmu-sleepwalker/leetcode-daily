package framework.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 滑动窗口模板
 * @Author: LCH
 * @Date: 2020/12/18 2:20 PM
 */
public class SlidingWindow {


    /**
     * 参考模板
     * @param s
     * @param t
     */
    public void slidingWindowsFrameWork(String s,String t){
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();

        char[] one = s.toCharArray();
        char[] two = t.toCharArray();

        for (char c : two){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < one.length){
            char c = one[right];
            right ++;
            // TODO 进行数据的更新操作

            System.out.println("left:" + left + ",right:" + right);

            // 判断左窗口是否需要收缩
            boolean ifNeedsShrink = true;
            while (ifNeedsShrink){
                char d = one[left];
                left ++;
                // TODO
            }
        }
    }


    /**
     * 最小覆盖子串
     *
     * s1 = "AKDSJKNCSLOWM" s2 = "DKN" return "DSJKN"
     *
     * @param s
     * @param t
     * @return
     */
    public String minWindows(String s, String t){
        HashMap<Character,Integer> need = new HashMap<>();
        HashMap<Character,Integer> windows = new HashMap<>();

        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        for (char c : target){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        // 记录覆盖串的起始索引及长度
        int start = 0;
        int len = Integer.MAX_VALUE;

        while (right < source.length){
            // 右移
            char c = source[right];
            right ++;
            // 进行窗口内数据的更新
            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            while (valid == need.size()){
                // 更新最小覆盖子串
                if (right - left < len){
                    start = left;
                    len = right - left;
                }
                // 左移
                char d = source[left];
                left++;
                // 进行窗口内数据的更新
                if (need.containsKey(d)){
                    if (windows.get(d).equals(need.get(d))){
                        valid--;
                    }
                    windows.put(d,windows.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE?"":s.substring(start,start + len);
    }


    /**
     * 字符串排列
     * s1 = "ab" s2 = "wafasgfba"  return true
     * s1 = "ab" s2 = "jkjsapdbskajksb"     return false
     * @param s
     * @param t
     * @return
     */
    public boolean checkInclusion(String s, String t){
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();

        char[] source = t.toCharArray();
        char[] target = s.toCharArray();
        for (char c : target){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < source.length){
            char c = source[right];
            right++;
            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 如果是排列的话 肯定要保证长度是一样的
            while ((right - left) >= s.length()){//???????????????need.size() 还是 s1.length
                // 如果长度一样 valid又相等 就表示已经找到了
                if (valid == need.size()){
                    return true;
                }
                char d = source[left];
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


    /**
     * 找所有字母异位词
     * s1 = "ab" s2 = "wabfasgfba"  return [1,8]
     * @param s
     * @param t
     * @return
     */
    public List<Integer> findAnagrams(String s, String t){
        List<Integer> result = new ArrayList<>();
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();

        char[] source = t.toCharArray();
        char[] target = s.toCharArray();
        for (char c : target){
            need.put(c,need.getOrDefault(c,0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;

        while (right < source.length){
            char c = source[right];
            right++;
            if (need.containsKey(c)){
                windows.put(c,windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c))){
                    valid++;
                }
            }
            // 如果是排列的话 肯定要保证长度是一样的
            while ((right - left) >= s.length()){//???????????????need.size() 还是 s1.length
                // 如果长度一样 valid又相等 就表示已经找到了
                if (valid == need.size()){
                    result.add(left);
                }
                char d = source[left];
                left++;
                if (need.containsKey(d)){
                    if (windows.get(d).equals(need.get(d))){
                        valid--;
                    }
                    windows.put(d,windows.get(d) - 1);
                }
            }
        }
        return result;
    }


    /**
     * 最长无重复子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubString(String s){
        int res = 0;
        HashMap<Character, Integer> windows = new HashMap<>();

        char[] source = s.toCharArray();

        int left = 0;
        int right = 0;

        while (right < source.length){
            char c = source[right];
            right++;
            windows.put(c,windows.getOrDefault(c,0) + 1);

            while (windows.get(c) > 1){
                char d = source[left];
                left++;
                windows.put(d,windows.get(d) - 1);
            }
            res = Integer.max(right - left,res);
        }
        return res;
    }

}
