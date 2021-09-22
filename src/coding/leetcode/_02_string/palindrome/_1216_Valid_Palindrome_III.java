package coding.leetcode._02_string.palindrome;

/*

Given a string s and an integer k, return true if s is a k-palindrome.

A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.


Example 1:
    Input:
        s = "abcdeca",
        k = 2
    Output:
        true
    Explanation:
        Remove 'b' and 'e' characters.

Example 2:
    Input:
        s = "abbababa", k = 1
    Output:
        true


Constraints:
    1 <= s.length <= 1000
    s consists of only lowercase English letters.
    1 <= k <= s.length

*/

public class _1216_Valid_Palindrome_III {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS_Memo {

        public boolean isValidPalindrome(String s, int k) {
            return DFS(s, 0, s.length() - 1, new int[s.length()][s.length()]) <= k;
        }

        private int DFS(String s, int p1, int p2, int[][] memo) {
            if (p1 >= p2) {
                return 0;
            }
            if (memo[p1][p2] == 0) {
                if (s.charAt(p1) == s.charAt(p2)) {
                    memo[p1][p2] = DFS(s, p1 + 1, p2 - 1, memo);
                } else {
                    memo[p1][p2] = 1 + Math.min(DFS(s, p1 + 1, p2, memo), DFS(s, p1, p2 - 1, memo));
                }
            }
            return memo[p1][p2];
        }
    }

}