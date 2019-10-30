/**
 * @author Yunxiang He
 * @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

/*

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. 
Return the maximum product you can get.


Example 1:
    Input: 2
    Output: 1
    Explanation: 2 = 1 + 1, 1 × 1 = 1.

Example 2:
    Input: 10
    Output: 36
    Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.


Note: 
    You may assume that n is not less than 2 and not larger than 58.

*/

public class _0343_Integer_Break {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int integerBreak_DP(int n) {
        if (n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Knapsack
    // We can assume that the volume of the knapsack is n. 
    // The items we can choose range from 1 to n - 1 (because we must divide n into at least two positive parts). 
    // The point is that we can choose each item many times.
    // For each item, we have two choices, pick it up or not. 
    // And we should choose the max result.
    // dp[j] = Math.max(dp[j], dp[j - i] * i);
    public int integerBreak_DP2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int item = 1; item < n; item++) {
            for (int sum = item; sum <= n; sum++) {
                dp[sum] = Math.max(dp[sum], dp[sum - item] * item);
            }
        }
        return dp[n];
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 由n个数的算术平均数大于等于它们的几何平均数：
    // 得：当把输入的n拆分成几个相等的数时它们的积最大。
    // 那么问题来了，拆分成几个呢？
    // 为了方便使用导数，我们先假设我们可以把 n 拆分成实数。那么设每一个数为x,则一共有 n / x 个数。
    // 设它们的积为 f(x), 则 f(x) = x(n/x)，那么怎么求f(x)最大值呢？求导数！
    // f′(x)=(n/x2) * x(n/x) * (1-lnx)
    // 当 x = e 时取极大值。
    // 而我们题目里规定 x 为整数，那么我们只需要取的x越靠近e越好。那么 2 < e < 3，而且 e = 2.71828...，所以取3是最好的，如果取不到3就取2。
    // 幂运算复杂度为O(lgn)，所以这个算法复杂度为O(lgn)。
    public int integerBreak_Math(int n) {
        if (n <= 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        res *= n;
        return res;
    }

    public static void main(String[] args) {
        _0343_Integer_Break dp = new _0343_Integer_Break();
        System.out.println(dp.integerBreak_DP(7));
        System.out.println(dp.integerBreak_Math(7));
    }
}
