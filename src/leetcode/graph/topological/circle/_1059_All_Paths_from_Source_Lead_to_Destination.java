package leetcode.graph.topological.circle;

import java.util.ArrayList;
import java.util.List;

/*
Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi,
and two nodes source and destination of this graph,
determine whether or not all paths starting from source eventually, end at destination, that is:

At least one path exists from the source node to the destination node
If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
The number of possible paths from source to destination is a finite number.
Return true if and only if all roads from source lead to destination.


Example 1:
    Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
    Output: false
    Explanation: It is possible to reach and get stuck on both node 1 and node 2.

Example 2:
    Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
    Output: false
    Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.

Example 3:
    Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
    Output: true


Constraints:
    1 <= n <= 10^4
    0 <= edges.length <= 10^4
    edges.length == 2
    0 <= ai, bi <= n - 1
    0 <= source <= n - 1
    0 <= destination <= n - 1
    The given graph may have self-loops and parallel edges.
*/
public class _1059_All_Paths_from_Source_Lead_to_Destination {

    /**
     * LeetCode 1059. All Paths from Source Lead to Destination
     * 解决方案类
     */
    class Solution {

        private int destination;

        public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
            // 将目标节点存储在成员变量中，方便 dfs 函数访问
            this.destination = destination;

            // 1. 构建邻接表来表示图
            List<Integer>[] graph = new List[n];
            for (int i = 0; i < n; ++i) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
            }

            // 2. 初始化访问状态数组
            // 0: UNVISITED (未访问)
            // 1: VISITING (正在访问，即在当前递归路径上)
            // 2: VISITED (已访问，即所有从该节点出发的路径都被验证是安全的)
            int[] visited = new int[n];

            // 3. 从源节点开始进行深度优先搜索
            return dfs(graph, source, visited);
        }

        /**
         * 深度优先搜索 (DFS) 辅助函数。
         *
         * @param graph   图的邻接表表示。
         * @param u       当前正在访问的节点。
         * @param visited 记录节点访问状态的数组。
         * @return 如果从节点 u 出发的所有路径都有效，则返回 true。
         */
        private boolean dfs(List<Integer>[] graph, int u, int[] visited) {
            // --- 状态检查 ---
            // 状态为 2 (VISITED): 表示这个节点之前已经被完整探索过，
            // 并且所有从它出发的路径都能安全到达 destination。这是一种记忆化，避免重复计算。
            if (visited[u] == 2) {
                return true;
            }
            // 状态为 1 (VISITING): 表示在当前这条探索路径上又遇到了这个节点，
            // 这意味着我们发现了一个环。有环的路径无法到达终点，所以是无效路径。
            if (visited[u] == 1) {
                return false;
            }

            // --- 核心逻辑 ---
            // 将当前节点标记为 1 (VISITING)，表示它已进入当前递归栈。
            visited[u] = 1;

            // 递归地探索当前节点的所有邻居
            for (int v : graph[u]) {
                // 如果从任何一个邻居 v 出发的路径是无效的...
                if (!dfs(graph, v, visited)) {
                    // ...那么从当前节点 u 出发的路径也必然是无效的，立即返回 false。
                    return false;
                }
            }

            // --- 回溯与标记 ---
            // 如果成功探索完所有邻居（所有子路径都有效），我们将当前节点标记为 2 (VISITED)。
            // 这意味着节点 u 是一个“安全”的节点。
            visited[u] = 2;

            // 最后，确认当前节点 u 本身是否满足终止条件。
            // 如果 u 是一个叶子节点 (没有出边)，它必须是 destination 才算有效。
            // 如果 u 不是叶子节点，那么 যেহেতু它的所有子路径都已验证有效，所以它也是有效的。
            return !graph[u].isEmpty() || destination == u;
        }
    }
}
