/**
 *  @author Yunxiang He
 *  @date Jan 10, 2018 1:24:44 AM
 */

package coding.leetcode.temp;

import java.util.Arrays;

/*

You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).


Example 1:
    Given input matrix = 
    [
      [1,2,3],
      [4,5,6],
      [7,8,9]
    ],
    
    rotate the input matrix in-place such that it becomes:
    [
      [7,4,1],
      [8,5,2],
      [9,6,3]
    ]

Example 2:
    Given input matrix =
    [
      [ 5, 1, 9,11],
      [ 2, 4, 8,10],
      [13, 3, 6, 7],
      [15,14,12,16]
    ], 
    
    rotate the input matrix in-place such that it becomes:
    [
      [15,13, 2, 5],
      [14, 3, 4, 1],
      [12, 6, 8, 9],
      [16, 7,10,11]
    ]
    

Note:
    You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
    DO NOT allocate another 2D matrix and do the rotation.

*/

public class _0048_Rotate_Image {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void rotate(int[][] matrix) {
        int i = 0;
        int j = matrix.length - 1;
        while (i < j) {
            for (int k = 0; i + k < j; k++) {
                int temp = matrix[i][i + k];
                matrix[i][i + k] = matrix[j - k][i];
                matrix[j - k][i] = matrix[j][j - k];
                matrix[j][j - k] = matrix[i + k][j];
                matrix[i + k][j] = temp;
            }
            i++;
            j--;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void rotate2(int[][] matrix) {
        // Rotate diagonally
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // Rotate vertically
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
    }

    private void print(int[][] matrix) {
        for (int[] m : matrix) {
            System.out.println(Arrays.toString(m));
        }
        System.out.println("----------------------------------");
    }

    public static void main(String[] args) {
        _0048_Rotate_Image test = new _0048_Rotate_Image();
        int[][] matrix = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
        test.rotate2(matrix);
    }
}
