/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

/*

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).


Example 1:
    Input:
        s = "aa"
        p = "a"
    Output: 
        false
    Explanation: 
        "a" does not dp the entire string "aa".

Example 2:
    Input:
        s = "aa"
        p = "a*"
    Output: 
        true
    Explanation: 
        '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
    Input:
        s = "ab"
        p = ".*"
    Output: 
        true
    Explanation: 
        ".*" means "zero or more (*) of any character (.)".

Example 4:
    Input:
        s = "aab"
        p = "c*a*b"
    Output: 
        true
    Explanation: 
        c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".

Example 5:
    Input:
        s = "mississippi"
        p = "mis*is*p*."
    Output: 
        false


Note:
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.

*/

public class _0010_Regular_Expression_Matching {

    public static void main(String[] args) {
        System.out.println(new _0010_Regular_Expression_Matching().isMatch("aa", "a*"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // If p.charAt(j) == s.charAt(i) || p.charAt(j) == '.' :  dp[i][j] = dp[i-1][j-1];
    // If p.charAt(j) == '*': 
    //      Here are two sub conditions:
    //      if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
    //      if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
    //          dp[i][j] = dp[i-1][j]   // in this case, a* counts as multiple a 
    //       or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
    //       or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
    public boolean isMatch(String s, String p) {
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        boolean[][] dp = new boolean[ss.length + 1][pp.length + 1];
        // Initiallization
        dp[0][0] = true;
        for (int j = 0; j < pp.length; j++) {
            if (pp[j] == '*') {
                if (j - 2 < 0) {
                    dp[0][j + 1] = true;
                } else {
                    dp[0][j + 1] = dp[0][j - 1];
                }
            }
        }
        for (int j = 0; j < pp.length; ++j) {
            for (int i = 0; i < ss.length; ++i) {
                if (pp[j] == '.' || pp[j] == ss[i]) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (pp[j] == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j]; // * matches 0 or 1
                    if (!dp[i + 1][j + 1] && (pp[j - 1] == '.' || pp[j - 1] == ss[i])) { // * matches 1+
                        dp[i + 1][j + 1] = dp[i][j + 1];
                    }
                }
            }
        }
        return dp[ss.length][pp.length];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isMatch_DP(String s, String p) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        for (int j = p.length() - 1; j >= 0; j--) {
            if (p.charAt(j) == '*') {
                // 从后往前走是因为*代表前面的element零次或者多次
                for (int i = s.length() - 1; i >= 0; i--) {
                    // 已经match过了，或者后面的element都match，并且当前的element也match
                    dp[i] = dp[i] || (dp[i + 1] && (p.charAt(j - 1) == '.' || s.charAt(i) == p.charAt(j - 1)));
                }
                // 因为*是对于preceding（前一个）element有作用，所以走完*，要往前多走一步。
                j--;
            } else {
                for (int i = 0; i < s.length(); i++) {
                    // dp[i]（s的这个字母match成功）等于true有两个条件，首先是这个字母往后的所有字母都match了，并且这个位置的字母也match
                    dp[i] = dp[i + 1] && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
                }
                // e.g. s = "ab", p = ".*c" 只要走过一次p是字母的情况（除了*前的字母），就代表最后的位置最近也是这个位置了，所以match[s.length()]要变成false
                dp[s.length()] = false;
            }
        }
        return dp[0];
    }

}
