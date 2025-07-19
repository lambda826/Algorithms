package leetcode;

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

    class Solution_DP {
        public String longestPalindrome(String s) {
            int len = s.length();
            boolean[][] dp = new boolean[len][len];
            int left = 0;
            int right = 0;
            for (int i = 0; i < len - 1; ++i) {
                dp[i][i] = true;
                if (s.charAt(i) == s.charAt(i + 1)) {
                    dp[i][i + 1] = true;
                    left = i;
                    right = i + 1;
                }
            }
            for (int k = 2; k < len; ++k) {
                for (int i = 0; i + k < len; ++i) {
                    int j = i + k;
                    if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        left = i;
                        right = j;
                    }
                }
            }
            return s.substring(left, right + 1);
        }
    }
}