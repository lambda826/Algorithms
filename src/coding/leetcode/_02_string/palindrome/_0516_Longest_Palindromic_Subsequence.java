package coding.leetcode._02_string.palindrome;

/*

Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.


Example 1:
    Input:
        s = "bbbab"
    Output:
        4
    Explanation:
        One possible longest palindromic subsequence is "bbbb".

Example 2:
    Input:
        s = "cbbd"
    Output:
        2
    Explanation:
        One possible longest palindromic subsequence is "bb".


Constraints:
    1 <= s.length <= 1000
    s consists only of lowercase English letters.

*/

public class _0516_Longest_Palindromic_Subsequence {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Solution_DFS_Memo {

        public int longestPalindromeSubseq(String s) {
            return s.length() - DFS(s, 0, s.length() - 1, new int[s.length()][s.length()]);
        }

        private int DFS(String s, int p1, int p2, int[][] memo) {
            if (p1 >= p2) {
                return 0;
            }
            if (memo[p1][p2] != 0) {
                return memo[p1][p2];
            }
            if (s.charAt(p1) == s.charAt(p2)) {
                memo[p1][p2] = DFS(s, p1 + 1, p2 - 1, memo);
            } else {
                memo[p1][p2] = 1 + Math.min(DFS(s, p1 + 1, p2, memo), DFS(s, p1, p2 - 1, memo));
            }
            return memo[p1][p2];
        }
    }
}