package questions._09_dfs_backtracking.memo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself.
For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.


Example 1:
    Input:
        n = 12
    Output:
        3
    Explanation:
        12 = 4 + 4 + 4.

Example 2:
    Input:
        n = 13
    Output:
        2
    Explanation:
        13 = 4 + 9.


Constraints:
    1 <= n <= 10^4

*/
public class _0279_Perfect_Squares {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DP {

        public List<Integer> numSquares(int n) {
            List<Integer>[] dp = new ArrayList[n + 1];
            dp[0] = new ArrayList<>();
            dp[1] = List.of(1);
            for (int i = 2; i <= n; ++i) {
                for (int j = 1; j * j <= i; ++j) {
                    if (dp[i] == null || (dp[i - j * j].size() + 1 < dp[i].size())) {
                        List<Integer> list = new ArrayList<>();
                        list.addAll(dp[i - j * j]);
                        list.add(j * j);
                        dp[i] = list;
                    }
                }
            }
            return dp[n];
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS_MEMO {

        public int numSquares(int n) {
            int[] memo = new int[n + 1];
            Arrays.fill(memo, Integer.MAX_VALUE);
            for (int i = 1; i * i <= n; ++i) {
                memo[i * i] = 1;
            }
            memo[0] = 0;
            DFS(memo, n);
            return memo[n];
        }

        public int DFS(int[] memo, int n) {
            if (memo[n] != Integer.MAX_VALUE) {
                return memo[n];
            } else {
                for (int i = 1; i * i <= n; ++i) {
                    memo[n] = Math.min(memo[n], 1 + DFS(memo, n - i * i));
                }
            }
            return memo[n];
        }
    }

}
