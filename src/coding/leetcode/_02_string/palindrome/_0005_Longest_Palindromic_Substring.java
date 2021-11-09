package coding.leetcode._02_string.palindrome;

/*

Given a string s, return the longest palindromic substring in s.


Example 1:
    Input:
        s = "babad"
    Output:
        "bab"
    Note:
        "aba" is also a valid answer.

Example 2:
    Input: s = "cbbd"
    Output: "bb"

Example 3:
    Input:
        s = "a"
    Output:
        "a"

Example 4:
    Input:
        s = "ac"
    Output:
        "a"


Constraints:
    1 <= s.length <= 1000
    s consist of only digits and English letters.

*/

public class _0005_Longest_Palindromic_Substring {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Expand Around Center approach
    //
    // Time complexity : O(n^2)
    // Space complexity : O(1)
    class Solution {

        public String longestPalindrome(String s) {
            int max = 0;
            int start = 0;
            int end = 0;
            int centerLeft = 0;
            while (centerLeft < s.length()) {
                int centerRight = centerLeft;
                while (centerRight + 1 < s.length() && s.charAt(centerRight + 1) == s.charAt(centerLeft)) {
                    ++centerRight;
                }
                int expandToLeft = centerLeft;
                int expandToRight = centerRight;
                while (expandToLeft - 1 >= 0 && expandToRight + 1 < s.length() && s.charAt(expandToLeft - 1) == s.charAt(expandToRight + 1)) {
                    --expandToLeft;
                    ++expandToRight;
                }
                if (expandToRight + 1 - expandToLeft > max) {
                    max = expandToRight + 1 - expandToLeft;
                    start = expandToLeft;
                    end = expandToRight + 1;
                }
                centerLeft = centerRight + 1;
            }
            return s.substring(start, end);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DP {
        public String longestPalindrome(String s) {
            int len = s.length();
            boolean[][] dp = new boolean[len][len];
            int start = 0;
            int end = 0;
            int max = 0;
            for (int l = 0; l < len; ++l) {
                for (int i = 0, j = i + l; j < len; ++i, ++j) {
                    if (i == j) {
                        dp[i][j] = true;
                    } else {
                        boolean isSame = s.charAt(i) == s.charAt(j);
                        if (i + 1 == j) {
                            dp[i][j] = isSame;
                        } else {
                            dp[i][j] = isSame && dp[i + 1][j - 1];
                        }
                    }
                    if (dp[i][j] && j - i > max) {
                        max = j - i;
                        start = i;
                        end = j;
                    }
                }
            }
            return s.substring(start, end + 1);
        }
    }
}