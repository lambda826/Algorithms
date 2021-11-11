/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:54:25 PM
 */

package questions.temp;

/*

Given a grid where each entry is only 0 or 1, find the number of corner rectangles.

A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. 
Note that only the corners need to have the value 1. 
Also, all four 1s used must be distinct.


Example 1:
Input: grid = 
[[1, 0, 0, 1, 0],
 [0, 0, 1, 0, 1],
 [0, 0, 0, 1, 0],
 [1, 0, 1, 0, 1]]
Output: 1
Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].

Example 2:
Input: grid = 
[[1, 1, 1],
 [1, 1, 1],
 [1, 1, 1]]
Output: 9
Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.

Example 3:
Input: grid = 
[[1, 1, 1, 1]]
Output: 0
Explanation: Rectangles must have four distinct corners.


Note:
The number of rows and columns of grid will each be in the range [1, 200].
Each grid[i][j] will be either 0 or 1.
The number of 1s in the grid will be at most 6000.

*/

public class _0750_Number_Of_Corner_Rectangles {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Record vertical pairs
    // For each additional row, how many more rectangles are added?
    public int countCornerRectangles_DP(int[][] grid) {
        if (grid.length <= 1 || grid[0].length <= 1) {
            return 0;
        }
        int count = 0;
        int[][] dp = new int[grid[0].length][grid[0].length];
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // find a vertex first
                if (grid[row][col] == 1) {
                    for (int c = col + 1; c < grid[0].length; c++) {
                        // if there are other verteces, form a segment
                        if (grid[row][c] == 1) {
                            count += dp[col][c]++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
