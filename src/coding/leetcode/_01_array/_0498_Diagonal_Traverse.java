package coding.leetcode._01_array;

/*

Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.


Example 1:
    Input:
        mat = [[1,2,3],
               [4,5,6],
               [7,8,9]]
    Output:
        [1,2,4,7,5,3,6,8,9]
Example 2:
    Input:
        mat = [[1,2],
               [3,4]]
    Output:
        [1,2,3,4]


Constraints:
    m == mat.length
    n == mat[i].length
    1 <= m, n <= 10^4
    1 <= m * n <= 10^4
    -10^5 <= mat[i][j] <= 10^5

*/

public class _0498_Diagonal_Traverse {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Simulation {

        public int[] findDiagonalOrder(int[][] mat) {
            int row = mat.length;
            int col = mat[0].length;
            int[] res = new int[row * col];
            int k = 0;
            int i = 0;
            int j = 0;
            boolean upLeft = true;
            while (k < res.length) {
                res[k++] = mat[i][j];
                // Next grid:
                if (upLeft) {
                    if (i > 0 && j + 1 < col) {
                        --i;
                        ++j;
                    } else {
                        upLeft = false; // Change direction
                        if (j + 1 < col) { // Prefer going right than going down
                            ++j;
                        } else {
                            ++i;
                        }
                    }
                } else {
                    if (i + 1 < row && j > 0) {
                        ++i;
                        --j;
                    } else {
                        upLeft = true; // Change direction
                        if (i + 1 < row) { // Prefer going down than going right
                            ++i;
                        } else {
                            ++j;
                        }
                    }
                }
            }
            return res;
        }
    }
}