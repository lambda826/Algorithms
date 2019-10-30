/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-13 03:42
 */

package coding.leetcode.todo;

/*

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"

*/

public class _0067_Add_Binary {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String addBinary(String a, String b) {
        char[] ch1 = a.toCharArray();
        char[] ch2 = b.toCharArray();
        int carry = 0;
        StringBuilder sb = new StringBuilder("");
        for (int x = ch1.length - 1, y = ch2.length - 1; x > -1 || y > -1; x--, y--) {
            int a1 = x > -1 ? Character.getNumericValue(ch1[x]) : 0;
            int a2 = y > -1 ? Character.getNumericValue(ch2[y]) : 0;
            int sum = a1 + a2 + carry;
            if (sum < 2) {
                sb.insert(0, sum);
                carry = 0;
            } else if (sum == 2) {
                sb.insert(0, 0);
                carry = 1;
            } else {
                sb.insert(0, 1);
                carry = 1;
            }
        }
        if (carry == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _0067_Add_Binary().addBinary("10", "111"));
    }
}
