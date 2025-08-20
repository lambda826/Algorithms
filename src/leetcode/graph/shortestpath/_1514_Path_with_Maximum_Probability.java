package leetcode.graph.shortestpath;

/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list
where edges[i] = [a, b] denotes an undirected edge between nodes a and b, and succProb[i] is the
probability of successfully traversing that edge.

Given two nodes start and end, find a path from start to end that maximizes the product of success
probabilities along the path, and return that maximum probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from
the correct answer by at most 1e-5.


Examples:
    Example 1:
        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
        Output: 0.25000
        Explanation: There are two paths from start to end: one has probability 0.2 and the other has 0.5 * 0.5 = 0.25.

    Example 2:
        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
        Output: 0.30000

    Example 3:
        Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
        Output: 0.00000
        Explanation: There is no path between 0 and 2.


Constraints:
    2 <= n <= 10^4
    0 <= start, end < n
    start != end
    0 <= a, b < n
    a != b
    0 <= succProb.length == edges.length <= 2*10^4
    0 <= succProb[i] <= 1
    There is at most one edge between any two nodes.
*/
public class _1514_Path_with_Maximum_Probability {

    /**
     * <h2>1514. Path with Maximum Probability — Dijkstra (max-heap)</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Find a path from {@code start} to {@code end} in an undirected graph that maximizes
     * the product of edge success probabilities; return {@code 0.0} if unreachable.
     *
     * <h3>Constraints/Assumptions</h3>
     * <ul>
     *   <li>{@code n <= 1e4}, {@code m = edges.length <= 2e4}</li>
     *   <li>Nodes are {@code 0..n-1}; graph is undirected</li>
     *   <li>{@code 0 <= succProb[i] <= 1}; multiple edges (if present) are handled</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Dijkstra variant maximizing a multiplicative objective:
     * treat the cumulative probability as the key and use a max-heap (priority queue).
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Build adjacency list: for each {@code i}, add {@code u->v} and {@code v->u} with probability {@code p}.</li>
     *   <li>Initialize {@code prob[start] = 1.0}, others {@code 0.0}; push {@code (start, 1.0)} into a max-heap.</li>
     *   <li>While heap not empty:
     *     <ol type="a">
     *       <li>Pop {@code cur}; if {@code cur.prob < prob[cur.node]} (stale), continue.</li>
     *       <li>If {@code cur.node == end}, return {@code cur.prob}.</li>
     *       <li>For each edge {@code (cur.node -> v, p)}:
     *           {@code newProb = cur.prob * p}; if {@code newProb > prob[v]}, update and push {@code (v, newProb)}.</li>
     *     </ol>
     *   </li>
     *   <li>If the heap empties, return {@code 0.0}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * The heap orders states by current best cumulative probability; the stale-entry check ensures we
     * expand only optimal states; therefore the first time {@code end} is popped, its probability is optimal.
     *
     * <h3>Complexity</h3>
     * Time {@code O((n + m) log n)}; Space {@code O(n + m)}.
     *
     * <h3>Edge Cases & Pitfalls</h3>
     * <ul>
     *   <li>No path → return {@code 0.0}.</li>
     *   <li>Do not base the heap comparator on a mutable external array (e.g., {@code prob[]}).</li>
     *   <li>Prefer stale-entry check over a {@code visited} set for correctness and performance.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Use {@code record Adj(int to, double p)} and {@code record State(int node, double prob)}.</li>
     *   <li>Max-heap: {@code Comparator.comparingDouble((State s) -> s.prob).reversed()}.</li>
     *   <li>Use {@code Double.compare} when needed; avoid subtracting doubles for ordering.</li>
     * </ul>
     *
     * <h3>Alternatives/Variants</h3>
     * <ul>
     *   <li>Transform with logs: maximize product ↔ minimize sum of {@code -log p} with classic Dijkstra.</li>
     *   <li>Bellman–Ford (slower, {@code O(n*m)}), useful for validating logic on small graphs.</li>
     * </ul>
     */
    static class Solution {

        private record Adj(int to, double p) { }

        private record State(int node, double prob) { }

        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            java.util.List<Adj>[] g = new java.util.ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new java.util.ArrayList<>();
            }
            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0], v = edges[i][1];
                double p = succProb[i];
                g[u].add(new Adj(v, p));
                g[v].add(new Adj(u, p));
            }

            double[] prob = new double[n];
            prob[start] = 1.0d;

            java.util.PriorityQueue<State> pq =
                    new java.util.PriorityQueue<>(java.util.Comparator.<State>comparingDouble(s -> s.prob).reversed());
            pq.offer(new State(start, 1.0d));

            while (!pq.isEmpty()) {
                State cur = pq.poll();

                // 过期条目检查（stale-entry check）
                if (cur.prob < prob[cur.node]) {
                    continue;
                }
                if (cur.node == end) {
                    return cur.prob; // 第一次弹出 end 即为最优
                }
                for (Adj adj : g[cur.node]) {
                    double newProb = cur.prob * adj.p;
                    if (newProb > prob[adj.to]) {
                        prob[adj.to] = newProb;
                        pq.offer(new State(adj.to, newProb));
                    }
                }
            }
            return 0.0d; // 不可达
        }

    }
}