/**
 * @author Yunxiang He
 * @date 10/05/2017
 */
package questions.temp;

/*

Determine whether an integer is a palindrome. 
An integer is a palindrome when it reads the same backward as forward.


Example 1:
    Input: 121
    Output: true

Example 2:
    Input: -121
    Output: false
    Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.

Example 3:
    Input: 10
    Output: false
    Explanation: Reads 01 from right to left. Therefore it is not a palindrome.


Follow up:
    Coud you solve it without converting the integer to a string?

*/

public class _0009_Palindrome_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int num = x;
        int sum = 0;
        while (x > 0) {
            sum = sum * 10 + x % 10;
            x /= 10;
        }
        return num == sum;
    }

    public static void main(String[] args) {
        _0009_Palindrome_Number test = new _0009_Palindrome_Number();
        System.out.println(test.isPalindrome(990));
        System.out.println(test.isPalindrome(9909));
        System.out.println(test.isPalindrome(99099));
    }
}
