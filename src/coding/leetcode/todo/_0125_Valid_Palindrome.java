/**
 *  @author Yunxiang He
 *  @date 01/25/2018
 */

package coding.leetcode.todo;

/*

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.


Example 1:
    Input: "A man, a plan, a canal: Panama"
    Output: true

Example 2:
    Input: "race a car"
    Output: false


Note: 
    For the purpose of this problem, we define empty string as valid palindrome.

 */

public class _0125_Valid_Palindrome {

    public static void main(String[] argss) {
        String string = "0P";
        _0125_Valid_Palindrome test = new _0125_Valid_Palindrome();
        System.out.println(test.isPalindrome(string));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

}
