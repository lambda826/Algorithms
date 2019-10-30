/**
 *  @author Yunxiang He
 *  @date Jan 25, 2018 3:29:35 AM
 */

package coding.temp;

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

public class _0392_Is_Subsequence {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSubsequence_TwoPointers(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == s.length();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSubsequence_String(String s, String t) {
        int start = 0;
        for (char c : s.toCharArray()) {
            start = t.indexOf(c, start) + 1;
            if (start == 0) {
                return false;
            }
        }
        return true;
    }

    /*
     
      Follow up:
      If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. 
      In this scenario, how would you change your code?
     
    */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isSubsequence_FollowUp_BinarySearch(String s, String t) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i < t.length(); i++) {
            if (!map.containsKey(t.charAt(i))) {
                map.put(t.charAt(i), new ArrayList<Integer>());
            }
            map.get(t.charAt(i)).add(i);
        }
        for (char c : s.toCharArray()) {
            if (!map.containsKey(c)) {
                return false;
            } else {
                index = binarySearch(c, map.get(c), index) + 1;
                if (index == 0 || index > t.length()) {
                    return false;
                }
            }
        }
        return true;
    }

    // Greater than or equal to target
    private int binarySearch(char c, List<Integer> list, int target) {
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

    public static void main(String[] args) {
        _0392_Is_Subsequence test = new _0392_Is_Subsequence();
        System.out.println(test.isSubsequence_FollowUp_BinarySearch("abc", "abc"));
    }
}
