/**
 * @author Yunxiang He
 * @date Jan 29, 2018 9:55:28 PM
 */

package questions.temp;

/*

A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. 
A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. 
In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

*/

public class _0413_Arithmetic_Slices {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numberOfArithmeticSlices_DP(int[] A) {
        if (A.length < 3) {
            return 0;
        }
        // dp[i] denotes the number of arithmetic slices of end with i
        int[] dp = new int[A.length];
        for (int i = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        int count = 0;
        for (int c : dp) {
            count += c;
        }
        return count;
    }
}
