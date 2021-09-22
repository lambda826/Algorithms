/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-18 09:45
 */

package coding.leetcode.temp;

/*

Count the number of prime numbers less than a non-negative number, n.

Example:
Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

*/

public class _0204_Count_Primes {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // sieve of Eratosthenes
    // 要得到自然数n以内的全部素数，必须把不大于sqrt(n)的所有素数的倍数剔除，剩下的就是素数。
    // 给出要筛数值的范围n，找出以内的素数。先用2去筛，即把2留下，把2的倍数剔除掉；
    // 再用下一个质数，也就是3筛，把3留下，把3的倍数剔除掉；
    // 接下去用下一个质数5筛，把5留下，把5的倍数剔除掉；不断重复下去......。
    public int countPrimes(int n) {
        boolean[] notPrimes = new boolean[n];
        if (n < 3) {
            return 0;
        }
        int count = 1;
        // Skip even numbers
        for (int i = 3; i < n; i += 2) {
            if (!notPrimes[i]) {
                count++;
                for (int k = i; k < n; k += 2 * i) {
                    notPrimes[k] = true;
                }
            }
        }
        return count;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countPrimes2(int n) {
        if (n < 3) {
            return 0;
        }
        int k = 3;
        int count = 1;
        while (k < n) {
            if (isPrime(k)) {
                count++;
            }
            k += 2;
        }
        return count;
    }

    private boolean isPrime(int k) {
        int x = 3;
        while (x <= Math.sqrt(k)) {
            if (k % x == 0) {
                return false;
            }
            x += 2;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new _0204_Count_Primes().countPrimes(499979));
    }
}
