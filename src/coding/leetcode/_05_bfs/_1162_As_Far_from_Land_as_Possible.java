package coding.leetcode._05_bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/*

Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.


Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.


Constraints:
n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

 */
public class _1162_As_Far_from_Land_as_Possible {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] visited = new int[m][n];
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    queue.offer(i * n + j);
                    visited[i][j] = -1;
                }
            }
        }
        if (queue.size() == 0 || queue.size() == m * n) {
            return -1;
        }
        int max = 0;
        int[] dir = { -1, 0, 1, 0, -1 };
        while (!queue.isEmpty()) {
            int size = queue.size();
            ++max;
            while (size-- > 0) {
                int k = queue.poll();
                int i = k / n;
                int j = k % n;
                for (int d = 0; d < 4; ++d) {
                    int _i = i + dir[d];
                    int _j = j + dir[d + 1];
                    if (_i >= 0 && _i < m && _j >= 0 && _j < n && visited[_i][_j] != -1) {
                        visited[_i][_j] = -1;
                        queue.offer(_i * n + _j);
                    }
                }
            }
        }
        return max - 1; // Need to subtract 1 for the last interation.
    }
}
