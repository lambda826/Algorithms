package coding.leetcode._02_string.palindrome;

/*

Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.


Example 1:
    Input:
        s = "A man, a plan, a canal: Panama"
    Output:
        true
    Explanation:
        "amanaplanacanalpanama" is a palindrome.

Example 2:
    Input:
        s = "race a car"
    Output:
        false
    Explanation:
        "raceacar" is not a palindrome.


Constraints:
    1 <= s.length <= 2 * 10^5
    s consists only of printable ASCII characters.

 */

public class _0125_Valid_Palindrome {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Two pointer
    public class Solution {

        public boolean isPalindrome(String s) {
            int p1 = 0;
            int p2 = s.length() - 1;
            while (p1 < p2) {
                while (p1 < p2 && !Character.isLetterOrDigit(s.charAt(p1))) {
                    ++p1;
                }
                while (p1 < p2 && !Character.isLetterOrDigit(s.charAt(p2))) {
                    --p2;
                }
                if (Character.toLowerCase(s.charAt(p1++)) != (Character.toLowerCase(s.charAt(p2--)))) {
                    return false;
                }
            }
            return true;
        }
    }

}