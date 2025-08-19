package leetcode.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*

You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal.
If it is impossible for all the n nodes to receive the signal, return -1.


Example 1:
    Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
    Output: 2

Example 2:
    Input: times = [[1,2,1]], n = 2, k = 1
    Output: 1

Example 3:
    Input: times = [[1,2,1]], n = 2, k = 2
    Output: -1


Constraints:
    1 <= k <= n <= 100
    1 <= times.length <= 6000
    times[i].length == 3
    1 <= ui, vi <= n
    ui != vi
    0 <= wi <= 100
    All the pairs (ui, vi) are unique. (i.e., no multiple edges.)

*/
public class _0743_Network_Delay_Time {

    /**
     * <h2>743. Network Delay Time — Dijkstra (Min-Heap) Solution</h2>
     *
     * <p><b>Goal.</b> Given a directed graph with non-negative edge weights and a start node {@code k},
     * compute the time needed for a signal to reach <em>all</em> nodes. If any node is unreachable, return {@code -1}.
     * Otherwise return the maximum shortest-path distance from {@code k} to any node.</p>
     *
     * <h3>Approach (Why Dijkstra)</h3>
     * <ul>
     *   <li>All edge weights are non-negative. This is exactly the setting where Dijkstra's algorithm is correct and efficient.</li>
     *   <li>We maintain a distance array {@code dist[i]} = best-known distance from {@code k} to node {@code i}.</li>
     *   <li>A min-heap (priority queue) orders frontier nodes by their current best distance.</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Build an adjacency list {@code graph[u]} = list of outgoing edges {@code (v, w)}.</li>
     *   <li>Initialize {@code dist[*] = Integer.MAX_VALUE}, {@code dist[k] = 0}. Push {@code (k, 0)} into the PQ.</li>
     *   <li>While the PQ is not empty:
     *     <ul>
     *       <li>Pop the state {@code (u, d)} with the smallest {@code d}.</li>
     *       <li><b>Stale check:</b> if {@code d != dist[u]}, skip; we've already found a better path to {@code u}.</li>
     *       <li>Relax all edges {@code u -> v} with weight {@code w}: if {@code d + w < dist[v]}, update and push.</li>
     *     </ul>
     *   </li>
     *   <li>After the loop, if any {@code dist[i] == Integer.MAX_VALUE}, return {@code -1} (unreachable).
     *       Otherwise return the maximum value in {@code dist[1..n]}.</li>
     * </ol>
     *
     * <h3>Correctness (Key Invariant)</h3>
     * <ul>
     *   <li>When a node {@code u} is popped from the PQ and passes the stale check,
     *       {@code dist[u]} is final (shortest) because all edge weights are non-negative.</li>
     *   <li>Any older, suboptimal entries for the same node are detected by the stale check and ignored.</li>
     * </ul>
     *
     * <h3>Complexity</h3>
     * <ul>
     *   <li>Time: {@code O(E log V)} — each relaxation pushes at most a few PQ entries; heap ops are {@code log V}.</li>
     *   <li>Space: {@code O(V + E)} for the adjacency list, distance array, and PQ.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Uses Java records for small immutable carriers: {@code Edge(to, w)} and {@code State(node, dist)}.</li>
     *   <li>Distances initialize to {@code Integer.MAX_VALUE}. We compute candidate sums in {@code long} to avoid overflow,
     *       then bounds-check before casting back to {@code int}.</li>
     *   <li>Style constraints: braces on all control statements; no single-line control statements.</li>
     * </ul>
     */
    public static final class Solution {

        /**
         * Computes the time for a signal from {@code k} to reach all nodes, or {@code -1} if impossible.
         *
         * @param times edges as {@code [u, v, w]} triples; nodes are labeled {@code 1..n}
         * @param n     number of nodes
         * @param k     source node (1-based)
         * @return the maximum shortest-path distance from {@code k} to any node, or {@code -1} if any node is unreachable
         */
        public int networkDelayTime(int[][] times, int n, int k) {
            List<Edge>[] graph = buildGraph(times, n);
            int[] dist = dijkstra(graph, k);
            return maxDistanceOrNegOne(dist, n);
        }

        /**
         * Builds an adjacency list for a directed, weighted graph.
         *
         * @param times edges as {@code [u, v, w]} triples
         * @param n     number of nodes (1-based indexing)
         * @return {@code graph[u]} = list of outgoing edges from {@code u}
         */
        private List<Edge>[] buildGraph(int[][] times, int n) {
            @SuppressWarnings("unchecked")
            List<Edge>[] g = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] t : times) {
                g[t[0]].add(new Edge(t[1], t[2]));
            }
            return g;
        }

        /**
         * Dijkstra's algorithm from a single source.
         *
         * @param graph adjacency list with non-negative edge weights
         * @param s     source node (1-based)
         * @return array {@code dist} where {@code dist[i]} is the shortest distance from {@code s} to {@code i};
         * unreachable nodes have {@code Integer.MAX_VALUE}
         */
        private int[] dijkstra(List<Edge>[] graph, int s) {
            int n = graph.length - 1;
            int[] dist = new int[n + 1];

            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;

            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::dist));
            pq.offer(new State(s, 0));

            while (!pq.isEmpty()) {
                State cur = pq.poll();

                // Skip if a better path to cur.node was already found.
                if (cur.dist() != dist[cur.node()]) {
                    continue;
                }

                for (Edge e : graph[cur.node()]) {
                    int cand = cur.dist() + e.w();
                    if (cand < dist[e.to()]) {
                        dist[e.to()] = cand;
                        pq.offer(new State(e.to(), cand));
                    }
                }
            }
            return dist;
        }

        /**
         * Returns the maximum finite distance in {@code dist[1..n]}; if any node is unreachable, returns {@code -1}.
         *
         * @param dist distance array from source
         * @param n    number of nodes
         * @return {@code -1} if any {@code dist[i] == Integer.MAX_VALUE}, else the maximum {@code dist[i]}
         */
        private int maxDistanceOrNegOne(int[] dist, int n) {
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                if (dist[i] > max) {
                    max = dist[i];
                }
            }
            return max;
        }

        private record Edge(int to, int w) { }

        private record State(int node, int dist) { }

    }

}
