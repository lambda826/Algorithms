package leetcode.graph.topological.prune;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
Description:
    There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1.
    You are given an integer n and a 2D integer array edges of length n - 1,
    where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
    You are also given an array coins of size n where coins[i] can be either 0 or 1,
    where 1 indicates the presence of a coin in the vertex i.

    Initially, you choose to start at any vertex in the tree. Then, you can perform the following
    operations any number of times:

        - Collect all the coins that are at a distance of at most 2 from the current vertex, or
        - Move to any adjacent vertex in the tree.

    Find the minimum number of edges you need to go through to collect all the coins and go back
    to the initial vertex.

    Note that if you pass an edge several times, you need to count it into the answer several times.


Examples:
    Example 1
        Input: coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
        Output: 2
        Explanation: Start at vertex 2, collect the coin at vertex 0, move to vertex 3,
        collect the coin at vertex 5 then move back to vertex 2.

    Example 2
        Input: coins = [0,0,0,1,1,0,0,1],
               edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
        Output: 2
        Explanation: Start at vertex 0, collect the coins at vertices 4 and 3,
        move to vertex 2, collect the coin at vertex 7, then move back to vertex 0.


Constraints:
    n == coins.length
    1 <= n <= 3 * 10^4
    0 <= coins[i] <= 1
    edges.length == n - 1
    edges[i].length == 2
    0 <= ai, bi < n
    ai != bi
    edges represents a valid tree.
*/
public class _2603_Collect_Coins_in_a_Tree {

