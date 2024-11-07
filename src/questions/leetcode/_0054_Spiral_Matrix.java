package questions.leetcode;

import java.util.ArrayList;
import java.util.List;

/*

Given an m x n matrix, return all elements of the matrix in spiral order.


Example 1:
    Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [1,2,3,6,9,8,7,4,5]

Example 2:
    Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]


Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100

*/
public class _0054_Spiral_Matrix {

    class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> res = new ArrayList<>();
            // up, right, down, left
            int[] border = { -1, matrix[0].length, matrix.length, -1 };
            int[] offset = { 1, -1, -1, 1 };
            int[] dir = { 0, 1, 0, -1, 0 };
            int d = 0;
            int x = 0;
            int y = 0;
            while (border[0] < border[2] - 1 && border[3] < border[1] - 1) {
                res.add(matrix[x][y]);
                if (x + dir[d] == border[0] || x + dir[d] == border[2] || y + dir[d + 1] == border[3] || y + dir[d + 1] == border[1]) {
                    border[d] += offset[d];
                    d = (d + 1) % 4;
                }
                x += dir[d];
                y += dir[d + 1];
            }
            return res;
        }

    }
}
