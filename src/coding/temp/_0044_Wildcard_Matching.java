/**
 *  @author Yunxiang He
 *  @date 06/26/2018
 */

package coding.temp;

/*

Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).


Example 1:
    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".

Example 2:
    Input:
    s = "aa"
    p = "*"O
    Output: true
    Explanation: '*' matches any sequence.

Example 3:
    Input:
    s = "cb"
    p = "?a"
    Output: false
    Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Example 4:
    Input:
    s = "adceb"
    p = "*a*b"
    Output: true
    Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".

Example 5:
    Input:
    s = "acdcb"
    p = "a*c?b"
    Output: false


Note:
    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like ? or *.

*/

public class _0044_Wildcard_Matching {

    public static void main(String[] args) {
        _0044_Wildcard_Matching test = new _0044_Wildcard_Matching();
        System.out.println(test.isMatch_DP2("acdcb", "a*c?b"));
        //        System.out.println(test.isMatch_Dp("ab", "*a*b*"));
        //        System.out.println(test.isMatch_Dp("acdcb", "a*c?b"));
        //        System.out.println(test.isMatch_Dp("aab", "c*a*b"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isMatch_DP2(String s, String p) {
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        boolean[][] dp = new boolean[ss.length + 1][pp.length + 1];
        // Initiallization
        dp[0][0] = true;
        for (int j = 0; j < pp.length; ++j) {
            if (pp[j] == '*') {
                dp[0][j + 1] = dp[0][j];
            }
            for (int i = 0; i < ss.length; ++i) {
                if (pp[j] == '?' || pp[j] == ss[i]) {
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (pp[j] == '*') {
                    dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }
        return dp[ss.length][pp.length];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 总是假定星号不匹配，后续匹配不成功才使用星号
    // 因此非星号总是被优先消耗掉
    // 保存最近一个星号的位置，以及该星号所匹配到的 s 上的位置。
    //
    // https://leetcode.com/problems/wildcard-matching/discuss/17829/Two-explained-points-in-the-highest-voted-solution
    // Given a string s and a pattern p, 
    // Consider the pattern string is divided into two substrings, p1 and p2, each of which starts with a '*'. 
    // With greedy matching, suppose p1 matches the shortest head substring of s, namely s1. 
    // Let's name its complement as s2, then we have s = s1 + s2 and p = p1 + p2.
    //
    // The statement is:
    // If p2 doesn't match s2 then p doesn't match s.
    //
    // The proof is the following:
    // If s1 is the only head substring of s matching p1, then we don't have alternative matches for p1, then the only case is checked.
    // If there is another head substring of s matching p1, namely s1' which must be longer than s1, then its complement, name s2' must be a tail substring of s2. We know that p2 doesn't match s2, and p2 starts with '*', then we know p2 doesn't match any tail substring of s2, so p2 doesn't match
    // s2' either. To illustrate, consider:
    // p2 = "*z"
    // s2 = "abcd"
    // p2 doesn't match s2, and p2 doesn't match any of "bcd", "cd", or "d". 
    // This is because in eyes of '*' "abc" "bc" "c" and "" are all the same.
    public boolean isMatch_Greedy(String s, String p) {
        int i = 0;
        int j = 0;
        int star = -1;
        int match = -1;
        while (i < s.length()) {
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i;
                ++j;
            } else if (j < p.length() && p.charAt(j) == '*') {
                star = j;
                ++j;
                match = i;
            } else if (star != -1) {
                j = star + 1;
                match++;
                i = match;
            } else {
                return false;
            }
        }
        while (j < p.length() && p.charAt(j) == '*') {
            ++j;
        }
        return j == p.length();
    }

}
