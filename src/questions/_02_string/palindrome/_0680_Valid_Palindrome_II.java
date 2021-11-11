package questions._02_string.palindrome;

/*

Given a string s, return true if the s can be palindrome after deleting at most one character from it.


Example 1:
    Input:
        s = "aba"
    Output:
        true

Example 2:
    Input:
        s = "abca"
    Output:
        true
    Explanation:
        You could delete the character 'c'.

Example 3:
    Input:
        s = "abc"
    Output:
        false


Constraints:
    1 <= s.length <= 10^5
    s consists of lowercase English letters.

*/

public class _0680_Valid_Palindrome_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        public boolean validPalindrome(String s) {
            int p1 = 0;
            int p2 = s.length() - 1;
            while (p1 < p2) {
                if (s.charAt(p1) != s.charAt(p2)) {
                    return delete(s, p1 + 1, p2) || delete(s, p1, p2 - 1);
                }
                ++p1;
                --p2;
            }
            return true;
        }

        private boolean delete(String s, int p1, int p2) {
            while (p1 < p2) {
                if (s.charAt(p1++) != s.charAt(p2--)) {
                    return false;
                }
            }
            return true;
        }
    }

}