package questions._02_string;

/*

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
You must also not convert the inputs to integers directly.


Example 1:
    Input:
        num1 = "11",
        num2 = "123"
    Output:
        "134"

Example 2:
    Input:
        num1 = "456",
        num2 = "77"
    Output:
        "533"

Example 3:
    Input: num1 = "0",
    num2 = "0"
    Output: "0"

Constraints:
    1 <= num1.length, num2.length <= 10^4
    num1 and num2 consist of only digits.
    num1 and num2 don't have any leading zeros except for the zero itself.

*/

public class _0415_Add_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {
        public String addStrings(String num1, String num2) {
            StringBuilder sb = new StringBuilder();
            int carry = 0;
            int l1 = num1.length() - 1;
            int l2 = num2.length() - 1;
            while (l1 >= 0 || l2 >= 0 || carry > 0) {
                int sum = (l1 < 0 ? 0 : num1.charAt(l1--) - '0')
                          + (l2 < 0 ? 0 : num2.charAt(l2--) - '0')
                          + carry;
                carry = sum / 10;
                sum = sum % 10;
                sb.insert(0, sum);
            }
            return sb.toString();
        }
    }
}