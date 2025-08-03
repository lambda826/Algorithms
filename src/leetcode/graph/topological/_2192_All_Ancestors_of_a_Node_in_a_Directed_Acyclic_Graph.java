package leetcode.graph.topological;

import java.util.*;

/*

You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes are numbered from 0 to n - 1 (inclusive).
You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a unidirectional edge from fromi to toi in the graph.
Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
A node u is an ancestor of another node v if u can reach v via a set of edges.


Example 1:
    Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
    Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
    Explanation:
        The above diagram represents the input graph.
        - Nodes 0, 1, and 2 do not have any ancestors.
        - Node 3 has two ancestors 0 and 1.
        - Node 4 has two ancestors 0 and 2.
        - Node 5 has three ancestors 0, 1, and 3.
        - Node 6 has five ancestors 0, 1, 2, 3, and 4.
        - Node 7 has four ancestors 0, 1, 2, and 3.

Example 2:
    Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
    Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
    Explanation:
        The above diagram represents the input graph.
        - Node 0 does not have any ancestor.
        - Node 1 has one ancestor 0.
        - Node 2 has two ancestors 0 and 1.
        - Node 3 has three ancestors 0, 1, and 2.
        - Node 4 has four ancestors 0, 1, 2, and 3.


Constraints:
    1 <= n <= 1000
    0 <= edges.length <= min(2000, n * (n - 1) / 2)
    edges[i].length == 2
    0 <= fromi, toi <= n - 1
    fromi != toi
    There are no duplicate edges.
    The graph is directed and acyclic.
*/
public class _2192_All_Ancestors_of_a_Node_in_a_Directed_Acyclic_Graph {

    class Solution {
        /**
         * 使用拓扑排序（Kahn's Algorithm）计算图中每个节点的祖先。
         * <p>
         * 算法思路：
         * 1. 构建一个正向图并计算所有节点的入度。
         * 2. 将所有入度为 0 的节点（图的起点）放入队列。
         * 3. 按照拓扑顺序，从队列中取出节点，并将其作为祖先信息传递给它的所有子节点。
         * 4. 一个节点的祖先集合等于其所有直接父节点的集合，并上所有父节点的祖先集合。
         * <p>
         * <b>复杂度分析</b>
         * <ul>
         * <li><b>时间复杂度:</b> {@code O(E*N + N^2*logN)}
         * <ul>
         * <li>建图与初始化：{@code O(N + E)}，其中 N 是节点数，E 是边数。</li>
         * <li>拓扑排序循环：循环体对每条边执行一次。内部的 {@code addAll} 操作是主要开销，
         * 在最坏情况下，一个节点的祖先集大小可达 O(N)，总时间可能高达 {@code O(E*N)}。</li>
         * <li>结果排序：对 N 个列表排序，每个列表最长为 N，总时间最坏为 {@code O(N^2*logN)}。</li>
         * </ul>
         * <li><b>空间复杂度:</b> {@code O(N^2 + E)}
         * <ul>
         * <li>邻接表 {@code graph} 需要 {@code O(N + E)} 空间。</li>
         * <li>祖先集合列表 {@code answers} 在最坏情况下需要 {@code O(N^2)} 空间。</li>
         * </ul>
         * </ul>
         *
         * @param n     图中节点的数量，编号从 0 到 n-1。
         * @param edges 表示有向边的数组，每个元素 [u, v] 代表一条从 u 到 v 的边。
         * @return 一个列表，其中第 i 个元素是节点 i 的所有祖先（按升序排列）。
         */
        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            // answers 用于存储每个节点的祖先集合，使用 HashSet 自动去重。
            List<Set<Integer>> answers = new ArrayList<>();
            // graph 是邻接表，存储图的结构 (u -> v)。
            List<Integer>[] graph = new List[n];
            // 初始化 answers 和 graph。
            for (int i = 0; i < n; ++i) {
                answers.add(new HashSet<>());
                graph[i] = new ArrayList<>();
            }

