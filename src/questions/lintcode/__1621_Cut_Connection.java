/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-14
 */

package questions.lintcode;

import java.util.Arrays;

/*

Given a matrix consists of 0 and 1, the first line is the roof.
Remove one of '1', all of the '1' that is not connected to the roof will drop and need to be set to '0'.


Example
    Given:
    matrix = [ 
             [1,1,1,1,1],
             [0,0,1,0,1],
             [0,0,1,0,1],
             [0,0,1,0,0]
             ]
    Point = (1,2)
    Return:
    matrix = [                  
             [1,1,1,1,1],
             [0,0,0,0,1],
             [0,0,0,0,1],
             [0,0,0,0,0]
             ]
         
         
Notice
    Roof will not be cut

*/

public class __1621_Cut_Connection {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 这个题居然意思是垂直的下方, 而不是连通性, 害我调试了半天
    public int[][] removeOne(int[][] matrix, int x, int y) {
        matrix[x][y] = 0;
        dfs(matrix, 0, 0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = 0;
                } else if (matrix[i][j] == 2) {
                    matrix[i][j] = 1;
                }
            }
        }
        return matrix;
    }

    private void dfs(int[][] matrix, int x, int y) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length && matrix[x][y] == 1) {
            matrix[x][y] = 2;
            dfs(matrix, x + 1, y);
            dfs(matrix, x - 1, y);
            dfs(matrix, x, y + 1);
            dfs(matrix, x, y - 1);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[][] removeOne2(int[][] matrix, int x, int y) {
        for (int r = x; r < matrix.length; r++) {
            matrix[r][y] = 0;
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new __1621_Cut_Connection()
                .removeOne(new int[][] { { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 1, 0, 1, 1 } }, 1, 1)));
    }
}
