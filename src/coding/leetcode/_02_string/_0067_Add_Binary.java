package coding.leetcode._02_string;

/*

Given two binary strings a and b, return their sum as a binary string.


Example 1:
    Input:
        a = "11", b = "1"
    Output:
        "100"

Example 2:
    Input:
        a = "1010", b = "1011"
    Output:
        "10101"


Constraints:
    1 <= a.length, b.length <= 104
    a and b consist only of '0' or '1' characters.
    Each string does not contain leading zeros except for the zero itself.

*/

public class _0067_Add_Binary {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int carry = 0;
        int i1 = a.length() - 1;
        int i2 = b.length() - 1;
        while (i1 >= 0 || i2 >= 0) {
            int v1 = i1 < 0 ? 0 : Character.getNumericValue(a.charAt(i1));
            int v2 = i2 < 0 ? 0 : Character.getNumericValue(b.charAt(i2));
            int sum = v1 + v2 + carry;
            if (sum > 2) {
                carry = 1;
                res.insert(0, 1);
            } else if (sum == 2) {
                carry = 1;
                res.insert(0, 0);
            } else {
                carry = 0;
                res.insert(0, sum);
            }
            --i1;
            --i2;
        }
        if (carry == 1) {
            res.insert(0, 1);
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new _0067_Add_Binary().addBinary("10", "111"));
    }
}
