/**
 *  @author Yunxiang He
 *  @date 03/26/2019
 */

package questions.temp;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*

Write a program to find the n-th ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 


Example:
    Input: n = 10
    Output: 12
    Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note:  
    1 is typically treated as an ugly number.
    n does not exceed 1690.

*/

public class _0264_Ugly_Number_II {

    public static void main(String[] args) {
        _0264_Ugly_Number_II test = new _0264_Ugly_Number_II();
        test.nthUglyNumber(10);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // dp
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        int next2 = 1;
        int next3 = 1;
        int next5 = 1;
        for (int i = 0; i < n; ++i) {
            dp[i] = Math.min(Math.min(next2, next3), next5);
            if (dp[i] == next2) {
                next2 = dp[i2++] * 2;
            }
            if (dp[i] == next3) {
                next3 = dp[i3++] * 3;
            }
            if (dp[i] == next5) {
                next5 = dp[i5++] * 5;
            }
        }
        return dp[n - 1];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int nthUglyNumber2(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        long[] primes = { 2, 3, 5 };
        visited.add(1L);
        minHeap.offer(1L);
        while (--n > 0) {
            long num = minHeap.poll();
            for (long fac : primes) {
                if (visited.add(num * fac)) {
                    minHeap.offer(num * fac);
                }
            }
        }
        return minHeap.peek().intValue();
    }

}
