/**
 *  @author Yunxiang He
 *  @date 01/22/2018
 */

package coding.temp;

import java.util.LinkedList;
import java.util.Queue;

/*

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.


Example 1:
    11110
    11010
    11000
    00000
    Answer: 1

Example 2:
    11000
    11000
    00100
    00011
    Answer: 3

*/

public class _0200_Number_of_Islands {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // DFS
    private int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public int numIslands(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    DFS(grid, i, j);
                }
            }
        }
        return count;
    }

    private void DFS(char[][] grid, int i, int j) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = 0;
            for (int[] d : dirs) {
                DFS(grid, i + d[0], j + d[1]);
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // BFS
    Queue<int[]> que = new LinkedList<>();

    public int numIslands2(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    BFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void BFS(char[][] grid, int i, int j) {
        que.add(new int[] { i, j });
        int nextI;
        int nextJ;
        while (!que.isEmpty()) {
            int[] curr = que.remove();
            for (int[] dir : dirs) {
                nextI = curr[0] + dir[0];
                nextJ = curr[1] + dir[1];
                if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[0].length && grid[nextI][nextJ] == '1') {
                    grid[nextI][nextJ] = '0';
                    que.add(new int[] { nextI, nextJ });
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // union-find
    public int numIslands3(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length;
        int col = grid[0].length;
        int sz = row * col;
        // init
        int[] root = new int[sz];
        int[] size = new int[sz];
        for (int i = 0; i < sz; ++i) {
            root[i] = i;
            size[i] = 1;
        }
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (grid[i][j] == '0') {
                    --sz;
                } else {
                    int nextI = i + 1;
                    int nextJ = j + 1;
                    // union right
                    if (nextI < row && grid[nextI][j] == '1' && union(root, size, i * col + j, nextI * col + j)) {
                        --sz;
                    }
                    // union down
                    if (nextJ < col && grid[i][nextJ] == '1' && union(root, size, i * col + j, i * col + nextJ)) {
                        --sz;
                    }
                }

            }
        }
        return sz;
    }

    private int find(int[] root, int index) {
        if (root[index] == index) {
            return index;
        }
        return root[index] = find(root, root[index]);
    }

    private boolean union(int[] root, int[] size, int index1, int index2) {
        int r1 = find(root, index1);
        int r2 = find(root, index2);
        if (r1 != r2) {
            if (size[r1] < size[r2]) {
                size[r2] += size[r1];
                root[r1] = r2;
            } else {
                size[r1] += size[r2];
                root[r2] = r1;
            }
            return true;
        }
        return false;
    }
}