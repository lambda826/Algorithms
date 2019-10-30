/**
 *  @author Yunxiang He
 *  @date 02/02/2018
 */

package coding.temp;

/*

Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)


Example 1:
    [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,1,1,0,1,0,0,0,0,0,0,0,0],
     [0,1,0,0,1,1,0,0,1,0,1,0,0],
     [0,1,0,0,1,1,0,0,1,1,1,0,0],
     [0,0,0,0,0,0,0,0,0,0,1,0,0],
     [0,0,0,0,0,0,0,1,1,1,0,0,0],
     [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Given the above grid, return 6. 
    Note the answer is not 11, because the island must be connected 4-directionally.

Example 2:
    [[0,0,0,0,0,0,0,0]]
    Given the above grid, return 0.

Note: The length of each dimension in the given grid does not exceed 50.

*/

public class _0695_Max_Area_of_Island {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxAreaOfIsland(int[][] grid) {
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0, }, { 0, -1 } };
        int max = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int r = 0; r < row; ++r) {
            for (int c = 0; c < col; ++c) {
                max = Math.max(max, DFS(grid, dirs, r, c));
            }
        }
        return max;
    }

    private int DFS(int[][] grid, int[][] dirs, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1) {
            grid[r][c] = 0;
            int area = 1;
            for (int[] dir : dirs) {
                area += DFS(grid, dirs, r + dir[0], c + dir[1]);
            }
            return area;
        } else {
            return 0;
        }
    }
}
