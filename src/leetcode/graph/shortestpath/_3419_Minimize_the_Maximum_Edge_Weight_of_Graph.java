package leetcode.graph.shortestpath;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*

You are given two integers, n and threshold, as well as a directed weighted graph of n nodes numbered from 0 to n - 1.
The graph is represented by a 2D integer array edges, where edges[i] = [Ai, Bi, Wi] indicates that there is an edge going from node Ai to node Bi with weight Wi.

You have to remove some edges from this graph (possibly none), so that it satisfies the following conditions:

Node 0 must be reachable from all other nodes.
The maximum edge weight in the resulting graph is minimized.
Each node has at most threshold outgoing edges.
Return the minimum possible value of the maximum edge weight after removing the necessary edges. If it is impossible for all conditions to be satisfied, return -1.


Example 1:
    Input: n = 5, edges = [[1,0,1],[2,0,2],[3,0,1],[4,3,1],[2,1,1]], threshold = 2
    Output: 1
    Explanation:
    Remove the edge 2 -> 0. The maximum weight among the remaining edges is 1.

Example 2:
    Input: n = 5, edges = [[0,1,1],[0,2,2],[0,3,1],[0,4,1],[1,2,1],[1,4,1]], threshold = 1
    Output: -1
    Explanation:
    It is impossible to reach node 0 from node 2.

Example 3:
    Input: n = 5, edges = [[1,2,1],[1,3,3],[1,4,5],[2,3,2],[3,4,2],[4,0,1]], threshold = 1
    Output: 2
    Explanation:
    Remove the edges 1 -> 3 and 1 -> 4. The maximum weight among the remaining edges is 2.

Example 4:
    Input: n = 5, edges = [[1,2,1],[1,3,3],[1,4,5],[2,3,2],[4,0,1]], threshold = 1
    Output: -1


Constraints:
    2 <= n <= 10^5
    1 <= threshold <= n - 1
    1 <= edges.length <= min(10^5, n * (n - 1) / 2).
    edges[i].length == 3
    0 <= Ai, Bi < n
    Ai != Bi
    1 <= Wi <= 10^6
    There may be multiple edges between a pair of nodes, but they must have unique weights.
*/
public class _3419_Minimize_the_Maximum_Edge_Weight_of_Graph {

    /**
     * LeetCode 3419 — Minimize the Maximum Edge Weight of Graph
     *
     * <p><strong>Approach</strong>
     * <br>
     * Convert the requirement “every node can reach node 0” into reachability on the
     * <em>reversed graph</em>: from node 0 we must reach all nodes. We then run a Prim-style
     * expansion using a min-heap on the reversed graph starting at node 0. Each time we pull the
     * smallest-weight edge that adds a new node to the reachable set; the maximum edge weight
     * encountered along this process is the minimal possible “max edge weight” needed to connect
     * all nodes to 0 (in the original direction).
     * <br><br>
     * Intuition: when you always extend with the smallest available edge, the first time you
     * cover every node, the largest edge used so far is the minimal feasible upper bound—any
     * smaller bound would have failed to connect some node.
     *
     * <p><strong>Why threshold does not matter here</strong>
     * <br>
     * This Prim-style expansion over the reversed graph implicitly forms a minimum in-arborescence
     * (rooted at 0). In the original graph, each non-root node has exactly one outgoing edge toward
     * its parent in that arborescence, so every node’s outdegree ≤ 1. Therefore, if
     * {@code threshold ≥ 1}, the constraint is automatically satisfied; if {@code threshold < 1}
     * and {@code n > 1}, the problem is unsatisfiable. Hence, {@code threshold} is not used.
     *
     * <p><strong>Complexity</strong>
     * <br>
     * Let {@code n} be the number of nodes and {@code m} the number of edges.
     * <ul>
     *   <li>Time: {@code O((n + m) log m)} — each edge can enter the heap at most once and heap
     *       operations are logarithmic.</li>
     *   <li>Space: {@code O(n + m)} for the graph, heap, and visited array.</li>
     * </ul>
     *
     * <p><strong>Correctness sketch</strong>
     * <br>
     * Suppose the algorithm returns {@code X} as the maximum edge used. If there were a solution
     * with a strictly smaller maximum edge weight, then while growing the reachable set using only
     * edges ≤ that smaller bound, all nodes should still become reachable—contradicting the fact
     * that our process would have added them already (since we always pick the smallest available
     * edge first). Thus {@code X} is minimal.
     */
    static class Solution {

        /**
         * Returns the minimal possible maximum edge weight so that every node can reach 0,
         * or -1 if impossible.
         *
         * @param n         number of nodes labeled [0..n-1]
         * @param edges     edges in the form [u, v, w] meaning u -> v with weight w
         * @param threshold allowed out-degree per node in the original graph (not used here; see class doc)
         * @return minimal maximum edge weight to make all nodes reach 0; -1 if not all nodes can reach 0
         */
        public int minMaxWeight(int n, int[][] edges, int threshold) {
            // Build reversed graph: rev[v] contains all predecessors (u, w) where original is u -> v
            List<Edge>[] rev = new ArrayList[n];
            for (int i = 0; i < n; ++i) rev[i] = new ArrayList<>();
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                rev[v].add(new Edge(u, w));
            }

            // Min-heap to always expand with the smallest connecting edge weight
            PriorityQueue<Candidate> pq = new PriorityQueue<>(Comparator.comparingInt(c -> c.weight));
            boolean[] visited = new boolean[n];

            // Start from node 0 with "cost" 0
            pq.offer(new Candidate(0, 0));

