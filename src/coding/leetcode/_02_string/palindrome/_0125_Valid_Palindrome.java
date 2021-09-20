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
    public class Solution {

        public boolean isPalindrome(String s) {
            int i = 0;
            int j = s.length() - 1;
            while (i < j) {
                while (i < j && Character.isLetter(s.charAt(i))) {
                    ++i;
                }
                while (i < j && Character.isLetter(s.charAt(j))) {
                    --j;
                }
                if (Character.toLowerCase(s.charAt(i)) != (Character.toLowerCase(s.charAt(j)))) {
                    return false;
                }
                ++i;
                --j;
            }
            return true;
        }
    }

}