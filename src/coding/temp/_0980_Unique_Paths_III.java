/**
 *  @author Yunxiang He
 *  @date 01/19/2019
 */

package coding.temp;

/*

On a 2-dimensional grid, there are 4 types of squares:

1 represents the starting square.  There is exactly one starting square.
2 represents the ending square.  There is exactly one ending square.
0 represents empty squares we can walk over.
-1 represents obstacles that we cannot walk over.
Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

 

Example 1:

Input: [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
Output: 2
Explanation: We have the following two paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
Example 2:

Input: [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
Output: 4
Explanation: We have the following four paths: 
1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
Example 3:

Input: [[0,1],[2,0]]
Output: 0
Explanation: 
There is no path that walks over every empty square exactly once.
Note that the starting and ending square can be anywhere in the grid.
 

Note:

1 <= grid.length * grid[0].length <= 20

*/

public class _0980_Unique_Paths_III {

    public static void main(String[] args) {
        System.out.println(new _0980_Unique_Paths_III().uniquePathsIII(new int[][] { { 1, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 2 } }));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int count = 0;

    public int uniquePathsIII(int[][] grid) {
        int startR = -1;
        int startC = -1;
        int endR = -1;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 1) {
                    grid[i][j] = 0;
                    startR = i;
                    startC = j;
                } else if (grid[i][j] == 2) {
                    endR = i;
                }
            }
        }
        if (startR != -1 && endR != -1) {
            DFS(startR, startC, grid);
        }
        return count;
    }

    private void DFS(int r, int c, int[][] grid) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length) {
            if (grid[r][c] == 2) {
                if (goalTest(grid)) {
                    count++;
                }
            } else if (grid[r][c] == 0) {
                grid[r][c]++;
                DFS(r, c + 1, grid);
                DFS(r + 1, c, grid);
                DFS(r, c - 1, grid);
                DFS(r - 1, c, grid);
                grid[r][c]--;
            }
        }
    }

    private boolean goalTest(int[][] grid) {
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
