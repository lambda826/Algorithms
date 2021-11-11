package questions._12_dynamicProgramming.twoDimension.optimized;

/*

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.


Example 1:
    Input:
        matrix =
            [["1","0","1","0","0"],
             ["1","0","1","1","1"],
             ["1","1","1","1","1"],
             ["1","0","0","1","0"]]
    Output:
        4

Example 2:
    Input:
        matrix =
            [["0","1"],
             ["1","0"]]
    Output:
        1

Example 3:
    Input:
        matrix =
            [["0"]]
    Output:
        0


Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 300
    matrix[i][j] is '0' or '1'.

*/

public class _0221_Maximal_Square {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DP {

        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[][] dp = new int[m + 1][n + 1];
            int max = 0;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (matrix[i][j] == '1') {
                        dp[i + 1][j + 1] = 1 + Math.min(dp[i][j], Math.min(dp[i + 1][j], dp[i][j + 1]));
                        max = Math.max(max, dp[i + 1][j + 1]);
                    }
                }
            }
            return max * max;
        }
    }

    class Solution_DP_Optimized {
        public int maximalSquare(char[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            int[] dp = new int[n + 1];
            int max = 0;
            for (int i = 0; i < m; ++i) {
                int pre = 0;
                for (int j = 0; j < n; ++j) {
                    int temp = dp[j + 1];
                    if (matrix[i][j] == '1') {
                        dp[j + 1] = 1 + Math.min(pre, Math.min(dp[j], dp[j + 1]));
                        max = Math.max(max, dp[j + 1]);
                    } else {
                        dp[j + 1] = 0;
                    }
                    pre = temp;
                }
            }
            return max * max;
        }
    }
}