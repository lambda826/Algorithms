package questions._09_DFS_backtracking;

/*

Given a 2D grid consists of 0s (land) and 1s (water).
An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
Return the number of closed islands.


Example 1:
    Input:
        grid = [[1,1,1,1,1,1,1,0],
                [1,0,0,0,0,1,1,0],
                [1,0,1,0,1,1,1,0],
                [1,0,0,0,0,1,0,1],
                [1,1,1,1,1,1,1,0]]
    Output:
        2
    Explanation:
        Islands in gray are closed because they are completely surrounded by water (group of 1s).

Example 2:
    Input:
        grid = [[0,0,1,0,0],
                [0,1,0,1,0],
                [0,1,1,1,0]]
    Output:
        1

Example 3:
    Input:
        grid = [[1,1,1,1,1,1,1],
                [1,0,0,0,0,0,1],
                [1,0,1,1,1,0,1],
                [1,0,1,0,1,0,1],
                [1,0,1,1,1,0,1],
                [1,0,0,0,0,0,1],
                [1,1,1,1,1,1,1]]
    Output:
        2


Constraints:
    1 <= grid.length, grid[0].length <= 100
    0 <= grid[i][j] <=1

*/
public class _1254_Number_of_Closed_Islands {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DFS {

        private final int[] dir = { -1, 0, 1, 0, -1 };

        public int closedIsland(int[][] grid) {
            int x = grid.length;
            int y = grid[0].length;
            int count = 0;
            for (int i = 0; i < x; ++i) {
                for (int j = 0; j < y; ++j) {
                    if (grid[i][j] == 0 && isClosed(grid, i, j)) {
                        ++count;
                    }
                }
            }
            return count;
        }

        private boolean isClosed(int[][] grid, int x, int y) {
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                if (grid[x][y] == 1) {
                    return true;
                }
                grid[x][y] = 1;
                boolean isClosed = true;
                for (int i = 0; i < 4; ++i) {
                    int xx = x + dir[i];
                    int yy = y + dir[i + 1];
                    isClosed = isClosed(grid, xx, yy) && isClosed; // Ensure we can go through the whole branch
                }
                return isClosed;
            } else {
                return false;
            }
        }
    }
}