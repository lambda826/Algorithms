/**
 *  @author Yunxiang He
 *  @date 10/06/2017
 */

package coding.temp;

/*

Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.


Example 1:
    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.

Example 2:
    Input: "cbbd"
    Output: "bb"

*/

public class _0005_Longest_Palindromic_Substring {

    public static void main(String[] args) {
        _0005_Longest_Palindromic_Substring test = new _0005_Longest_Palindromic_Substring();
        System.out.println(test.longestPalindrome("bananas"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Find the ceterLeft, and centerRight
    public String longestPalindrome(String s) {
        int center = 0;
        int centerLeft = 0;
        int centerRight = 0;
        int maxLeft = 0;
        int maxRight = 0;
        int max = -1;
        while (center < s.length()) {
            centerLeft = centerRight = center;
            while (centerRight + 1 < s.length() && s.charAt(centerLeft) == s.charAt(centerRight + 1)) {
                ++centerRight;
            }
            center = centerRight + 1;
            while (centerLeft - 1 >= 0 && centerRight + 1 < s.length() && s.charAt(centerLeft - 1) == s.charAt(centerRight + 1)) {
                --centerLeft;
                ++centerRight;
            }
            if (centerRight - centerLeft > max) {
                max = centerRight - centerLeft;
                maxLeft = centerLeft;
                maxRight = centerRight + 1;
            }
        }
        return s.substring(maxLeft, maxRight);
    }
}
