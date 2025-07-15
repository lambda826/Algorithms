package questions.leetcode.graph.topological;

import java.util.*;

/*
There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1.
You are given an integer n and a 2D integer array edges of length n - 1,
where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
You are also given an array coins of size n where coins[i] can be either 0 or 1, where 1 indicates the presence of a coin in the vertex i.

Initially, you choose to start at any vertex in the tree. Then, you can perform the following operations any number of times:

Collect all the coins that are at a distance of at most 2 from the current vertex, or
Move to any adjacent vertex in the tree.
Find the minimum number of edges you need to go through to collect all the coins and go back to the initial vertex.

Note that if you pass an edge several times, you need to count it into the answer several times.


Example 1:
    Input: coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
    Output: 2
    Explanation: Start at vertex 2, collect the coin at vertex 0, move to vertex 3, collect the coin at vertex 5 then move back to vertex 2.

Example 2:
    Input: coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
    Output: 2
    Explanation: Start at vertex 0, collect the coins at vertices 4 and 3, move to vertex 2,  collect the coin at vertex 7, then move back to vertex 0.


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

    class Solution {
        public int collectTheCoins(int[] coins, int[][] edges) {
            int n = coins.length;
            if (n <= 1) {
                return 0;
            }
            int[] degree = new int[n];
            List<Integer>[] graph = buildGraph(edges, degree);
            Queue<Integer> leaves = new ArrayDeque<>();

            boolean[] active = new boolean[n];
            Arrays.fill(active, true);

            // --- First Pruning: Removing leaves with no coins ---
            for (int i = 0; i < n; ++i) {
                if (degree[i] == 1 && coins[i] == 0) {
                    leaves.offer(i);
                    active[i] = false;
                }
            }

            while (!leaves.isEmpty()) {
                int u = leaves.poll();
                for (int v : graph[u]) {
                    if (active[v]) {
                        degree[v]--;
                        if (degree[v] == 1 && coins[v] == 0) {
                            leaves.offer(v);
                            active[v] = false;
                        }
                    }
                }
            }

            // --- Second Pruning: Removing 2 layers of leaves ---
            Queue<Integer> secondPruningQueue = new ArrayDeque<>();
            for (int i = 0; i < n; ++i) {
                if (active[i] && degree[i] == 1) {
                    secondPruningQueue.offer(i);
                }
            }

            // Perform this pruning for two "steps" or "layers".
            // Each step removes one layer of current leaves.
            for (int k = 0; k < 2; k++) {
                int currentLevelSize = secondPruningQueue.size();
                if (currentLevelSize == 0) {
                    break;
                }

                for (int i = 0; i < currentLevelSize; i++) {
                    int u = secondPruningQueue.poll();
                    if (!active[u]) {
                        continue;
                    }
                    active[u] = false;
                    for (int v : graph[u]) {
                        if (active[v]) {
                            degree[v]--;
                            if (degree[v] == 1) {
                                secondPruningQueue.offer(v);
                            }
                        }
                    }
                }
            }

            // Correct final calculation: Count the edges that remain between active nodes.
            int remainingEdges = 0;
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                if (active[u] && active[v]) {
                    remainingEdges++;
                }
            }
            return remainingEdges * 2;
        }

        private List<Integer>[] buildGraph(int[][] edges, int[] degree) {
            List<Integer>[] graph = new List[degree.length];
            for (int i = 0; i < degree.length; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                degree[edge[0]]++;
                graph[edge[1]].add(edge[0]);
                degree[edge[1]]++;
            }
            return graph;
        }
    }
}
