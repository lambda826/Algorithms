package coding.leetcode._02_string;

/*

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.

You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
You must also not convert the inputs to integers directly.


Example 1:
    Input:
        num1 = "11", num2 = "123"
    Output:
        "134"

Example 2:
    Input:
        num1 = "456", num2 = "77"
    Output:
        "533"

Example 3:
    Input: num1 = "0", num2 = "0"
    Output: "0"

Constraints:
    1 <= num1.length, num2.length <= 104
    num1 and num2 consist of only digits.
    num1 and num2 don't have any leading zeros except for the zero itself.

*/

public class _0415_Add_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i1 = num1.length() - 1;
        int i2 = num2.length() - 1;
        int carry = 0;
        while (i1 >= 0 || i2 >= 0) {
            int v1 = i1 < 0 ? 0 : Character.getNumericValue(num1.charAt(i1));
            int v2 = i2 < 0 ? 0 : Character.getNumericValue(num2.charAt(i2));
            int sum = v1 + v2 + carry;
            carry = sum / 10;
            sb.insert(0, sum % 10);
            --i1;
            --i2;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }

}