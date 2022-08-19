package questions._09_dfs_backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
    class Solution_DFS {

        private final int[] dir = { -1, 0, 1, 0, -1 };
        private final char[] mark = { 'A', 'B', 'C', 'D' };

        public int numDistinctIslands(int[][] grid) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == 1) {
                        StringBuilder path = new StringBuilder("#");
                        dfs(grid, path, i, j);
                        set.add(path.toString());
                    }
                }
            }
            return set.size();
        }

        private void dfs(int[][] grid, StringBuilder path, int i, int j) {
            grid[i][j] = -1;
            for (int k = 0; k < 4; ++k) {
                int ii = i + dir[k];
                int jj = j + dir[k + 1];
                if (ii >= 0 && ii < grid.length && jj >= 0 && jj < grid[0].length && grid[ii][jj] == 1) {
                    path.append(mark[k]);
                    dfs(grid, path, ii, jj);
                }
            }
            path.append("#");
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Alternatively, record relative coordinates.
    // To record relative coordinates, we can't simply record the relative distance to (0, 0),
    //      this approach actually just treat the shape as 1D vector
    // for example:
    //      |0 1 0 0| is same as |1 0 0 1| after adjusting the coordinates by minus the distance from the first 1 to the origin.
    //      |1 0 0 0|            |0 0 0 0|
    class Solution_DFS2 {

        public int numDistinctIslands(int[][] grid) {
            Set<String> set = new HashSet<>();
            int col = grid[0].length;
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < col; ++j) {
                    if (grid[i][j] == 1) {
                        List<Integer> path = new ArrayList<>();
                        int[] minCord = { Integer.MAX_VALUE, Integer.MAX_VALUE };
                        dfs(grid, path, i, j, minCord);
                        int[] _path = new int[path.size()];
                        for (int k = 0; k < _path.length; ++k) {
                            _path[k] = (path.get(k) / col - minCord[0]) * col + (path.get(k) % col - minCord[1]);
                        }
                        set.add(Arrays.toString(_path));
                    }
                }
            }
            return set.size();
        }

        private void dfs(int[][] grid, List<Integer> path, int m, int n, int[] minCord) {
            if (m >= 0 && m < grid.length && n >= 0 && n < grid[0].length && grid[m][n] == 1) {
                grid[m][n] = 0;
                path.add(m * grid[0].length + n);
                minCord[0] = Math.min(minCord[0], m);
                minCord[1] = Math.min(minCord[1], n);
                dfs(grid, path, m + 1, n, minCord);
                dfs(grid, path, m - 1, n, minCord);
                dfs(grid, path, m, n + 1, minCord);
                dfs(grid, path, m, n - 1, minCord);
            }
        }
    }
}
