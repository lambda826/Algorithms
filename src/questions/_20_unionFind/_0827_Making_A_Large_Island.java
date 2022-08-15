package questions._20_unionFind;

import java.util.HashSet;
import java.util.Set;

/*

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.


Example 1:
    Input:
        grid = [[1,0],
                [0,1]]
    Output:
        3
    Explanation:
        Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:
    Input:
        grid = [[1,1],
                [1,0]]
    Output:
        4
    Explanation:
        Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:
    Input:
        grid = [[1,1],
                [1,1]]
    Output:
        4
    Explanation:
        Can't change any 0 to 1, only one island with area = 4.


Constraints:
    n == grid.length
    n == grid[i].length
    1 <= n <= 500
    grid[i][j] is either 0 or 1.

*/
public class _0827_Making_A_Large_Island {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution {

        public int largestIsland(int[][] grid) {
            int n = grid.length;
            int[] parent = new int[n * n];
            int[] weight = new int[n * n];
            int[] height = new int[n * n];

            for (int i = 0; i < n * n; ++i) {
                parent[i] = i;
                weight[i] = 1;
            }

            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 1) {
                        int ii = i + 1;
                        int jj = j + 1;
                        // Union right
                        if (jj < n && grid[i][jj] == 1) {
                            union(i * n + j, i * n + jj, parent, weight, height);
                        }
                        // Union down
                        if (ii < n && grid[ii][j] == 1) {
                            union(i * n + j, ii * n + j, parent, weight, height);
                        }
                    }
                }
            }

            // Flip 0 to 1 to test the max count
            int[] dir = { -1, 0, 1, 0, -1 };
            int max = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == 0) {
                        int temp = 1;
                        Set<Integer> visited = new HashSet<>();
                        for (int k = 0; k < 4; ++k) {
                            int ii = i + dir[k];
                            int jj = j + dir[k + 1];
                            if (ii >= 0 && ii < n && jj >= 0 && jj < n && grid[ii][jj] == 1) {
                                // We should calculate kk after ii and jj pass the check.
                                int kk = find(ii * n + jj, parent);
                                if (visited.add(kk)) {
                                    temp += weight[kk];
                                }
                            }
                        }
                        max = Math.max(max, temp);
                    }
                }
            }
            // This is for the edge case that all cells are 1.
            return Math.max(max, weight[find(0, parent)]);
        }

        private int find(int i, int[] parent) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i], parent);
        }

        private void union(int m, int n, int[] parent, int[] size, int[] height) {
            int mm = find(m, parent);
            int nn = find(n, parent);
            if (mm != nn) {
                if (height[mm] < height[nn]) {
                    parent[mm] = nn;
                    size[nn] += size[mm];
                } else {
                    parent[nn] = mm;
                    size[mm] += size[nn];
                    if (height[mm] == height[nn]) {
                        ++height[mm];
                    }
                }
            }
        }
    }
}
