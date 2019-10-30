/**
 *  @author Yunxiang He
 *  @date 12/22/2017
 */

package coding.temp;

import coding.lintcode.__0690_Factorial;

import java.util.ArrayList;
import java.util.List;

/*

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.


Example 1:
    Input: num1 = "2", num2 = "3"
    Output: "6"

Example 2:
    Input: num1 = "123", num2 = "456"
    Output: "56088"

Note:
    The length of both num1 and num2 is < 110.
    Both num1 and num2 contain only digits 0-9.
    Both num1 and num2 do not contain any leading zero, except the number 0 itself.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.

*/

public class _0043_Multiply_Strings {

    // Related
    __0690_Factorial __0690_Factorial = null;

    public static void main(String[] args) {
        System.out.println(new _0043_Multiply_Strings().multiply("123", "0"));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] ch1 = num1.toCharArray();
        char[] ch2 = num2.toCharArray();
        int carry = 0;
        int prod;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < ch1.length; ++i) {
            for (int j = 0; j < ch2.length; ++j) {
                if (list.size() == i + j) {
                    list.add(0);
                }
                prod = Character.getNumericValue(ch1[ch1.length - 1 - i]) * Character.getNumericValue(ch2[ch2.length - 1 - j]) + carry + list.get(i + j);
                carry = prod / 10;
                prod = prod % 10;
                list.set(i + j, prod);
            }
            while (carry > 0) {
                list.add(carry % 10);
                carry /= 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int digit : list) {
            sb.insert(0, digit);
        }
        return sb.toString();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String multiply2(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
        int prod;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                prod = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + pos[i + j + 1];
                pos[i + j] += prod / 10;
                pos[i + j + 1] = prod % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int p : pos) {
            if (!(sb.length() == 0 && p == 0)) {
                sb.append(p);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

}
