/**
 *  @author Yunxiang He
 *  @date Jan 20, 2018 7:03:36 AM
 */

package coding.leetcode.temp;

/*

For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.

Now given a string representing n, you should return the smallest good base of n in string format. 

Example 1:
Input: "13"
Output: "3"
Explanation: 13 base 3 is 111.

Example 2:
Input: "4681"
Output: "8"
Explanation: 4681 base 8 is 11111.

Example 3:
Input: "10^5000000000"
Output: "999999999999999999"
Explanation: 10^5000000000 base 999999999999999999 is 11.

Note:
The range of n is [3, 10^18].
The string representing n is always valid and will not have leading zeros.

 */

public class _0483_Smallest_Good_Base {

    /**
     * Optimal Solution:
     * Binary Serach
     * --------------------------------------------------------------
     */
    public String smallestGoodBase(String n) {
        long num = 0;
        // This is faster than built-in Long::parseLong / Long::valueOf
        for (char c : n.toCharArray()) {
            num = num * 10 + c - '0';
        }
        for (int x = 62; x >= 2; x--) {
            // e.g. 
            // If num = 16, then x should x < 4, because the base is at least 2
            if ((1 << x) < num) {
                long base = (long) (Math.pow(num, 1.0 / x));
                if (base >= 2) {
                    long sum = 0;
                    long cur = 1;
                    // 等比数列求和
                    for (int i = 0; i <= x; i++) {
                        sum += cur;
                        cur *= base;
                    }
                    if (num == sum) {
                        return String.valueOf(base);
                    }
                }
            }
        }
        return String.valueOf(num - 1);
    }

    public String smallestGoodBase2(String n) {
        long num = 0;
        for (char c : n.toCharArray()) {
            num = num * 10 + c - '0';
        }
        for (int x = 64; x >= 1; x--) {
            if ((1 << x) < num) {
                long k = helper(num, x);
                if (k != -1) {
                    return String.valueOf(k);
                }
            }
        }
        return String.valueOf(num - 1);
    }

    private long helper(long num, int x) {
        long lo = 2;
        long hi = (long) (Math.pow(num, 1.0 / x) + 1);
        while (lo < hi) {
            long base = lo + (hi - lo) / 2;
            long sum = 0, cur = 1;
            for (int i = 0; i <= x; i++) {
                sum += cur;
                cur *= base;
            }
            if (sum == num) {
                return base;
            } else if (sum > num) {
                hi = base;
            } else {
                lo = base + 1;
            }
        }
        return -1;
    }

    public String smallestGoodBase3(String n) {
        long num = Long.valueOf(n);
        long res = 0;
        if ((num & 1) == 0) {
            res = num - 1;
        } else {
            for (long i = 2; i < num; i++) {
                if (isBase(num, i)) {
                    res = i;
                    break;
                }
            }
        }
        return String.valueOf(res);
    }

    private boolean isBase(long num, long base) {
        long temp = 0;
        long i = 0;
        while (Math.pow(base, i) <= num) {
            temp += Math.pow(base, i);
            i++;
        }
        if (temp == num) {
            return true;
        } else {
            return false;
        }
    }


}
