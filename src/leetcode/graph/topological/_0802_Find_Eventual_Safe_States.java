package leetcode.graph.topological;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*

There is a directed graph of n nodes with each node labeled from 0 to n - 1.
The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.



Example 1:
    Illustration of graph
    Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
    Output: [2,4,5,6]
    Explanation: The given graph is shown above.
    Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
    Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.

Example 2:
    Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
    Output: [4]
    Explanation:
    Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


Constraints:
    n == graph.length
    1 <= n <= 10^4
    0 <= graph[i].length <= n
    0 <= graph[i][j] <= n - 1
    graph[i] is sorted in a strictly increasing order.
    The graph may contain self-loops.
    The number of edges in the graph will be in the range [1, 4 * 10^4].

*/
public class _0802_Find_Eventual_Safe_States {

    class Solution {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer>[] inbound = new List[graph.length];
            int[] degree = new int[graph.length];
            for (int i = 0; i < graph.length; ++i) {
                if (graph[i] != null && graph[i].length > 0) {
                    degree[i] = graph[i].length;
                    for (int next : graph[i]) {
                        if (inbound[next] == null) {
                            inbound[next] = new ArrayList<>();
                        }
                        inbound[next].add(i);
                    }
                } else {
                    queue.offer(i);
                }
            }

            boolean[] safe = new boolean[graph.length];
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                safe[curr] = true;
                if (inbound[curr] != null) {
                    for (int in : inbound[curr]) {
                        if (--degree[in] == 0) {
                            queue.offer(in);
                        }
                    }
                }
            }
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < safe.length; ++i) {
                if (safe[i]) {
                    list.add(i);
                }
            }
            return list;
        }
    }

    // Find circle: Any node that is connected to the circle should be excluded
    class Solution2 {
        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> res = new ArrayList<>();
            int[] visited = new int[graph.length];
            for (int i = 0; i < graph.length; ++i) {
                if (dfs(graph, visited, i)) {
                    res.add(i);
                }
            }
            return res;
        }

        private boolean dfs(int[][] graph, int[] visited, int curr) {
            if (visited[curr] == 0) {
                visited[curr] = 1;
                for (int next : graph[curr]) {
                    if (!dfs(graph, visited, next)) {
                        return false;
                    }
                }
                visited[curr] = 2;
                return true;
            } else return visited[curr] != 1;
        }
    }
}
