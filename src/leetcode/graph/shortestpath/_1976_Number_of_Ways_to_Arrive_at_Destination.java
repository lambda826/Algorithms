package leetcode.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
Description:
    You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections.
    The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
    You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi that takes timei minutes to travel.
    You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
    Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 10^9 + 7.


Examples:
    Example 1:
        Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
        Output: 4
        Explanation: The shortest time from 0 to 6 is 7; the four shortest paths are [0→6], [0→4→6], [0→1→2→5→6], [0→1→3→5→6].

    Example 2:
        Input: n = 2, roads = [[1,0,10]]
        Output: 1


Constraints:
    1 <= n <= 200
    n - 1 <= roads.length <= n * (n - 1) / 2
    roads[i].length == 3
    0 <= ui, vi <= n - 1
    1 <= timei <= 10^9
    ui != vi
    There is at most one road connecting any two intersections.
    You can reach any intersection from any other intersection.
*/
public class _1976_Number_of_Ways_to_Arrive_at_Destination {

    /**
     * <h2>1976. Number of Ways to Arrive at Destination — Dijkstra + Path Counting</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Count the number of shortest paths from node {@code 0} to node {@code n-1} in a connected,
     * undirected, positively weighted graph; return the count modulo {@code 1e9+7}.
     *
     * <h3>Constraints/Assumptions</h3>
     * <ul>
     *   <li>{@code n <= 200}; edge weights up to {@code 1e9} ⇒ use {@code long} for distances.</li>
     *   <li>Graph is connected; at most one edge between any pair.</li>
     *   <li>Weights are positive ⇒ Dijkstra applicable.</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * Run Dijkstra to compute shortest distances. During relaxation maintain {@code ways[v]}:
     * <ul>
     *   <li>If we find a strictly shorter distance to {@code v}, set {@code ways[v] = ways[u]}.</li>
     *   <li>If we find another path with the same shortest distance, add {@code ways[u]} to {@code ways[v]} (mod).</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Build adjacency list.</li>
     *   <li>Initialize {@code dist[*] = +∞} (as {@code Long.MAX_VALUE}), {@code dist[0] = 0}; {@code ways[0] = 1}.</li>
     *   <li>Min-heap over {@code State(node, dist)}; pop smallest; skip stale states.</li>
     *   <li>Relax neighbors with the above update rules.</li>
     *   <li>Answer is {@code ways[n-1]}.</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * With positive weights, when a node is popped from the min-heap, its distance is final.
     * Aggregating counts on relaxations thus yields the exact number of shortest paths.
     *
     * <h3>Complexity</h3>
     * Time: {@code O((n + m) log n)}; Space: {@code O(n + m)}, where {@code m = roads.length}.
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>Use {@code long} for distances to avoid overflow.</li>
     *   <li>Take modulo every time you add to {@code ways[v]}.</li>
     *   <li>Use a stale-entry check instead of a visited array.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>Small carriers as records ({@code Edge}, {@code State}); braces on all control statements.</li>
     * </ul>
     *
     * <h3>Alternatives/Variants</h3>
     * Build a DAG of shortest-path-preserving edges and count via DP/toposort; similar complexity.
     */
    static class Solution {

        private static final int MOD = 1_000_000_007;

        public int countPaths(int n, int[][] roads) {
            List<Edge>[] graph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] r : roads) {
                int u = r[0], v = r[1], w = r[2];
                graph[u].add(new Edge(v, w));
                graph[v].add(new Edge(u, w));
            }

            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE);
            dist[0] = 0L;

            int[] ways = new int[n];
            ways[0] = 1;

            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(State::dist));
            pq.offer(new State(0, 0L));

            while (!pq.isEmpty()) {
                State cur = pq.poll();
                if (cur.dist > dist[cur.node]) {
                    continue; // stale-entry
                }
                for (Edge e : graph[cur.node]) {
                    long nd = cur.dist + (long) e.w;
                    if (nd < dist[e.to]) {
                        dist[e.to] = nd;
                        ways[e.to] = ways[cur.node];
                        pq.offer(new State(e.to, nd));
                    } else if (nd == dist[e.to]) {
                        ways[e.to] = (int) ((ways[e.to] + (long) ways[cur.node]) % MOD);
                    }
                }
            }

            return ways[n - 1];
        }

        private record Edge(int to, int w) { }

        private record State(int node, long dist) { }
    }
}
