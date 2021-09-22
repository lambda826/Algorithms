/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.


Example 1: 
    Input:
        0 0 0
        0 1 0
        0 0 0
    Output:
        0 0 0
        0 1 0
        0 0 0

Example 2: 
    Input:
        0 0 0
        0 1 0
        1 1 1
    Output:
        0 0 0
        0 1 0
        1 2 1


Note:
    The number of elements of the given matrix will not exceed 10,000.
    There are at least one 0 in the given matrix.
    The cells are adjacent in only four directions: up, down, left and right.

*/

public class _0542_01_Matrix {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[][] updateMatrix_BFS(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        Queue<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        int[] dirs = { -1, 0, 1, 0, -1 };
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (0 == matrix[i][j]) {
                    que.add(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }
        int len = 1;
        int size;
        int x;
        int y;
        int[] next;
        while (!que.isEmpty()) {
            size = que.size();
            for (int i = 0; i < size; i++) {
                next = que.remove();
                for (int j = 0; j < 4; j++) {
                    x = next[0] + dirs[j];
                    y = next[1] + dirs[j + 1];
                    if (x >= 0 & x < row && y >= 0 && y < col && !visited[x][y]) {
                        visited[x][y] = true;
                        que.add(new int[] { x, y });
                        matrix[x][y] = len;
                    }
                }
            }
            len++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        _0542_01_Matrix test = new _0542_01_Matrix();
        test.updateMatrix_BFS(new int[][] { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } });
    }
}
