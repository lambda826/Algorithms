package questions._11_graph.bipartition;

import java.util.ArrayDeque;
import java.util.Queue;

/*

There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
More formally, for each v in graph[u], there is an undirected edge between node u and node v.
The graph has the following properties:
    There are no self-edges (graph[u] does not contain u).
    There are no parallel edges (graph[u] does not contain duplicate values).
    If v is in graph[u], then u is in graph[v] (the graph is undirected).
    The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.

A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.


Example 1:
    Input:
        [[1,3], [0,2], [1,3], [0,2]]
    Output:
        true
    Explanation:
        The graph looks like this:
        0----1
        |    |
        |    |
        3----2
        We can divide the vertices into two groups: {0, 2} and {1, 3}.

Example 2:
    Input:
        [[1,2,3], [0,2], [0,1,3], [0,2]]
    Output:
        false
    Explanation:
        The graph looks like this:
        0----1
        | \  |
        |  \ |
        3----2
        We cannot find a way to divide the set of nodes into two independent subsets.
 

Constraints:
    graph.length == n
    1 <= n <= 100
    0 <= graph[u].length < n
    0 <= graph[u][i] <= n - 1
    graph[u] does not contain u.
    All the values of graph[u] are unique.
    If graph[u] contains v, then graph[v] contains u.

*/
public class _0785_Is_Graph_Bipartite {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Use an array to denote the color of each node;
    // 2. BFS and when enqueue the neighbour nodes, color them to the opposite color of current node;
    // 2.1. If any neighbour cannot be colored, return false;
    public static class Solution_BFS {

        public boolean isBipartite(int[][] graph) {
            Queue<Integer> queue = new ArrayDeque<>();
            int[] color = new int[graph.length];
            for (int i = 0; i < graph.length; ++i) {
                if (color[i] == 0) {
                    queue.offer(i);
                    color[i] = 1;
                    while (!queue.isEmpty()) {
                        int curr = queue.poll();
                        for (int nei : graph[curr]) {
                            if (color[nei] == 0) {
                                queue.offer(nei);
                                color[nei] = -color[curr];
                            } else if (color[nei] == color[curr]) {
                                return false;
                            }
                        }
                    }
                }
            }
            return true;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Similar idea with BFS approach
    // 1. Use an array to denote the color of each node;
    // 2. Color every neighbour to the opposite color of current node.
    class Solution_DFS {

        public boolean isBipartite(int[][] graph) {
            int[] color = new int[graph.length];
            for (int i = 0; i < graph.length; ++i) {
                if (color[i] == 0 && !dfs(graph, color, i, 1)) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(int[][] graph, int[] color, int n, int c) {
            color[n] = c;
            for (int nei : graph[n]) {
                if (color[n] == color[nei]) {
                    return false;
                }
                if (color[nei] == 0 && !dfs(graph, color, nei, -color[n])) {
                    return false;
                }
            }
            return true;
        }
    }
}
