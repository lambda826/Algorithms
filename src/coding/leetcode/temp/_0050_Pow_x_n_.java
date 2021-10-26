/**
 *  @author Yunxiang He
 *  @date Jan 11, 2018 10:20:11 PM
 */

package coding.leetcode.temp;

/*

Implement pow(x, n), which calculates x raised to the power n (x^n).

Example 1:
    Input: 2.00000, 10
    Output: 1024.00000

Example 2:
    Input: 2.10^4, 3
    Output: 9.26100

Example 3:
    Input: 2.00000, -2
    Output: 0.25000
    Explanation: 2-2 = 1/22 = 1/4 = 0.25

Note:
    -100.0 < x < 100.0
    n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]

 */

public class _0050_Pow_x_n_ {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double myPow_Math(double x, int n) {
        if (n < 0) {
            // To avoid overflow
            return 1 / (myPow_Math(x, -(n + 1)) * myPow_Math(x, 1));
        } else if (n == 0) {
            return 1;
        } else {
            return ((n & 1) == 0 ? 1 : x) * myPow_Math(x * x, n >> 1);
        }
    }

    public static void main(String[] args) {
        _0050_Pow_x_n_ test = new _0050_Pow_x_n_();
        System.out.println(test.myPow_Math(111, -12));
        System.out.println(Math.pow(111, -12));
        int min = Integer.MIN_VALUE;
        System.out.println(min);
        System.out.println(Integer.toBinaryString(min));
        //  -2147483648
        //  10^50000000000000000000000
        System.out.println(-min);
        System.out.println(Integer.toBinaryString(-min));
        //  -2147483648
        //  10^50000000000000000000000
        System.out.println(-min - 1);
        System.out.println(Integer.toBinaryString(-min - 1));
        //   2147483647
        //  01111111111111111111111111111111
    }

}