    /**
     * <h2>2603. Collect Coins in a Tree — Pruning / Topological Peeling</h2>
     *
     * <h3>Goal / Problem summary</h3>
     * <ul>
     *     <li>We have a tree where some nodes contain coins.</li>
     *     <li>From a node, we can collect all coins within distance ≤ 2.</li>
     *     <li>We may move along edges and must eventually return to the starting node.</li>
     *     <li>Find the minimum total number of edge traversals (each traversal counts).</li>
     * </ul>
     *
     * <h3>Constraints / Assumptions</h3>
     * <ul>
     *     <li>1 ≤ n ≤ 3 * 10^4 — tree with up to 30k nodes.</li>
     *     <li>Each coin indicator is 0 or 1.</li>
     *     <li>The graph is a valid tree (connected, acyclic).</li>
     * </ul>
     *
     * <h3>Approach</h3>
     * <ul>
     *     <li>Interpret the problem as finding the smallest subtree that must be traversed.</li>
     *     <li>Use degree-based pruning (similar to topological peeling on trees).</li>
     *     <li>Exploit the distance ≤ 2 collection rule to remove outer layers of leaves.</li>
     * </ul>
     *
     * <h3>Algorithm</h3>
     * <ol>
     *     <li>Build adjacency lists and an array of node degrees.</li>
     *     <li>First prune:
     *         <ul>
     *             <li>Push all leaf nodes with no coin into a queue.</li>
     *             <li>Repeatedly remove them, decrement neighbors' degrees, and push
     *             new leaf-no-coin nodes.</li>
     *             <li>After this, only nodes that either have coins or lie on paths
     *             between coin nodes remain active.</li>
     *         </ul>
     *     </li>
     *     <li>Second prune (two layers):
     *         <ul>
     *             <li>Collect all remaining leaves (degree == 1) into a queue.</li>
     *             <li>Run two rounds:
     *                 <ol>
     *                     <li>Round 1: remove current leaves, decrement neighbors'
     *                     degrees; enqueue new leaves.</li>
     *                     <li>Round 2: remove the new leaves, but do not shrink neighbor
     *                     degrees (so we only peel exactly two layers).</li>
     *                 </ol>
     *             </li>
     *         </ul>
     *     </li>
     *     <li>Count remaining edges where both endpoints still have degree > 0.</li>
     *     <li>The result is 2 * (number of such edges), since we traverse each twice
     *     (out and back).</li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * <ul>
     *     <li>After the first prune, every remaining node is either:
     *         <ul>
     *             <li>a coin node, or</li>
     *             <li>on some path connecting coin nodes.</li>
     *         </ul>
     *     </li>
     *     <li>Because coins can be collected at distance ≤ 2, any coin sitting in the
     *     outer two layers of leaves can be collected without ever stepping into those
     *     leaf nodes themselves.</li>
     *     <li>Thus, peeling away two layers of leaves preserves the set of collectible
     *     coins while shrinking the subtree we actually have to walk.</li>
     * </ul>
     *
     * <h3>Complexity</h3>
     * <ul>
     *     <li>Time: O(n), since each node and edge is processed a constant number of times.</li>
     *     <li>Space: O(n) for adjacency lists, degree array, and queue.</li>
     * </ul>
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *     <li>n ≤ 2: even if there are coins, we can always collect them with 0 cost
     *     (stand there and collect within radius 2), so return 0.</li>
     *     <li>Tree with no coins: first prune removes all nodes; remaining edges = 0 → answer 0.</li>
     *     <li>Be careful to not shrink neighbors' degrees in the second peeling round,
     *     otherwise we would remove more than two layers.</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *     <li>Use degrees[i] == 0 as the "inactive / removed" flag.</li>
     *     <li>Queue-based BFS for pruning is simple and linear.</li>
     * </ul>
     *
     * <h3>Alternatives / Variants</h3>
     * <ul>
     *     <li>Rooted-tree DP could also compute minimal traversal cost but is more complex.</li>
     *     <li>This degree-pruning method is simpler and naturally fits trees.</li>
     * </ul>
     */
    static class Solution {
        public int collectTheCoins(int[] coins, int[][] edges) {
            int n = coins.length;

            // For n == 1 or n == 2, we can always stand somewhere and collect everything
            // within distance 2 without walking any edge.
            if (n <= 2) {
                return 0;
            }

            List<Integer>[] graph = new List[n];
            int[] degrees = new int[n];

            buildGraph(edges, graph, degrees);

            Queue<Integer> queue = new ArrayDeque<>();

            // Step 1: prune leaf nodes with no coins.
            for (int i = 0; i < n; i++) {
                if (degrees[i] == 1 && coins[i] == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                degrees[curr] = 0;

                for (int next : graph[curr]) {
                    if (degrees[next] == 0) {
                        continue;
                    }
                    degrees[next]--;
                    if (degrees[next] == 1 && coins[next] == 0) {
                        queue.offer(next);
                    }
                }
            }

            // Step 2: prune the remaining tree's outer two layers.
            // Reinitialize the queue with all current leaves.
            for (int i = 0; i < n; i++) {
                if (degrees[i] == 1) {
                    queue.offer(i);
                }
            }

            for (int round = 0; round < 2; round++) {
                int size = queue.size();

                while (size-- > 0) {
                    int curr = queue.poll();
                    degrees[curr] = 0;

                    // Only in the first round do we shrink neighbors' degrees and
                    // enqueue freshly formed leaves for the second round.
                    if (round == 0) {
                        for (int next : graph[curr]) {
                            if (degrees[next] == 0) {
                                continue;
                            }
                            degrees[next]--;
                            if (degrees[next] == 1) {
                                queue.offer(next);
                            }
                        }
                    }
                }
            }

            // Step 3: count remaining edges where both endpoints are still active.
            int remainingEdges = 0;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                if (degrees[u] > 0 && degrees[v] > 0) {
                    remainingEdges++;
                }
            }

            return remainingEdges * 2;
        }

        private void buildGraph(int[][] edges, List<Integer>[] graph, int[] degrees) {
            for (int[] edge : edges) {
                if (graph[edge[0]] == null) {
                    graph[edge[0]] = new ArrayList<>();
                }
                graph[edge[0]].add(edge[1]);
                if (graph[edge[1]] == null) {
                    graph[edge[1]] = new ArrayList<>();
                }
                graph[edge[1]].add(edge[0]);
                degrees[edge[0]]++;
                degrees[edge[1]]++;
            }
        }
    }
}
