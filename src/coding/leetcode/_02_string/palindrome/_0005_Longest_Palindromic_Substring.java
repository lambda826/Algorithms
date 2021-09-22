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
    // Find the ceterLeft, and centerRight
    class Solution {
        public String longestPalindrome(String s) {
            int max = 0;
            int start = 0;
            int end = 0;
            int p1 = 0;
            while (p1 < s.length()) {
                int p2 = p1;
                while (p2 + 1 < s.length() && s.charAt(p2 + 1) == s.charAt(p1)) {
                    ++p2;
                }
                int tempP1 = p1;
                int tempP2 = p2;
                while (tempP1 - 1 >= 0 && tempP2 + 1 < s.length() && s.charAt(tempP1 - 1) == s.charAt(tempP2 + 1)) {
                    --tempP1;
                    ++tempP2;
                }
                if (tempP2 + 1 - tempP1 > max) {
                    max = tempP2 + 1 - tempP1;
                    start = tempP1;
                    end = tempP2 + 1;
                }
                p1 = p2 + 1;
            }
            return s.substring(start, end);
        }
    }
}