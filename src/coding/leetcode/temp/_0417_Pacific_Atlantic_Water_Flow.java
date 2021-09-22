/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.


Example:
    Given the following 5x5 matrix:
    
      Pacific ~   ~   ~   ~   ~ 
           ~  1   2   2   3  (5) *
           ~  3   2   3  (4) (4) *
           ~  2   4  (5)  3   1  *
           ~ (6) (7)  1   4   5  *
           ~ (5)  1   1   2   4  *
              *   *   *   *   * Atlantic
    Return:
        [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).


Note:
    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.

*/

public class _0417_Pacific_Atlantic_Water_Flow {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    int row;
    int col;
    int[][] visited;
    List<int[]> res = new ArrayList<>();
    int[][] matrix;
    int[] dirs = { -1, 0, 1, 0, -1 };

    public List<int[]> pacificAtlantic_DFS(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        this.matrix = matrix;
        row = matrix.length;
        col = matrix[0].length;
        visited = new int[row][col];
        for (int i = 0; i < row; i++) {
            DFS(i, col - 1, 1);
        }
        for (int i = 0; i < col; i++) {
            DFS(row - 1, i, 1);
        }

        for (int i = 0; i < col; i++) {
            DFS(0, i, 2);
        }
        for (int i = 0; i < row; i++) {
            DFS(i, 0, 2);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (3 == visited[i][j]) {
                    res.add(new int[] { i, j });
                }
            }
        }
        return res;
    }

    private void DFS(int i, int j, int flag) {
        if ((visited[i][j] & flag) == 0) {
            visited[i][j] += flag;
            for (int k = 0; k < 4; k++) {
                int _i = i + dirs[k];
                int _j = j + dirs[k + 1];
                if (_i >= 0 && _i < row && _j >= 0 && _j < col && matrix[_i][_j] >= matrix[i][j]) {
                    DFS(_i, _j, flag);
                }
            }
        }
    }

    public static void main(String[] args) {
        _0417_Pacific_Atlantic_Water_Flow test = new _0417_Pacific_Atlantic_Water_Flow();
        test.pacificAtlantic_DFS(new int[][] { { 1, 2, 2, 3, 5 }, { 3, 2, 3, 4, 4 }, { 2, 4, 5, 3, 1 }, { 6, 7, 1, 4, 5 }, { 5, 1, 1, 2, 4 } });

    }
}
