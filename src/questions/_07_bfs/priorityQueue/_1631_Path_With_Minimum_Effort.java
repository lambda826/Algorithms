package questions._07_bfs.priorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*

You are a hiker preparing for an upcoming hike.
You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.


Example 1:
    Input:
        heights = [[1,2,2],
                   [3,8,2],
                   [5,3,5]]
    Output:
        2
    Explanation:
        The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
        This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.

Example 2:
    Input:
        heights = [[1,2,3],
                   [3,8,4],
                   [5,3,5]]
    Output:
        1
    Explanation:
        The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].

Example 3:
    Input:
        heights = [[1,2,1,1,1],
                   [1,2,1,2,1],
                   [1,2,1,2,1],
                   [1,2,1,2,1],
                   [1,1,1,2,1]]
    Output:
        0
    Explanation:
        This route does not require any effort.


Constraints:
    rows == heights.length
    columns == heights[i].length
    1 <= rows, columns <= 100
    1 <= heights[i][j] <= 10^6

*/
public class _1631_Path_With_Minimum_Effort {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Shortest path: Dijkstra's variation
    class Solution_BFS {
        private final int[] dir = { -1, 0, 1, 0, -1 };

        public int minimumEffortPath(int[][] heights) {
            int x = heights.length;
            int y = heights[0].length;
            int[][] maxSoFar = new int[x][y];
            for (int[] arr : maxSoFar) {
                Arrays.fill(arr, Integer.MAX_VALUE);
            }
            maxSoFar[0][0] = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> maxSoFar[a[0]][a[1]]));
            pq.add(new int[] { 0, 0 });
            while (!pq.isEmpty()) {
                int[] current = pq.remove();
                for (int i = 0; i < 4; ++i) {
                    int xx = current[0] + dir[i];
                    int yy = current[1] + dir[i + 1];
                    if (xx >= 0 && yy >= 0 && xx < x && yy < y) {
                        int dis = Math.max(maxSoFar[current[0]][current[1]], Math.abs(heights[current[0]][current[1]] - heights[xx][yy]));
                        if (dis < maxSoFar[xx][yy]) {
                            maxSoFar[xx][yy] = dis;
                            pq.add(new int[] { xx, yy });
                        }
                    }
                }
            }
            return maxSoFar[x - 1][y - 1];
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            int[][] efforts = new int[(row - 1) * col + row * (col - 1)][];
            int count = 0;
            for (int r = 0; r < row; ++r) {
                for (int c = 0; c < col; ++c) {
                    int a1 = r * col + c;
                    int a2 = a1 + 1;
                    int a3 = (r + 1) * col + c;
                    if (r + 1 < row) {
                        efforts[count++] = new int[] { Math.abs(heights[r][c] - heights[r + 1][c]), a1, a3 };
                    }
                    if (c + 1 < col) {
                        efforts[count++] = new int[] { Math.abs(heights[r][c] - heights[r][c + 1]), a1, a2 };
                    }
                }
            }
            Arrays.sort(efforts, Comparator.comparingInt(a -> a[0]));

            int[] parent = new int[row * col];
            int[] height = new int[row * col];
            for (int i = 0; i < parent.length; ++i) {
                parent[i] = i;
            }
            int min = 0;
            for (int[] effort : efforts) {
                union(parent, height, effort[1], effort[2]);
                min = effort[0];
                if (find(parent, 0) == find(parent, parent.length - 1)) {
                    break;
                }
            }
            return min;
        }

        private int find(int[] parent, int i) {
            return parent[i] == i ? i : (parent[i] = find(parent, parent[i]));
        }

        private void union(int[] parent, int[] height, int i, int j) {
            int ii = find(parent, i);
            int jj = find(parent, j);
            if (ii != jj) {
                if (height[ii] < height[jj]) {
                    parent[ii] = jj;
                } else {
                    parent[jj] = ii;
                    if (height[ii] == height[jj]) {
                        ++height[ii];
                    }
                }
            }
        }
    }
}
