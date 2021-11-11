/**
 *  @author Yunxiang He
 *  @date 04/23/2018
 */

package questions.temp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*

Write a program to find the nth super ugly number.
Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.


Example:
    Input: n = 12, primes = [2,7,13,19]
    Output: 32 
    Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 
                 super ugly numbers given primes = [2,7,13,19] of size 4.


Note:
    1 is a super ugly number for any given primes.
    The given numbers in primes are in ascending order.
    0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
    The nth super ugly number is guaranteed to fit in a 32-bit signed integer.

*/

public class _0313_Super_Ugly_Number {

    public static void main(String[] args) {
        _0313_Super_Ugly_Number test = new _0313_Super_Ugly_Number();
        test.nthSuperUglyNumber(12, new int[] { 2, 7, 13, 19 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // dp
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 1;
        int[] idx = new int[primes.length];
        int[] nextPrimes = new int[primes.length];
        Arrays.fill(nextPrimes, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < primes.length; ++j) {
                dp[i] = Math.min(dp[i], nextPrimes[j]);
            }
            for (int j = 0; j < primes.length; ++j) {
                if (dp[i] == nextPrimes[j]) {
                    nextPrimes[j] = dp[idx[j]++] * primes[j];
                }
            }
        }
        return dp[n - 1];
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // heap
    public int nthSuperUglyNumber2(int n, int[] primes) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        visited.add(1L);
        minHeap.offer(1L);
        while (--n > 0) {
            long num = minHeap.poll();
            for (int prime : primes) {
                if (visited.add(num * prime)) {
                    minHeap.offer(num * prime);
                }
            }
        }
        return minHeap.peek().intValue();
    }

}
