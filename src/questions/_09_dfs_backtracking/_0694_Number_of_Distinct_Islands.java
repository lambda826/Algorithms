package questions._09_dfs_backtracking;

import java.util.HashSet;
import java.util.Set;

/*

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
You may assume all four edges of the grid are surrounded by water.

An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Return the number of distinct islands.


Example 1:
    Input:
        grid = [[1,1,0,0,0],
                [1,1,0,0,0],
                [0,0,0,1,1],
                [0,0,0,1,1]]
    Output:
        1

Example 2:
    Input:
        grid = [[1,1,0,1,1],
                [1,0,0,0,0],
                [0,0,0,0,1],
                [1,1,0,1,1]]
    Output:
        3


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.

*/
public class _0694_Number_of_Distinct_Islands {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        private final int[] dir = { -1, 0, 1, 0, -1 };
        private final char[] mark = { 'A', 'B', 'C', 'D' };

        public int numDistinctIslands(int[][] grid) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == 1) {
                        StringBuilder path = new StringBuilder("#");
                        DFS(grid, path, i, j);
                        set.add(path.toString());
                    }
                }
            }
            return set.size();
        }

        private void DFS(int[][] grid, StringBuilder path, int i, int j) {
            grid[i][j] = -1;
            for (int k = 0; k < 4; ++k) {
                int ii = i + dir[k];
                int jj = j + dir[k + 1];
                if (ii >= 0 && ii < grid.length && jj >= 0 && jj < grid[0].length && grid[ii][jj] == 1) {
                    path.append(mark[k]);
                    DFS(grid, path, ii, jj);
                }
            }
            path.append("#");
        }
    }
}
