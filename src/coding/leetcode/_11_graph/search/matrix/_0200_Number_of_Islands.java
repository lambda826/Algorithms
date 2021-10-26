package coding.leetcode._11_graph.search.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.


Example 1:
    Input:
        grid = [
          ["1","1","1","1","0"],
          ["1","1","0","1","0"],
          ["1","1","0","0","0"],
          ["0","0","0","0","0"]
        ]
    Output:
        1

Example 2:
    Input:
        grid = [
          ["1","1","0","0","0"],
          ["1","1","0","0","0"],
          ["0","0","1","0","0"],
          ["0","0","0","1","1"]
        ]
    Output:
        3


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 300
    grid[i][j] is '0' or '1'.

*/

public class _0200_Number_of_Islands {

    int[] dir = {-1, 0, 1, 0, -1};

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numIslands_DFS(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    ++num;
                    DFS(grid, i, j, dir);
                }
            }
        }
        return num;
    }

    private void DFS(char[][] grid, int i, int j, int[] dir) {
        if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = '0';
            for (int k = 0; k < 4; ++k) {
                DFS(grid, i + dir[k], j + dir[k + 1], dir);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numIslands_BFS(char[][] grid) {
        int[] dir = {-1, 0, 1, 0, -1};
        int num = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    ++num;
                    queue.offer(i * grid[0].length + j);
                    bfs(grid, dir, queue);
                }
            }
        }
        return num;
    }

    private void bfs(char[][] grid, int[] dir, Queue<Integer> queue) {
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            int currX = curr / grid[0].length;
            int currY = curr % grid[0].length;
            grid[currX][currY] = '0';
            for (int i = 0; i < 4; ++i) {
                int nextX = currX + dir[i];
                int nextY = currY + dir[i + 1];
                if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && grid[nextX][nextY] == '1') {
                    grid[nextX][nextY] = '0';
                    queue.offer(nextX * grid[0].length + nextY);
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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