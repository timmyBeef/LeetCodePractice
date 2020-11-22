package leetcode.string;

/*
    time: O(n^2)
    space: O(1)

    spread from center

    helper(s, i , i); // for odd case
    helper(s, i, i + 1); // for even case

    for examle:
    b a b a d, spread from middle b , is the helper(s, 2 , 2) case,
    so test each conditions and find the longest palindromic substring
 */
public class LongestPalindromicSubstring {
    String res = "";
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return res;

        for (int i = 0; i < s.length(); i++) {
            helper(s, i , i);
            helper(s, i, i + 1);
        }
        return res;
    }

    private void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length()) {
            res = cur;
        }
    }
}
