/**
 *  @author Yunxiang He
 *  @date 07/13/2018
 */

package coding.temp;

/*

Given a non-empty string s, you may delete at most one character. 
judge whether you can make it a palindrome.


Example 1:
    Input: "aba"
    Output: True

Example 2:
    Input: "abca"
    Output: True
    Explanation: You could delete the character 'c'.


Note:
    The string will only contain lowercase characters a-z. 
    The maximum length of the string is 50000.

*/

public class _0680_Valid_Palindrome_II {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean validPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return deleteOne(s, left + 1, right) || deleteOne(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private boolean deleteOne(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
