package coding.leetcode._09_dynamicProgramming.oneDimension.optimized;

/*

The Tribonacci sequence Tn is defined as follows:

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.



Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537


Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.

 */
public class _1137_N_th_Tribonacci_Number {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int tribonacci(int n) {
        int t0 = 0;
        int t1 = 1;
        int t2 = 1;
        if (n == 0) {
            return t0;
        } else if (n == 1) {
            return t1;
        } else if (n == 2) {
            return t2;
        } else {
            int tn = t2 + t1 + t0;
            for (int i = 3; i <= n; ++i) {
                tn = t2 + t1 + t0;
                t0 = t1;
                t1 = t2;
                t2 = tn;
            }
            return tn;
        }
    }
}
