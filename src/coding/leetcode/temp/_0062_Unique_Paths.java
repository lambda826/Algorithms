/**
 *  @author Yunxiang He
 */

package coding.leetcode.temp;

/*

A robot is located at the top-left corner of a m x n grid.

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Example 1:
Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right

Example 2:
Input: m = 7, n = 3
Output: 28


Note: m and n will be at most 100.

*/

public class _0062_Unique_Paths {

    public static void main(String[] args) {
        _0062_Unique_Paths test = new _0062_Unique_Paths();
        System.out.println(test.uniquePaths_DP(2, 1));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int uniquePaths_DP(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int r = 0; r < m; r++) {
            for (int c = 1; c <= n; c++) {
                dp[c] = dp[c] + dp[c - 1];
            }
            dp[0] = 0;
        }
        return dp[n];
    }

}
