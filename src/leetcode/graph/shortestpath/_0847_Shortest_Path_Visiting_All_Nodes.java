package leetcode.graph.shortestpath;

import java.util.ArrayDeque;
import java.util.Queue;

/*
Description:
    You are given an undirected connected graph with n nodes labeled 0..n-1. The graph is represented
    by an adjacency list int[][] graph, where graph[i] is the list of nodes adjacent to i.

    Return the length of the shortest path that visits every node at least once. You may start at any node,
    and you may revisit nodes and edges any number of times. The path length is the number of edges traversed.


Examples:
    Example 1:
        Input: graph = [[1,2,3],[0],[0],[0]]
        Output: 4
        Explanation: One optimal route is 1 → 0 → 2 → 0 → 3 (or any permutation around 0) with 4 edges.

    Example 2:
        Input: graph = [[1,2,3],[0,2,4],[0,1,3,4],[0,2],[1,2]]
        Output: 4
        Explanation: One optimal route is 0 → 1 → 4 → 2 → 3 with 4 edges.


Constraints:
    1 <= n <= 12
    0 <= graph[i].length <= n - 1
    0 <= graph[i][j] < n
    All the edges are undirected: if u is in graph[v], then v is in graph[u].
    The graph is connected.
*/
public class _0847_Shortest_Path_Visiting_All_Nodes {

    /**
     * <h2>847. Shortest Path Visiting All Nodes — BFS on (node, visitedMask)</h2>
     *
     * <h3>Goal/Problem summary</h3>
     * Find the minimum number of edges to traverse so that every node is visited at least once
     * in a connected undirected graph. You may start anywhere and revisit nodes/edges.
     *
     * <h3>Approach</h3>
     * Model each state as {@code (node, mask)} where {@code mask} is an n-bit bitmask marking visited nodes.
     * Perform a **multi-source BFS**: initialize the queue with all n starting states {@code (i, 1<<i)} with distance 0.
     * Since every edge has unit cost, BFS guarantees the first time we reach any state is with minimal steps.
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>Let {@code target = (1<<n) - 1} be the mask where all nodes are visited.</li>
     *   <li>Prepare a queue; push all {@code (i, 1<<i, 0)} and mark {@code seen[1<<i][i] = true}.</li>
     *   <li>While queue not empty:
     *     <ol type="a">
     *       <li>Pop {@code (u, mask, dist)}. If {@code mask == target}, return {@code dist}.</li>
     *       <li>For each neighbor {@code v} of {@code u}, compute {@code nextMask = mask | (1<<v)}.
     *           If {@code !seen[nextMask][v]}, mark seen and push {@code (v, nextMask, dist+1)}.</li>
     *     </ol>
     *   </li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * BFS explores states by non-decreasing distance. Because edges are unweighted, the first time we dequeue
     * a state reaching {@code mask == target}, we have the shortest number of steps visiting all nodes.
     *
     * <h3>Complexity</h3>
     * States: at most {@code n * 2^n} (n <= 12). Each transition scans neighbors.
     * Time {@code O(n * 2^n + m * 2^n)} (often written {@code O(2^n * (n + m))}); Space {@code O(n * 2^n)}.
     *
     * <h3>Edge Cases & Pitfalls</h3>
     * <ul>
     *   <li>Initialize BFS from all nodes (multi-source) to avoid trying every start individually.</li>
     *   <li>Use a {@code boolean seen[1<<n][n]} to avoid re-enqueuing the same state.</li>
     *   <li>Do not try TSP DP with distances unless you precompute all-pairs shortest paths; BFS is simpler and optimal here.</li>
     * </ul>
     *
     * <h3>Alternatives/Variants</h3>
     * Held–Karp style DP: precompute all-pairs shortest path lengths, then do DP over subsets with last node.
     * Same asymptotic but more code; BFS is the canonical solution.
     */
    static class Solution {
        public int shortestPathLength(int[][] graph) {
            int n = graph.length;
            boolean[][] visited = new boolean[n][1 << n];
            int target = (1 << n) - 1;
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < n; ++i) {
                visited[i][1 << i] = true;
                queue.offer(new int[] { i, 1 << i });
            }
            int step = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    int[] curr = queue.poll();
                    if (curr[1] == target) {
                        return step;
                    }
                    for (int next : graph[curr[0]]) {
                        int nextPath = curr[1] | (1 << next);
                        if (!visited[next][nextPath]) {
                            visited[next][nextPath] = true;
                            queue.offer(new int[] { next, nextPath });
                        }
                    }
                }
                step++;
            }
            return -1;
        }
    }

}
