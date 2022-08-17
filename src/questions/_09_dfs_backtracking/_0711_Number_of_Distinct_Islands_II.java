package questions._09_dfs_backtracking;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
Return the number of distinct islands.


Example 1:
    Input:
        grid = [[1,1,0,0,0],
                [1,0,0,0,0],
                [0,0,0,0,1],
                [0,0,0,1,1]]
    Output:
        1
    Explanation:
        The two islands are considered the same because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.

Example 2:
    Input:
        grid = [[1,1,0,0,0],
                [1,1,0,0,0],
                [0,0,0,1,1],
                [0,0,0,1,1]]
    Output:
        1


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    grid[i][j] is either 0 or 1.

*/
public class _0711_Number_of_Distinct_Islands_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        public int numDistinctIslands2(int[][] grid) {
            int col = grid[0].length;
            Set<String> visited = new HashSet<>();
            int count = 0;
            for (int m = 0; m < grid.length; ++m) {
                for (int n = 0; n < col; ++n) {
                    if (grid[m][n] == 1) {
                        List<Integer> path = new ArrayList();
                        DFS(grid, path, m, n);
                        if (visited.add(getMaxPath(path, col))) {
                            ++count;
                        }
                    }
                }
            }
            return count;
        }

        private void DFS(int[][] grid, List<Integer> path, int m, int n) {
            if (m >= 0 && m < grid.length && n >= 0 && n < grid[0].length && grid[m][n] == 1) {
                grid[m][n] = 0;
                // We shouldn't deduct the distance from the first "1" of this connected component to the origin.
                // Because that would just treat the matrix as a vector and move the vector to the left
                // for example:
                //      |0 1 0 0| is same as |1 0 0 1| after adjusting the coordinates by minus the distance from the first 1 to the origin.
                //      |1 0 0 0|            |0 0 0 0|
                path.add(m * grid[0].length + n);
                DFS(grid, path, m + 1, n);
                DFS(grid, path, m - 1, n);
                DFS(grid, path, m, n + 1);
                DFS(grid, path, m, n - 1);
            }
        }

        private String getMaxPath(List<Integer> path, int col) {
            int[][] dir = { { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
            // For a shape of the path, we only need to record a single one from the 8 after rotation and reflection,
            //      as long as the rule remains same.
            // Here we also return the one which has maximum alphanumeric order.
            String result = "";
            int[] x = new int[path.size()];
            int[] y = new int[path.size()];
            int[] z = new int[path.size()];
            for (int[] _dir : dir) {
                for (int i = 0; i < 2; ++i) {
                    // We need to record and initialize the relative coordinates that we can subtract from the path after the alternation.
                    int xMin = Integer.MAX_VALUE;
                    int yMin = Integer.MAX_VALUE;
                    for (int j = 0; j < path.size(); ++j) {
                        int num = path.get(j);
                        x[j] = (i & 1) == 0 ? (num / col) * _dir[0] : (num % col) * _dir[0];
                        y[j] = (i & 1) == 0 ? (num % col) * _dir[1] : (num / col) * _dir[1];
                        xMin = Math.min(xMin, x[j]);
                        yMin = Math.min(yMin, y[j]);
                    }
                    for (int j = 0; j < z.length; ++j) {
                        z[j] = (x[j] - xMin) * col + (y[j] - yMin);
                    }
                    // We need to sort the outcome to stick to same rule.
                    Arrays.sort(z);
                    String zz = Arrays.toString(z);
                    // Always keep the one which has maximum alphanumeric order.
                    result = result.compareTo(zz) > 0 ? result : zz;
                }
            }
            return result;
        }
    }
}