            // degree 数组用于存储每个节点的入度 (in-degree)。
            int[] degree = new int[n];
            // 遍历所有边，构建图并计算每个节点的入度。
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]); // 添加从 u 到 v 的边。
                degree[edge[1]]++;     // 节点 v 的入度加 1。
            }

            // 使用队列进行拓扑排序。
            Queue<Integer> queue = new ArrayDeque<>();
            // 将所有入度为 0 的节点（图的起点）加入队列。
            for (int i = 0; i < n; ++i) {
                if (degree[i] == 0) {
                    queue.offer(i);
                }
            }

            // 当队列不为空时，持续按拓扑顺序处理节点。
            while (!queue.isEmpty()) {
                int curr = queue.poll();

                // 遍历该节点的所有邻居 (子节点)。
                for (int nei : graph[curr]) {
                    // 将当前节点 curr 添加为其邻居 nei 的直接祖先。
                    answers.get(nei).add(curr);
                    // 关键步骤：将 curr 的所有祖先也添加为 nei 的祖先。
                    answers.get(nei).addAll(answers.get(curr));

                    // 邻居 nei 的一个父节点已处理完毕，其入度减 1。
                    if (--degree[nei] == 0) {
                        queue.offer(nei);
                    }
                }
            }

            // 将最终的祖先集合转换为排序后的列表。
            List<List<Integer>> result = new ArrayList<>();
            for (Set<Integer> s : answers) {
                List<Integer> l = new ArrayList<>(s);
                Collections.sort(l);
                result.add(l);
            }

            return result;
        }
    }

    class Solution2 {
        /**
         * 使用反向图、深度优先搜索（DFS）和备忘录来计算祖先。
         * <p>
         * 算法思路：
         * 1. 构建一个反向图，如果原图有 `u -> v`，则建边 `v -> u`。
         * 2. 从反向图中的节点 `i` 出发进行 DFS，所有能访问到的节点就是 `i` 的祖先。
         * 3. 使用备忘录（memoization）存储已计算过的节点的祖先集，避免重复计算。
         * <p>
         * <b>复杂度分析</b>
         * <ul>
         * <li><b>时间复杂度:</b> {@code O(N^2*logN + E)}
         * <ul>
         * <li>建图：{@code O(N + E)}。</li>
         * <li>DFS 与备忘录：由于备忘录的存在，每个节点的祖先集合只会计算一次。
         * 所有 {@code addAll} 操作的总成本与图的传递闭包大小有关，最坏为 {@code O(N^2)}。
         * 因此 DFS 部分总时间约为 {@code O(N^2 + E)}。</li>
         * <li>结果排序：最坏为 {@code O(N^2*logN)}。</li>
         * </ul>
         * <li><b>空间复杂度:</b> {@code O(N^2 + E)}
         * <ul>
         * <li>反向图 {@code graph} 需要 {@code O(N + E)} 空间。</li>
         * <li>备忘录 {@code temp} 和递归栈深度，最坏情况下需要 {@code O(N^2)} 空间。</li>
         * </ul>
         * </ul>
         *
         * @param n     图中节点的数量。
         * @param edges 有向边数组。
         * @return 每个节点的祖先列表。
         */
        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            // graph 是反向邻接表。
            List<Integer>[] graph = new List[n];
            // temp 作为备忘录，temp[i] 存储节点 i 的所有祖先。
            Set<Integer>[] temp = new Set[n];
            for (int i = 0; i < n; ++i) {
                graph[i] = new ArrayList<>();
            }
            // 遍历边，构建反向图 (v -> u)。
            for (int[] edge : edges) {
                graph[edge[1]].add(edge[0]);
            }

            List<List<Integer>> answer = new ArrayList<>();
            // 遍历所有节点，确保每个节点的祖先都被计算。
            for (int i = 0; i < n; ++i) {
                dfs(graph, i, temp);
                // 将计算结果（Set）转换为排序后的 List。
                List<Integer> l = new ArrayList<>(temp[i]);
                Collections.sort(l);
                answer.add(l);
            }

            return answer;
        }

        private Set<Integer> dfs(List<Integer>[] graph, int node, Set<Integer>[] temp) {
            // 如果此节点的祖先已在备忘录中，直接返回。
            if (temp[node] != null) {
                return temp[node];
            }

            // 初始化当前节点的祖先集合。
            temp[node] = new HashSet<>();
            // 在反向图中，遍历 node 的邻居，即它的直接父节点。
            for (int parent : graph[node]) {
                // 将直接父节点 parent 加入祖先集。
                temp[node].add(parent);
                // 递归地找到父节点的所有祖先，并将它们全部加入。
                temp[node].addAll(dfs(graph, parent, temp));
            }

            return temp[node];
        }
    }

    class Solution3 {
        /**
         * 使用多次独立的深度优先搜索（DFS）来计算祖先。
         * <p>
         * 算法思路：
         * 1. 构建一个标准的正向图。
         * 2. 遍历每一个节点 `i` (从 0 到 n-1)，将其视为一个潜在的“祖先”。
         * 3. 对于每一个 `i`，都发起一次全新的 DFS，目的是找到 `i` 的所有后代节点。
         * 4. 在 DFS 过程中，每当找到一个 `i` 的后代 `j`，就将 `i` 添加到 `j` 的祖先列表中。
         * <p>
         * <b>复杂度分析</b>
         * <ul>
         * <li><b>时间复杂度:</b> {@code O(N * (N + E))}
         * <ul>
         * <li>建图：{@code O(N + E)}。</li>
         * <li>主循环和 DFS：主循环执行 N 次，每次都进行一次完整的图遍历（DFS），
         * 其成本为 {@code O(N+E)}。因此这部分总时间是 {@code O(N * (N+E))}，这是主要的性能瓶颈。</li>
         * </ul>
         * <li><b>空间复杂度:</b> {@code O(N^2 + E)}
         * <ul>
         * <li>邻接表 {@code graph} 需要 {@code O(N + E)} 空间。</li>
         * <li>结果列表 {@code answer} 在最坏情况下需要 {@code O(N^2)} 空间。</li>
         * </ul>
         * </ul>
         *
         * @param n     图中节点的数量。
         * @param edges 有向边数组。
         * @return 每个节点的祖先列表。
         */
        public List<List<Integer>> getAncestors(int n, int[][] edges) {
            // graph 是正向邻接表。
            List<Integer>[] graph = new List[n];
            List<List<Integer>> answer = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                graph[i] = new ArrayList<>();
                answer.add(new ArrayList<>());
            }
            // 构建正向图。
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
            }

            // 核心循环：遍历每一个节点，把它当作一个潜在的“祖先” parent。
            for (int i = 0; i < n; ++i) {
                // 从节点 i 开始进行一次独立的 DFS，寻找其所有后代。
                dfs(graph, i, i, answer, new boolean[n]);
            }

            // 对结果进行排序。
            for (int i = 0; i < n; ++i) {
                Collections.sort(answer.get(i));
            }

            return answer;
        }

        private void dfs(List<Integer>[] graph, int parent, int node, List<List<Integer>> answer, boolean[] visited) {
            // 标记当前节点已在本次 DFS 中访问。
            visited[node] = true;
            // 遍历所有直接后代。
            for (int nei : graph[node]) {
                // 如果后代未被访问过。
                if (!visited[nei]) {
                    // 将固定的“祖先” parent 添加到后代 nei 的祖先列表中。
                    answer.get(nei).add(parent);
                    // 继续向下搜索。
                    dfs(graph, parent, nei, answer, visited);
                }
            }
        }
    }
}
