package coding.leetcode._03_hash.rolling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
Given a string s, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.



Example 1:

Input: s = "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:

Input: s = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:

Input: s = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.
Example 4:

Input: s = "aaaaa"
Output: 4
Explanation: The longest repeating substring is "aaaa", which occurs twice.


Constraints:

The string s consists of only lowercase English letters from 'a' - 'z'.
1 <= s.length <= 1500

 */
public class _1062_Longest_Repeating_Substring {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestRepeatingSubstring_BinarySearch(String s) {
        int lo = 1;
        int hi = s.length();
        while (lo < hi) {
            int len = lo + (hi - lo) / 2;
            if (containsRepeated(s, len)) {
                lo = len + 1;
            } else {
                hi = len;
            }
        }
        return lo - 1;
    }

    private boolean containsRepeated(String s, int len) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i + len <= s.length(); ++i) {
            if (!set.add(s.substring(i, i + len))) {
                return true;
            }
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Analysis:
    //      We can view the string as two strings which starts from 0 ends in length() - 1 and start from 1 and ends in length();
    //      So the problem is transformed into finding longest common substring
    public int longestRepeatingSubstring_DP(String s) {
        int max = 0;
        int[][] dp = new int[s.length() + 1][s.length() + 1];
        for (int i = 0; i < s.length() - 1; ++i) {
            for (int j = i + 1; j < s.length(); ++j) {
                dp[i + 1][j + 1] = s.charAt(i) == s.charAt(j) ? dp[i][j] + 1 : 0;
                max = Math.max(max, dp[i + 1][j + 1]);
            }
        }
        return max;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int longestRepeatingSubstring_Hashing(String s) {
        Map<String, List<Integer>> curr = new HashMap<>();
        Map<String, List<Integer>> next = new HashMap<>();

        for (int i = 0; i < s.length(); ++i) {
            String key = Character.toString(s.charAt(i));
            curr.putIfAbsent(key, new ArrayList<>());
            curr.get(key).add(i);
        }

        int len = 1;
        int max = 0;
        while (curr.size() > 0 && len < s.length()) {
            ++len;
            for (Map.Entry<String, List<Integer>> entry : curr.entrySet()) {
                if (entry.getValue().size() > 1) {
                    max = Math.max(max, entry.getKey().length());
                    for (int index : entry.getValue()) {
                        if (index + len <= s.length()) {
                            String key = entry.getKey() + s.charAt(index + len - 1);
                            next.putIfAbsent(key, new ArrayList<>());
                            next.get(key).add(index);
                        }
                    }
                }
            }
            curr = next;
            next = new HashMap<>();
        }
        return max;
    }


}
