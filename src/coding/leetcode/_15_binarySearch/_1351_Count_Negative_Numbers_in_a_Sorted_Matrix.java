package coding.leetcode._15_binarySearch;


/*

Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.


Example 1:
    Input:
        grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
    Output:
        8
    Explanation:
        There are 8 negatives number in the matrix.

Example 2:
    Input:
        grid = [[3,2],[1,0]]
    Output:
        0

Example 3:
    Input:
        grid = [[1,-1],[-1,-1]]
    Output:
        3

Example 4:
    Input:
        grid = [[-1]]
    Output:
        1


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 100
    -100 <= grid[i][j] <= 100

*/

public class _1351_Count_Negative_Numbers_in_a_Sorted_Matrix {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countNegatives(int[][] grid) {
        int num = 0;
        int lo = 0;
        int hi = grid[0].length;
        for (int[] row : grid) {
            int temp = binarySearch(row, lo, hi);
            if (temp == 0 && row[0] >= 0) {
                num += row.length - 1;
            } else {
                num += (row.length - temp);
            }
            // Making use of the condition "non-increasing order both row-wise and column-wise"
            hi = temp;
        }
        return num;
    }

    private int binarySearch(int[] row, int lo, int hi) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (row[mid] >= 0) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
