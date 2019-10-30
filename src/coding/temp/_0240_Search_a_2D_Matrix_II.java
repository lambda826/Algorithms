/**
 *  @author Yunxiang He
 *  @date Jan 17, 2018 5:58:04 PM
 */

package coding.temp;

/*

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.

For example,
    Consider the following matrix:
    [
      [1,   4,  7, 11, 15],
      [2,   5,  8, 12, 19],
      [3,   6,  9, 16, 22],
      [10, 13, 14, 17, 24],
      [18, 21, 23, 26, 30]
    ]
Given target = 5, return true.
Given target = 20, return false.

 */

public class _0240_Search_a_2D_Matrix_II {

    public static void main(String[] args) {
        _0240_Search_a_2D_Matrix_II test = new _0240_Search_a_2D_Matrix_II();
        test.searchMatrix_BinarySearch(new int[][] { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 }, { 18, 21, 23, 26, 30 } }, 26);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean searchMatrix_Scan(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int row = m;
        int col = 0;
        // Row 从最大, Col 从最小
        while (row >= 0 && col <= n) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean searchMatrix_BinarySearch(int[][] matrix, int target) {
        if (matrix.length == 0) {
            return false;
        }
        int end = matrix.length - 1;
        for (int col = 0; col < matrix[0].length; col++) {
            int lo = 0;
            int hi = end;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (matrix[mid][col] == target) {
                    return true;
                } else if (matrix[mid][col] < target) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            // previous search result can be used on next;
            end = lo < matrix.length ? lo : matrix.length - 1;
        }
        return false;
    }

}
