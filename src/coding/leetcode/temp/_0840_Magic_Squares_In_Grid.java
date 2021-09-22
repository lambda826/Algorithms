/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-28 05:39
 */

package coding.leetcode.temp;

import java.util.HashSet;
import java.util.Set;

/*

A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.

Given an grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).
 

Example 1:

Input: [[4,3,8,4],
        [9,5,1,9],
        [2,7,6,2]]
Output: 1
Explanation: 
The following subgrid is a 3 x 3 magic square:
438
951
276
while this one is not:
384
519
762
In total, there is only one magic square inside the given grid.

Note:
1 <= grid.length <= 10
1 <= grid[0].length <= 10
0 <= grid[i][j] <= 15

*/

public class _0840_Magic_Squares_In_Grid {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numMagicSquaresInside(int[][] grid) {
        if (grid.length < 3 || grid[0].length < 3) {
            return 0;
        }
        int count = 0;
        int row = 1;
        int col = 1;

        while (row < grid.length - 1) {
            col = 1;
            while (col < grid.length - 1) {
                if (grid[row][col] == 5 && isValid(grid, row, col)) {
                    count++;
                    col++;
                }
                col++;
            }
            row++;
        }
        return count;
    }

    private boolean isValid(int[][] grid, int row, int col) {
        Set<Integer> set = new HashSet<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                int num = grid[row + i][col + j];
                if (num > 9 || num < 0 || !set.add(num)) {
                    return false;
                }
            }
        }
        if (grid[row - 1][col - 1] + grid[row - 1][col] + grid[row - 1][col + 1] != 15 || grid[row][col - 1] + grid[row][col + 1] != 10 || grid[row + 1][col - 1] + grid[row + 1][col] + grid[row + 1][col + 1] != 15
                || grid[row - 1][col - 1] + grid[row][col - 1] + grid[row + 1][col - 1] != 15 || grid[row - 1][col] + grid[row + 1][col] != 10 || grid[row - 1][col + 1] + grid[row][col + 1] + grid[row + 1][col + 1] != 15
                || grid[row - 1][col - 1] + grid[row + 1][col + 1] != 10 || grid[row - 1][col + 1] + grid[row + 1][col - 1] != 10) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _0840_Magic_Squares_In_Grid test = new _0840_Magic_Squares_In_Grid();
        System.out.println(test.numMagicSquaresInside(new int[][] { { 4, 3, 8, 4 }, { 9, 5, 1, 9 }, { 2, 7, 6, 2 } }));
    }
}
