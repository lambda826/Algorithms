package leetcode.graph.shortestpath;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell (0, 0),
and you want to travel to the bottom-right cell (rows - 1, columns - 1). You can move up, down, left, or right.

A path’s effort is defined as the maximum absolute difference in heights between two consecutive cells along the path.
Return the minimum effort required to travel from the top-left cell to the bottom-right cell.


Examples:
    Example 1:
        Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
        Output: 2
        Explanation: The path [1 → 3 → 5 → 3 → 5] has maximum step difference 2; a more direct path yields 3.

    Example 2:
        Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
        Output: 1

    Example 3:
        Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
        Output: 0
        Explanation: A flat corridor exists from start to end.


Constraints:
    m == heights.length, n == heights[0].length
    1 <= m, n <= 100
    1 <= heights[r][c] <= 1_000_000
*/
public class _1631_Path_With_Minimum_Effort {

    /**
     * <h2>1631. Path With Minimum Effort — Dijkstra (min-heap)</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Find a path from (0,0) to (m-1,n-1) that minimizes the path effort, where effort is the maximum
     * absolute height difference between adjacent cells along the path.
     *
     * <h3>Constraints/Assumptions</h3>
     * <ul>
     *   <li>{@code 1 <= m, n <= 100}, {@code heights[r][c] in [1, 1_000_000]}</li>
     *   <li>Moves allowed in four directions (no diagonals)</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Dijkstra on the grid where the “distance” (key) is the minimal possible effort to reach each cell.
     * Relaxation uses {@code newEffort = max(cur.effort, |h[cur]-h[next]|)}; the priority queue is a min-heap on effort.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Let {@code dist[r][c]} be the minimal effort to reach (r,c); init to {@code INF} except {@code dist[0][0]=0}.</li>
     *   <li>Push {@code (0,0,0)} into a min-heap keyed by {@code effort}.</li>
     *   <li>While heap not empty:
     *     <ol type="a">
     *       <li>Pop {@code cur}; if {@code cur.effort > dist[cur.r][cur.c]} (stale), continue.</li>
     *       <li>If {@code cur} is the target, return {@code cur.effort}.</li>
     *       <li>For each 4-neighbor in-bounds, compute {@code step = |h[cur]-h[nx]|}, {@code ne = max(cur.effort, step)};
     *           if {@code ne < dist[nx]}, update and push.</li>
     *     </ol>
     *   </li>
     *   <li>Return {@code dist[m-1][n-1]}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * The heap orders states by the best-known minimal effort; with the stale-entry check, the first time we pop a cell,
     * we have its optimal effort. Thus the first time the target is popped, its effort is optimal.
     *
     * <h3>Complexity</h3>
     * Time {@code O(m*n*log(m*n))}; Space {@code O(m*n)}.
     *
     * <h3>Edge Cases & Pitfalls</h3>
     * <ul>
     *   <li>Single cell grid → answer is 0.</li>
     *   <li>Use {@code max(curEffort, stepDiff)} for relaxation (not sum).</li>
     *   <li>Prefer stale-entry check; no {@code visited} set needed.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Use {@code record State(int r, int c, int effort)} as the PQ element.</li>
     *   <li>Make {@code State} implement {@code Comparable<State>} so no imports are needed for a comparator.</li>
     *   <li>All control statements use braces.</li>
     * </ul>
     *
     * <h3>Alternatives/Variants</h3>
     * <ul>
     *   <li>Binary search on the answer + BFS/DFS feasibility check.</li>
     *   <li>Minimum spanning tree approach with Kruskal/Union-Find (included below as {@code Solution_UF}).</li>
     * </ul>
     */
    static class Solution {

        public int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;

            int[][] dist = new int[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            dist[0][0] = 0;

            PriorityQueue<State> pq = new PriorityQueue<>();
            pq.offer(new State(0, 0, 0));

            int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

            while (!pq.isEmpty()) {
                State cur = pq.poll();

                // stale-entry check
                if (cur.effort > dist[cur.r][cur.c]) {
                    continue;
                }
                if (cur.r == m - 1 && cur.c == n - 1) {
                    return cur.effort;
                }

                for (int[] d : dirs) {
                    int nr = cur.r + d[0];
                    int nc = cur.c + d[1];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }
                    int step = Math.abs(heights[cur.r][cur.c] - heights[nr][nc]);
                    int ne = Math.max(cur.effort, step);
                    if (ne < dist[nr][nc]) {
                        dist[nr][nc] = ne;
                        pq.offer(new State(nr, nc, ne));
                    }
                }
            }
            return 0; // fallback
        }

        private record State(int r, int c, int effort) implements Comparable<State> {
            @Override
            public int compareTo(State o) {
                return Integer.compare(this.effort, o.effort);
            }
        }
    }

    // Optional alternative: Kruskal + Union-Find (MST of grid edges by effort)
    static class Solution_UF {

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;

            int edgesCount = (row - 1) * col + row * (col - 1);
            int[][] edges = new int[edgesCount][3];
            int idx = 0;

            for (int r = 0; r < row; r++) {
                for (int c = 0; c < col; c++) {
                    int id = r * col + c;
                    if (r + 1 < row) {
                        edges[idx++] = new int[] { Math.abs(heights[r][c] - heights[r + 1][c]), id, id + col };
                    }
                    if (c + 1 < col) {
                        edges[idx++] = new int[] { Math.abs(heights[r][c] - heights[r][c + 1]), id, id + 1 };
                    }
                }
            }

            Arrays.sort(edges, Comparator.comparingInt(a -> a[0]));

            int total = row * col;
            int[] parent = new int[total];
            int[] rank = new int[total];
            for (int i = 0; i < total; i++) {
                parent[i] = i;
            }

            int start = 0, target = total - 1;
            for (int[] e : edges) {
                union(parent, rank, e[1], e[2]);
                if (find(parent, start) == find(parent, target)) {
                    return e[0];
                }
            }
            return 0;
        }

        private int find(int[] parent, int x) {
            if (parent[x] != x) {
                parent[x] = find(parent, parent[x]);
            }
            return parent[x];
        }

        private void union(int[] parent, int[] rank, int a, int b) {
            int ra = find(parent, a);
            int rb = find(parent, b);
            if (ra == rb) {
                return;
            }
            if (rank[ra] < rank[rb]) {
                parent[ra] = rb;
            } else if (rank[ra] > rank[rb]) {
                parent[rb] = ra;
            } else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }
}
