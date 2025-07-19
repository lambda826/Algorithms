package leetcode;

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
    Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25

Constraints:
    -100.0 < x < 100.0
    -2^31 <= n <= 2^31-1
    n is an integer.
    Either x is not zero or n > 0.
    -10^4 <= x^n <= 10^4

*/
public class _0050_Pow_x_n_ {

    class Solution {

        public double myPow(double x, int n) {
            if ((n & 1) == 1) {
                return x * myPow(x, n - 1);
            } else if (n < 0) {
                return 1 / (myPow(x, -(n + 1)) * x);
            } else if (n == 0) {
                return 1;
            } else {
                return myPow(x * x, (n >> 1));
            }
        }

    }

}
