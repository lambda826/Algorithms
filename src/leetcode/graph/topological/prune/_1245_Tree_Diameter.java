package leetcode.graph.topological.prune;

import java.util.*;

/*

The diameter of a tree is the number of edges in the longest path in that tree.

There is an undirected tree of n nodes labeled from 0 to n - 1.
You are given a 2D array edges where edges.length == n - 1 and edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the tree.

Return the diameter of the tree.


Example 1:
    Input: edges = [[0,1],[0,2]]
    Output: 2
    Explanation: The longest path of the tree is the path 1 - 0 - 2.

Example 2:
    Input: edges = [[0,1],[1,2],[2,3],[1,4],[4,5]]
    Output: 4
    Explanation: The longest path of the tree is the path 3 - 2 - 1 - 4 - 5.


Constraints:
    n == edges.length + 1
    1 <= n <= 10^4
    0 <= ai, bi < n
    ai != bi

*/
public class _1245_Tree_Diameter {


    class Solution_Prune {
        public int treeDiameter(int[][] edges) {
            int n = edges.length + 1;

            // 特殊情况处理：如果只有1个或2个节点
            if (n <= 2) {
                return n - 1;
            }

            // 1. 构建邻接表和每个节点的度
            List<Set<Integer>> adj = new ArrayList<>();
            int[] degrees = new int[n];
            for (int i = 0; i < n; i++) {
                adj.add(new HashSet<>());
            }
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];
                adj.get(u).add(v);
                adj.get(v).add(u);
                degrees[u]++;
                degrees[v]++;
            }

            // 2. 初始化队列，将所有叶子节点（度为1）入队
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (degrees[i] == 1) {
                    queue.offer(i);
                }
            }

            int remainingNodes = n;
            int diameter = 0;

            // 3. 循环“剥离”叶子节点，直到剩下 2 个或 1 个节点
            while (remainingNodes > 2) {
                int levelSize = queue.size(); // 当前层的叶子节点数量
                remainingNodes -= levelSize;  // 减去这一层的节点

                // 遍历并移除当前所有的叶子节点
                for (int i = 0; i < levelSize; i++) {
                    int u = queue.poll();
                    // 找到该叶子节点的唯一邻居
                    for (int v : adj.get(u)) {
                        // 由于 u 被移除，其邻居 v 的度减 1
                        degrees[v]--;
                        if (degrees[v] == 1) {
                            // 如果邻居 v 成为了新的叶子节点，入队
                            queue.offer(v);
                        }
                    }
                }
                // 每剥完一层，直径的两端都向内缩进了一步，所以路径长度增加了 2
                diameter += 2;
            }

            // 4. 处理最后剩下的中心部分
            // 如果最后剩下 2 个节点，说明中心是一条边，直径是奇数，需要再加 1
            if (remainingNodes == 2) {
                diameter++;
            }

            // 如果最后剩下 1 个节点，说明中心是一个节点，直径是偶数，无需额外操作

            return diameter;
        }
    }

    class Solution_DFS {
        private List<List<Integer>> adj;
        private int diameter;

        public int treeDiameter(int[][] edges) {
            int n = edges.length + 1;
            if (n <= 1) {
                return 0;
            }
            // 1. 构建邻接表
            this.adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }
            this.diameter = 0;
            // 2. 从任意节点（如 0）开始深度优先搜索, -1 表示没有父节点，防止向回访问
            dfs(0, -1);
            return this.diameter;
        }

        /**
         * 深度优先搜索
         *
         * @param u      当前节点
         * @param parent 父节点，用于防止在树中走回头路
         * @return 返回从节点 u 向其子树延伸的最长路径长度（边的数量）
         */
        private int dfs(int u, int parent) {
            // 记录从 u 出发到其子树的最长和次长路径
            int maxDepth1 = 0;
            int maxDepth2 = 0;

            for (int v : adj.get(u)) {
                if (v == parent) { // 不访问父节点
                    continue;
                }
                // 递归地获取从子节点 v 出发的最长路径长度
                int childDepth = dfs(v, u);

                // 更新最长和次长路径
                if (childDepth + 1 > maxDepth1) {
                    maxDepth2 = maxDepth1;
                    maxDepth1 = childDepth + 1;
                } else if (childDepth + 1 > maxDepth2) {
                    maxDepth2 = childDepth + 1;
                }
            }

            // 3. 更新全局直径
            // 经过节点 u 的最长路径是其最长和次长向下路径之和
            this.diameter = Math.max(this.diameter, maxDepth1 + maxDepth2);

            // 4. 返回从 u 出发的最长路径，供其父节点使用
            return maxDepth1;
        }
    }

}
