/**
 *  @author Yunxiang He
 *  @date 06/28/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.List;

/*

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.


Example 1:
    Input:
    [
     [ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]
    ]
    Output: [1,2,3,6,9,8,7,4,5]

Example 2:
    Input:
    [
      [1, 2, 3, 4],
      [5, 6, 7, 8],
      [9,10,11,12]
    ]
    Output: [1,2,3,4,8,12,11,10,9,5,6,7]

 */

public class _0054_Spiral_Matrix {

    public static void main(String[] args) {
        System.out.println(new _0054_Spiral_Matrix().spiralOrder(new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } }));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int up = 0;
        int left = 0;
        int down = matrix.length - 1;
        int right = matrix[0].length - 1;
        while (up <= down && left <= right) {
            // right toward
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            up++;
            // down toward
            for (int j = up; j <= down; j++) {
                res.add(matrix[j][right]);
            }
            right--;
            // left toward
            if (up <= down) {
                for (int i = right; i >= left; i--) {
                    res.add(matrix[down][i]);
                }
                down--;
            }
            // up toward
            if (left <= right) {
                for (int j = down; j >= up; j--) {
                    res.add(matrix[j][left]);
                }
                left++;
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int[] dirs = { 0, 1, 0, -1, 0 };
        int[][] offsets = { { 1, 0, 0, 0 }, { 0, -1, 0, 0 }, { 0, 0, -1, 0 }, { 0, 0, 0, 1 } };
        int[][] next;
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int r = 0;
        int c = 0;
        int d = 0;
        while (up <= down && left <= right) {
            d = d % 4;
            next = new int[][] { { up, left }, { up, right }, { down, right }, { down, left } };
            r = next[d][0];
            c = next[d][1];
            while (r <= down && r >= up && c <= right && c >= left) {
                res.add(matrix[r][c]);
                r += dirs[d];
                c += dirs[d + 1];
            }
            up += offsets[d][0];
            right += offsets[d][1];
            down += offsets[d][2];
            left += offsets[d][3];
            d++;
        }
        return res;
    }
}
