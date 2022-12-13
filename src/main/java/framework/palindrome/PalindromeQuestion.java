package framework.palindrome;

/**
 * 字符串回文问题
 * 如果输入相同的 l 和 r，就相当于寻找长度为奇数的回文串，如果输入相邻的 l 和 r，则相当于寻找长度为偶数的回文串。
 * 解法的大致思路就是：
 *  for 0 <= i < len(s):
 *     找到以 s[i] 为中心的回文串
 *     找到以 s[i] 和 s[i+1] 为中心的回文串
 *     更新答案
 *
 * @Author: LCH
 * @Date: 2021/3/31 3:04 PM
 */
public class PalindromeQuestion {


    /**
     * 最长回文串
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String a1 = palindromeUtils(s, i, i);
            String a2 = palindromeUtils(s, i, i + 1);
            res = a1.length() > res.length() ? a1 : res;
            res = a2.length() > res.length() ? a2 : res;
        }
        return res;
    }

    private String palindromeUtils(String str, int l, int r) {
        //防止越界
        while (l > 0 && r < str.length() && str.charAt(l) == str.charAt(l)) {
            l--;
            r++;
        }
        // 为什么要+1和-1？因为当前的位置是需要判断的 如果不符合标识当前l和r并不是回文 所以需要减掉
        return str.substring(l + 1, r);
    }


    // 单链表
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 单链表判断回文
     * @param head
     * @return
     */
    ListNode left;
    public boolean longestPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    private boolean traverse(ListNode right){
        if (right == null){
            return true;
        }
        boolean res = traverse(right.next);
        res = res && (left.val == right.val);

        left = left.next;
        return res;
    }

    /* 倒序打印单链表中的元素值 */
    private void traverseTest(ListNode head) {
        if (head == null) {
            return;
        }
        traverseTest(head.next);
        // 后序遍历代码
        System.out.println(head.val);
    }
}
