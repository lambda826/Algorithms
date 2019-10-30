/**
 *  @author Yunxiang He
 *  @date 01/11/2018
 */

package coding.temp;

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

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int len = 0; len < dp.length; len++) {
            for (int l = 0; l < len && !dp[len]; l++) {
                dp[len] = dp[l] && set.contains(s.substring(l, len));
            }
        }
        return dp[dp.length - 1];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS + Memo
    public boolean wordBreak2(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        DFS(s, wordDict, dp, 0);
        return dp[dp.length - 1];
    }

    private void DFS(String s, List<String> wordDict, boolean[] dp, int start) {
        if (!dp[dp.length - 1]) {
            for (int end = start + 1; end <= s.length(); end++) {
                if (!dp[end - 1] && wordDict.contains(s.substring(start, end))) {
                    dp[end - 1] = true;
                    DFS(s, wordDict, dp, end);
                }
            }
        }
    }
}
