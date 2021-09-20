package coding.leetcode._02_string;

/*

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.


Example 1:
    Input:
        num1 = "2",
        num2 = "3"
    Output:
        "6"

Example 2:
    Input:
        num1 = "123",
        num2 = "456"
    Output:
        "56088"


Constraints:
    1 <= num1.length, num2.length <= 200
    num1 and num2 consist of digits only.
    Both num1 and num2 do not contain any leading zero, except the number 0 itself.

*/

public class _0043_Multiply_Strings {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public class Solution {

        public String multiply(String num1, String num2) {
            int len1 = num1.length();
            int len2 = num2.length();
            int[] prod = new int[len1 + len2];
            while (--len1 >= 0) {
                int tempLen = len2;
                while (--tempLen >= 0) {
                    int num = (num1.charAt(len1) - '0') * (num2.charAt(tempLen) - '0') + prod[len1 + tempLen + 1];
                    prod[len1 + tempLen + 1] = num % 10;
                    prod[len1 + tempLen] += num / 10;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int num : prod) {
                if (!(sb.length() == 0 && num == 0)) {
                    sb.append(num);
                }
            }

            return sb.length() == 0 ? "0" : sb.toString();
        }
    }

}