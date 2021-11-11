package questions.temp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.


Example 1:
    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                 Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false


Note:
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

*/

public class _0139_Word_Break {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int end = 1; end <= s.length(); ++end) {
            for (int start = end - 1; start >= 0; --start) {
                if (set.contains(s.substring(start, end)) && dp[start]) {
                    dp[end] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BackTracking + Memo
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] memo = new boolean[s.length() + 1];
        helper(s, 0, set, memo);
        return memo[memo.length - 1];
    }

    private void helper(String s, int start, Set<String> set, boolean[] memo) {
        if (!memo[memo.length - 1]) { // If not found a combination
            for (int end = start + 1; end <= s.length(); ++end) {
                if (!memo[end] && set.contains(s.substring(start, end))) {
                    memo[end] = true;
                    helper(s, end, set, memo);
                }
            }
        }
    }
}
