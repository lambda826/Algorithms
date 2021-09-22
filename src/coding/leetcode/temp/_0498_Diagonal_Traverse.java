/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package coding.leetcode.temp;

/*



 */

public class _0498_Diagonal_Traverse {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] res = new int[row * col];
        int k = 0;
        int i = 0;
        int j = 0;
        boolean up = true;
        while (k < res.length) {
            res[k++] = mat[i][j];
            // next grid:
            if (up) {
                if (i > 0 && j + 1 < col) {
                    --i;
                    ++j;
                } else {
                    up = false;
                    if (j + 1 < col) { // prefer going right than going down
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
                    up = true;
                    if (i + 1 < row) { // prefer going down than going right
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