            int coveredCount = 0;           // how many nodes have been reached
            int maxEdgeWeightOnPath = 0;    // maximum edge weight used so far

            while (!pq.isEmpty()) {
                Candidate cur = pq.poll();
                int u = cur.node;
                if (visited[u]) continue;

                visited[u] = true;
                coveredCount++;
                if (cur.weight > maxEdgeWeightOnPath) {
                    maxEdgeWeightOnPath = cur.weight;
                }
                if (coveredCount == n) break; // early exit if all nodes are covered

                // Add all predecessors of u (in reversed graph) as candidates
                for (Edge e : rev[u]) {
                    if (!visited[e.to]) {
                        pq.offer(new Candidate(e.to, e.weight));
                    }
                }
            }

            // If all nodes are reachable from 0 in the reversed graph, return the max edge we used
            return coveredCount == n ? maxEdgeWeightOnPath : -1;
        }

        /**
         * Represents an edge in the reversed graph: from current node to neighbor with a weight.
         */
        private static final class Edge {
            final int to;
            final int weight;

            Edge(int to, int weight) { this.to = to; this.weight = weight; }
        }

        /**
         * Candidate node to be added into the reachable set with its connecting edge weight.
         */
        private static final class Candidate {
            final int node;
            final int weight;

            Candidate(int node, int weight) { this.node = node; this.weight = weight; }
        }
    }


    /**
     * LeetCode 3419 — Minimize the Maximum Edge Weight of Graph
     *
     * <p><strong>Approach (Binary Search + BFS on Reversed Graph)</strong>
     * <br>
     * Let X be the answer (the minimal possible maximum edge weight). If we keep only edges with
     * weight ≤ T and check whether <em>every</em> node can reach 0, then:
     * <ul>
     *   <li>If it’s possible for a threshold T, it’s also possible for any T' ≥ T (monotonic).</li>
     *   <li>If it’s impossible for T, it’s impossible for any T' &lt; T.</li>
     * </ul>
     * Therefore we can binary search on T over the edge weights. For each mid T, build the
     * <em>reversed graph</em> using only edges with weight ≤ T, and run a BFS/DFS from node 0 to see
     * if we can visit all nodes. The smallest feasible T is the answer.
     *
     * <p><strong>Why threshold does not matter here</strong>
     * <br>
     * The connectivity we test ensures there exists an in-arborescence to 0 using only edges ≤ T
     * (each non-root node contributes exactly one outgoing edge in the original graph). Hence,
     * every node’s outdegree ≤ 1 in that construction. If {@code threshold ≥ 1}, the constraint is
     * automatically satisfied; if {@code threshold < 1} and {@code n > 1}, the problem is infeasible.
     *
     * <p><strong>Complexity</strong>
     * <br>
     * Let {@code n} be the number of nodes and {@code m} the number of edges, and let {@code W} be
     * the range of edge weights. Each feasibility check builds a graph and runs BFS in
     * {@code O(n + m)}. Binary search performs {@code O(log W)} checks. Thus:
     * <ul>
     *   <li>Time: {@code O((n + m) log W)}</li>
     *   <li>Space: {@code O(n + m)}</li>
     * </ul>
     *
     * <p><strong>Edge cases</strong>
     * <br>
     * If {@code n == 1}, answer is 0 (no edge needed). If there are no edges and {@code n > 1},
     * it’s impossible. Optionally short-circuit {@code threshold < 1 && n > 1} to -1.
     */
    static class Solution2 {

        /**
         * Returns the minimal possible maximum edge weight so that every node can reach 0,
         * or -1 if impossible.
         *
         * @param n         number of nodes labeled [0..n-1]
         * @param edges     edges in the form [u, v, w] meaning u -> v with weight w
         * @param threshold allowed out-degree per node in the original graph (not used; see class doc)
         * @return minimal maximum edge weight to make all nodes reach 0; -1 if not possible
         */
        public int minMaxWeight(int n, int[][] edges, int threshold) {
            // Trivial/unsatisfiable shortcuts
            if (n == 1) return 0;
            if (threshold < 1) return -1; // needs at least one outgoing edge per non-root in any solution
            if (edges == null || edges.length == 0) return -1;

            // Determine the search bounds over weights
            int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;
            for (int[] e : edges) {
                int w = e[2];
                if (w < lo) lo = w;
                if (w > hi) hi = w;
            }

            int ans = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (canAllReachZero(n, edges, mid)) {
                    ans = mid;
                    hi = mid - 1; // try a smaller maximum edge
                } else {
                    lo = mid + 1; // need to allow larger edges
                }
            }
            return ans;
        }

        /**
         * Feasibility check for a given edge-weight cap T: keep only edges with weight ≤ T,
         * build the reversed graph, and verify that 0 can reach all nodes (in reversed graph).
         */
        private boolean canAllReachZero(int n, int[][] edges, int cap) {
            // Build reversed adjacency using only edges ≤ cap
            java.util.List<java.util.ArrayList<Integer>> rev = new java.util.ArrayList<>(n);
            for (int i = 0; i < n; ++i) rev.add(new java.util.ArrayList<>());
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (w <= cap) {
                    // original: u -> v ; reversed: v -> u
                    rev.get(v).add(u);
                }
            }

            // BFS from 0 on the reversed graph
            boolean[] seen = new boolean[n];
            java.util.ArrayDeque<Integer> q = new java.util.ArrayDeque<>();
            q.offer(0);
            seen[0] = true;

            int visited = 1;
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (int nei : rev.get(cur)) {
                    if (!seen[nei]) {
                        seen[nei] = true;
                        visited++;
                        q.offer(nei);
                    }
                }
            }
            return visited == n;
        }
    }

}
