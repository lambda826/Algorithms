/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-16 12:27
 */

package coding.leetcode.todo;

/*

Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:
The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

*/

public class _0415_Add_Strings {
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String addStrings(String num1, String num2) {
        int len1 = num1.length() - 1;
        int len2 = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (len1 >= 0 || len2 >= 0) {
            int digit = (len1 >= 0 ? num1.charAt(len1--) - 48 : 0) + (len2 >= 0 ? num2.charAt(len2--) - 48 : 0) + carry;
            carry = digit / 10;
            digit = digit % 10;
            sb.insert(0, digit);
        }
        if (carry > 0) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new _0415_Add_Strings().addStrings("1233", "36612");
        System.out.println('4' + '1');
    }

}
