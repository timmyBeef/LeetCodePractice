package leetcode.slidingwindows;

import java.util.*;

//https://leetcode.com/problems/minimum-window-substring/discuss/26811/Share-my-neat-java-solution

/*
     sliding windows

     step 1  build map: the string which you want to find
             int counter = map.size();// length 0f the string which you want to find
             left = 0, right =0

     step 2: right point "keep moving" to the match condition,
             if match map key, do counter--

     step 3: when counter == 0, it means match condition(all char hit the map),
             so do the left point++,
             to find next window which match condition.

             before left++, we should do the counter++, if the abandom left match the map key
             to recover the counter

             // save / update(min/max) the result if find a target
             // result collections or result int value
             and update the result info

                 if (end - begin < len) {
                    len = end - begin;
                    head = begin;
                }
             left++

      step 4  return result (return s.substring(head, head + len))


      time:  O(S+T) , S search string length(traversal data), T pattern string lenfth(build the map time)
      space: O(S+T)
 */

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        if (t.length() > s.length()) return "";
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int counter = map.size();

        int begin = 0, end = 0;
        int head = 0;
        int len = Integer.MAX_VALUE;

        while (end < s.length()) { //!

            // move right
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1); //!!
                if (map.get(c) == 0) counter--; //!!
            }
            end++;

            while (counter == 0) { // 代表 match 完了

                // move letf
                // 所以左邊要開始繼續右移, 右移的過程中, 如果是原本符合的字, 要把 map 對應的 count +回去
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1); //!!
                    if (map.get(tempc) > 0) { //!!
                        counter++;
                    }
                }
                if (end - begin < len) {
                    len = end - begin;
                    head = begin;
                }
                begin++; // move letf
            }

        }
        if (len == Integer.MAX_VALUE) return "";
        return s.substring(head, head + len);
    }

    /*  Input: S = "ADOBECODEBANC", T = "ABC"
        Output: "BANC"
    */
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(new MinimumWindowSubstring().minWindow(s, t));

    }
}