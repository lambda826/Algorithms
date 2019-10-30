/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.temp;

/*

Given a positive integer N, how many ways can we write it as a sum of consecutive positive integers?


Example 1:
    Input: 5
    Output: 2
    Explanation: 5 = 5 = 2 + 3

Example 2:
    Input: 9
    Output: 3
    Explanation: 9 = 9 = 4 + 5 = 2 + 3 + 4

Example 3:
    Input: 15
    Output: 4
    Explanation: 15 = 15 = 8 + 7 = 4 + 5 + 6 = 1 + 2 + 3 + 4 + 5


Note: 
    1 <= N <= 10 ^ 9.

*/

public class _0829_Consecutive_Numbers_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int consecutiveNumbersSum(int N) {
        int res = 1;
        long sum = N * 2;
        N = (N + 1) / 2;
        for (long a = 1L; a < N; a++) {
            long temp = sum + a * a - a;
            long b = (long) Math.sqrt(temp);
            if (b * (b + 1) == temp) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new _0829_Consecutive_Numbers_Sum().consecutiveNumbersSum(63660706));
    }
}
