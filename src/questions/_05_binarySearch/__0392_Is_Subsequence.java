package questions._05_binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*

Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. 
t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
(ie, "ace" is a subsequence of "abcde" while "aec" is not).


Example 1:
    s = "abc", t = "ahbgdc"
    Return true.

Example 2:
    s = "axc", t = "ahbgdc"
    Return false.


Follow up:
    If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence.
    In this scenario, how would you change your code?

 */

public class __0392_Is_Subsequence {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSubsequence(String shortString, String longString) {
        int i = 0;
        int j = 0;
        while (i < shortString.length() && j < longString.length()) {
            if (shortString.charAt(i) == longString.charAt(j)) {
                ++i;
            }
            ++j;
        }
        return i == shortString.length();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Follow up:
    //      If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence.
    //      In this scenario, how would you change your code?
    public boolean isSubsequence_FollowUp_BinarySearch(String s, String t) {
        // Record the indices of each character
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.putIfAbsent(t.charAt(i), new ArrayList<>());
            map.get(t.charAt(i)).add(i);
        }
        int index = 0;
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            } else {
                //                index = Collections.binarySearch(map.get(c), index) + 1;
                index = binarySearch(map.get(c), index) + 1;
                if (index == 0 || index > t.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Greater than or equal to target
    private int binarySearch(List<Integer> list, int target) {
        int lo = 0;
        int hi = list.size() - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (list.get(mid) < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return list.get(lo) < target ? -1 : list.get(lo);
    }

}