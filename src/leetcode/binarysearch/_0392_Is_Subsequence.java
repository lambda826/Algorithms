package leetcode.binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*

Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.
(i.e., "ace" is a subsequence of "abcde" while "aec" is not).


Example 1:
    Input: s = "abc", t = "ahbgdc"
    Output: true

Example 2:
    Input: s = "axc", t = "ahbgdc"
    Output: false


Constraints:
    0 <= s.length <= 100
    0 <= t.length <= 10^4
    s and t consist only of lowercase English letters.


Follow up:
    Suppose there are lots of incoming s, say s1, s2, ..., sk where k >= 10^9, and you want to check one by one to see if t has its subsequence.
    In this scenario, how would you change your code?

*/
public class _0392_Is_Subsequence {

    class Solution {
        public boolean isSubsequence(String s, String t) {
            int i1 = 0;
            int i2 = 0;
            while (i1 < s.length() && i2 < t.length()) {
                if (s.charAt(i1) == t.charAt(i2)) {
                    i1++;
                }
                i2++;
            }
            return i1 == s.length();
        }
    }

    class Solution_Followup {
        public boolean isSubsequence(String s, String t) {
            Map<Character, List<Integer>> chars = new HashMap<>();
            for (int i = 0; i < t.length(); ++i) {
                chars.putIfAbsent(t.charAt(i), new ArrayList<>());
                chars.get(t.charAt(i)).add(i);
            }
            int next = -1;
            for (int i = 0; i < s.length(); ++i) {
                if (!chars.containsKey(s.charAt(i))) {
                    return false;
                }
                next = binarySearch(chars.get(s.charAt(i)), next) + 1;
                if (next == 0) {
                    return false;
                }
            }
            return true;
        }

        private int binarySearch(List<Integer> list, int target) {
            if (target > list.get(list.size() - 1)) {
                return -1;
            }
            int lo = 0;
            int hi = list.size() - 1;
            while (lo < hi) {
                int m = lo + (hi - lo) / 2;
                if (list.get(m) < target) {
                    lo = m + 1;
                } else {
                    hi = m;
                }
            }
            return list.get(lo);
        }
    }
}
