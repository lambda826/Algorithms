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

    /**
     * 核心思路：通过“剪枝”找到最小必要路径
     * 这道题的关键在于找到一个最小的子树，这个子树中的所有边都是我们为了收集所有硬币而“不得不”经过的。 一旦找到这个最小子树，我们只需要计算它的边数，并乘以2（因为需要往返）就是答案。
     * 所以，解题的策略就是**“剪枝”（Pruning）**。我们会分两步进行剪枝，逐步移除那些不需要被直接访问的节点和边。
     * <p>
     * 分步解析
     * 步骤 1：第一次剪枝 - 移除没有硬币的叶子节点
     * 目标： 移除所有那些既没有硬币，也不是通往任何有硬币节点的必经之路的节点。
     * 为什么这样做？
     * 如果一个节点是叶子（度为1），并且它上面没有硬币，那么我们没有理由去访问它。访问它只会增加边数，但收集不到硬币，它也不是去往其他硬币的通道。
     * 当我们移除这样的叶子节点后，它的唯一邻居的度会减小。如果这个邻居因此也变成了新的“没有硬币的叶子节点”，那么它也应该被移除。这个过程会像水流一样，从树的边缘向内收缩。
     * <p>
     * 步骤 2：第二次剪枝 - 移除两层“边缘”节点
     * 目标： 利用“距离2收集”的规则，进一步优化路径。
     * 为什么这样做？
     * 题目允许从当前节点2条边以内收集硬币。这意味着，如果你在一个节点 X，你可以收集到 X 自身、X 的邻居 Y 上的硬币，以及 X 的邻居 Y 的邻居 Z (只要 Z 离 X 距离为2) 上的硬币。
     * 考虑一个在第一次剪枝后仍然活跃的叶子节点 L。这个 L 节点必然是有硬币的（否则它在第一步就被移除了）。我们其实不需要真正走到 L。
     * 如果你走到 L 的父节点 P，你就可以收集到 L 上的硬币（距离为1）。
     * 如果你走到 L 的祖父节点 GP，你也可以收集到 L 上的硬币（距离为2，路径 GP-P-L）。
     * 这意味着，对于任何一个有硬币的叶子节点 L，它和它的父节点 P 都可以被“跳过”，我们只需要确保能到达 GP 即可。所以，我们可以再移除两层叶子节点。
     * <p>
     * 步骤 3：计算最终路径长度
     * 目标： 统计剩下需要遍历的边数。
     * 如何实现？
     * 统计活跃边数： 遍历原始的 edges 数组。对于每一条边 (u, v)：
     * 如果 u 和 v 都仍然是活跃的 (active[u] == true 且 active[v] == true)，那么这条边是我们需要遍历的。将一个 remainingEdges 计数器加 1。
     * 计算总路径： 最终的答案就是 remainingEdges * 2。因为我们需要从一个起始点出发，经过所有这些必要的边，最后再回到起始点，所以每条边都会被走两次（去和回）。
     */
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
