/**
 *  @author Yunxiang He
 *  @date 06/11/2018
 */

package questions.temp;

/*

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.


Example:
    Input:
    [
      ["1","0","1","0","0"],
      ["1","0","1","1","1"],
      ["1","1","1","1","1"],
      ["1","0","0","1","0"]
    ]
    Output: 6

*/

public class _0085_Maximal_Rectangle {

    public static void main(String[] args) {
        char[][] matrix = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' }, { '1', '0', '0', '1', '0' }, };
        _0085_Maximal_Rectangle test = new _0085_Maximal_Rectangle();
        test.maximalRectangle(matrix);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] != '0') {
                    dp[i][j] += (j == 0 ? 1 : dp[i][j - 1] + 1);
                }
                int min = Integer.MAX_VALUE;
                for (int k = i; k >= 0; --k) {
                    if (matrix[k][j] == '0') {
                        break;
                    } else {
                        min = Math.min(min, dp[k][j]);
                        max = Math.max(max, (i - k + 1) * min);
                    }
                }
            }
        }
        return max;
    }
}
