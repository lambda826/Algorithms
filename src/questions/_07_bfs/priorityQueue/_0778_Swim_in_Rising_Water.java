package questions._07_bfs.priorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;


/*

You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
The rain starts to fall. At time t, the depth of the water everywhere is t.
You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t.
You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).


Example 1:
    Input:
        grid = [[0,2],
                [1,3]]
    Output:
        3
    Explanation:
        At time 0, you are in grid location (0, 0).
        You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
        You cannot reach point (1, 1) until time 3.
        When the depth of water is 3, we can swim anywhere inside the grid.

Example 2:
    Input:
        grid = [[0,1,2,3,4],
                [24,23,22,21,5],
                [12,13,14,15,16],
                [11,17,18,19,20],
                [10,9,8,7,6]]
    Output:
        16
    Explanation:
        The final route is shown.
        We need to wait until time 16 so that (0, 0) and (4, 4) are connected.


Constraints:
    n == grid.length
    n == grid[i].length
    1 <= n <= 50
    0 <= grid[i][j] < n^2
    Each value grid[i][j] is unique.

*/
public class _0778_Swim_in_Rising_Water {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BFS {
        public int swimInWater(int[][] grid) {
            int[] dir = { -1, 0, 1, 0, -1 };
            int n = grid.length;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(v -> grid[v / n][v % n]));
            boolean[][] visited = new boolean[n][n];
            pq.offer(0);
            visited[0][0] = true;
            int max = grid[0][0];
            while (!pq.isEmpty()) {
                int next = pq.poll();
                int mm = next / n;
                int nn = next % n;
                max = Math.max(max, grid[mm][nn]);
                if (mm == n - 1 && nn == n - 1) {
                    return max;
                }
                for (int i = 0; i < 4; ++i) {
                    int mmm = mm + dir[i];
                    int nnn = nn + dir[i + 1];
                    if (mmm >= 0 && mmm < n && nnn >= 0 && nnn < n && !visited[mmm][nnn]) {
                        visited[mmm][nnn] = true;
                        pq.offer(mmm * n + nnn);
                    }
                }
            }
            return max;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_UF {

        public int swimInWater(int[][] grid) {
            int n = grid.length;
            int[] dir = { -1, 0, 1, 0, -1 };
            int[] parent = new int[n * n];
            int[] nextMin = new int[n * n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    int k = i * n + j;
                    parent[k] = k;
                    nextMin[grid[i][j]] = k;
                }
            }

            int max = 0;
            while (max < n * n) {
                int i = nextMin[max] / n;
                int j = nextMin[max] % n;
                for (int k = 0; k < 4; ++k) {
                    int ii = i + dir[k];
                    int jj = j + dir[k + 1];
                    if (ii >= 0 && ii < n && jj >= 0 && jj < n && grid[ii][jj] < max) {
                        union(parent, i * n + j, ii * n + jj);
                    }
                }
                if (isConnected(parent)) {
                    break;
                }
                ++max;
            }
            return max;
        }

        private int find(int[] parent, int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent, parent[i]);
        }

        private void union(int[] parent, int i, int j) {
            int ii = find(parent, i);
            int jj = find(parent, j);
            if (ii != jj) {
                parent[ii] = jj;
            }
        }

        private boolean isConnected(int[] parent) {
            return find(parent, 0) == find(parent, parent.length - 1);
        }
    }

}
