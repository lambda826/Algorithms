package coding.leetcode._09_dfs_backtracking;

/*

You are given an m x n binary matrix grid.
An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 on the island.

Return the maximum area of an island in grid. If there is no island, return 0.


Example 1:
    Input:
        grid =
            [[0,0,1,0,0,0,0,1,0,0,0,0,0],
             [0,0,0,0,0,0,0,1,1,1,0,0,0],
             [0,1,1,0,1,0,0,0,0,0,0,0,0],
             [0,1,0,0,1,1,0,0,1,0,1,0,0],
             [0,1,0,0,1,1,0,0,1,1,1,0,0],
             [0,0,0,0,0,0,0,0,0,0,1,0,0],
             [0,0,0,0,0,0,0,1,1,1,0,0,0],
             [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    Output:
        6
    Explanation:
        The answer is not 11, because the island must be connected 4-directionally.

Example 2:
    Input:
        grid = [[0,0,0,0,0,0,0,0]]
    Output:
        0


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.

*/

public class _0695_Max_Area_of_Island {

    private static final int[] dir = { -1, 0, 1, 0, -1 };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS {

        public int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    max = Math.max(max, DFS(grid, i, j));
                }
            }
            return max;
        }

        private int DFS(int[][] grid, int i, int j) {
            int sum = 0;
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
                sum = 1;
                grid[i][j] = 0;
                for (int d = 0; d < 4; ++d) {
                    sum += DFS(grid, i + dir[d], j + dir[d + 1]);
                }
            }
            return sum;
        }
    }
}