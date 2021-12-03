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
}