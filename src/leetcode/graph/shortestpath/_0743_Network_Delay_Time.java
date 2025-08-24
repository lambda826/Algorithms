package leetcode.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
Description:
    You are given a directed, weighted graph with n nodes labeled 1..n. Each edge is represented by
    times[i] = [u, v, w], meaning a directed edge from node u to node v with travel time w.
    Starting from node k, send a signal to all nodes. Return the time it takes for all nodes to receive
    the signal; if any node is unreachable, return -1.


Examples:
    Example 1:
        Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
        Output: 2
        Explanation: From 2→1 takes 1, 2→3 takes 1, and the fastest to 4 is 2→3→4 with total time 2.

    Example 2:
        Input: times = [[1,2,1]], n = 2, k = 1
        Output: 1

    Example 3:
        Input: times = [[1,2,1]], n = 2, k = 2
        Output: -1
        Explanation: There is no path between 2 and 1.


Constraints:
    1 <= n <= 100
    1 <= times.length <= 6000
    times[i] = [u, v, w] with 1 <= u, v <= n, u != v, and 1 <= w <= 100
    1 <= k <= n
*/
public class _0743_Network_Delay_Time {

    /**
     * <h2>743. Network Delay Time — Dijkstra (min-heap)</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Given a directed weighted graph and a start node {@code k}, find the minimum time for the
     * signal to reach all nodes; return {@code -1} if any node is unreachable.
     *
     * <h3>Constraints/Assumptions</h3>
     * <ul>
     *   <li>{@code 1 <= n <= 100}, {@code m = times.length <= 6000}</li>
     *   <li>Nodes labeled {@code 1..n}; edge weights are positive integers</li>
     *   <li>Multiple edges are naturally handled by relaxation (the faster one wins)</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Classic Dijkstra with a min-heap over non-negative edge weights.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Build adjacency list {@code g[1..n]} with directed edges {@code u -> (v, w)}.</li>
     *   <li>Initialize {@code dist[i] = Integer.MAX_VALUE}, set {@code dist[k] = 0}.</li>
     *   <li>Push {@code (k, 0)} into a min-heap keyed by current distance.</li>
     *   <li>While heap not empty:
     *     <ol type="a">
     *       <li>Pop {@code cur}; if {@code cur.dist > dist[cur.node]} (stale), continue.</li>
     *       <li>For each outgoing edge {@code (cur.node -> v, w)}:
     *           if {@code cur.dist + w < dist[v]}, update {@code dist[v]} and push {@code (v, dist[v])}.</li>
     *     </ol>
     *   </li>
     *   <li>Return {@code max(dist[1..n])}; if any {@code dist[i] == Integer.MAX_VALUE}, return {@code -1}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * With non-negative weights, when a node is popped with the minimal tentative distance, that distance is final.
     * The stale-entry check ensures only optimal states are expanded.
     *
     * <h3>Complexity</h3>
     * Time {@code O((n + m) log n)}; Space {@code O(n + m)}.
     *
     * <h3>Edge Cases & Pitfalls</h3>
     * <ul>
     *   <li>Disconnected graph → return {@code -1}.</li>
     *   <li>Use {@code Integer.MAX_VALUE} as {@code INF}; guard the relaxation to avoid adding to {@code INF}.</li>
     *   <li>Do not base heap order on a mutable external array; carry the key inside the state.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Use {@code record Edge(int to, int w)} and {@code record State(int node, int dist)}.</li>
     *   <li>Min-heap comparator: {@code Comparator.comparingInt((State s) -> s.dist)}.</li>
     *   <li>All control statements use braces; short names with normal imports (IDE auto-import is fine).</li>
     * </ul>
     *
     * <h3>Alternatives/Variants</h3>
     * <ul>
     *   <li>Bellman–Ford ({@code O(n*m)}), useful for validation but slower.</li>
     * </ul>
     */
    static class Solution {

        private record Edge(int to, int w) { }

        private record State(int node, int dist) { }

        public int networkDelayTime(int[][] times, int n, int k) {
            // Build graph (1..n)
            List<Edge>[] g = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                g[i] = new ArrayList<>();
            }
            for (int[] t : times) {
                g[t[0]].add(new Edge(t[1], t[2]));
            }

            // Distances
            int[] dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[k] = 0;

            // Min-heap by current best distance
            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(State::dist));
            pq.offer(new State(k, 0));

            while (!pq.isEmpty()) {
                State curr = pq.poll();
                // Stale-entry check
                if (curr.dist > dist[curr.node]) {
                    continue;
                }
                for (Edge e : g[curr.node]) {
                    if (dist[curr.node] != Integer.MAX_VALUE) {
                        int newDist = curr.dist + e.w;
                        if (newDist < dist[e.to]) {
                            dist[e.to] = newDist;
                            pq.offer(new State(e.to, newDist));
                        }
                    }
                }
            }

            // Compute the answer: max distance among reachable nodes
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    return -1;
                }
                max = Math.max(max, dist[i]);
            }
            return max;
        }

    }
}
