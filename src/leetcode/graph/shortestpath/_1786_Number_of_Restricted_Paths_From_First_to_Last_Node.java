package leetcode.graph.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _1786_Number_of_Restricted_Paths_From_First_to_Last_Node {

    /**
     * 这个问题要求计算从节点 1 到节点 n 的“限制性路径”数量。
     * “限制性路径”的定义是：路径上每一步都必须走向一个“距离终点 n 更近”的节点。
     * <p>
     * 本解法将 Dijkstra 算法和动态规划（DP）相结合，在一次遍历中高效地解决问题。
     * 核心思想：Dijkstra 算法按“距离终点 n”从小到大的顺序处理节点，
     * 这个顺序天然地为 DP 计算提供了一个有效的拓扑序。
     */
    class Solution {

        public int countRestrictedPaths(int n, int[][] edges) {
            final int MOD = 1_000_000_007;

            // 1. 构建图的邻接表表示
            List<List<NodeInfo>> adjacencyList = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjacencyList.add(new ArrayList<>());
            }
            for (int[] edge : edges) {
                int node1 = edge[0];
                int node2 = edge[1];
                int weight = edge[2];
                // 无向图，需要为两个节点都添加对方作为邻居
                adjacencyList.get(node1).add(new NodeInfo(node2, weight));
                adjacencyList.get(node2).add(new NodeInfo(node1, weight));
            }

            // 2. 初始化用于算法的数据结构
            // distToLastNode[i] 存储从节点 i 到终点 n 的最短距离
            int[] distToLastNode = new int[n + 1];
            Arrays.fill(distToLastNode, Integer.MAX_VALUE);
            distToLastNode[n] = 0; // 终点 n 到自身的距离为 0

            // pathCounts[i] 存储从节点 i 到终点 n 的限制性路径数量 (作为 DP 数组)
            int[] pathCounts = new int[n + 1];
            pathCounts[n] = 1; // DP 的基础状态：从 n 到 n 只有 1 条路径（即原地不动）

            // 优先队列（最小堆）用于 Dijkstra 算法，按距离从小到大对节点进行排序
            PriorityQueue<NodeInfo> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
            minHeap.offer(new NodeInfo(n, 0)); // 从终点 n 开始执行 Dijkstra
            // 3. 执行 Dijkstra 算法，并在其中无缝嵌入 DP 计算
            while (!minHeap.isEmpty()) {
                // 从堆中取出当前已知距离终点 n 最近的节点
                NodeInfo currentNodeInfo = minHeap.poll();
                int currentDist = currentNodeInfo.weight;
                int currentNode = currentNodeInfo.nodeId;

                // 这是一个标准的 Dijkstra 优化：如果当前取出的距离比已记录的距离要大，
                // 说明这个节点信息是旧的（之前有更短的路径被发现了），直接跳过。
                if (currentDist > distToLastNode[currentNode]) {
                    continue;
                }

                // 遍历当前节点的所有邻居
                for (NodeInfo neighbor : adjacencyList.get(currentNode)) {
                    int neighborNode = neighbor.nodeId;
                    int edgeWeight = neighbor.weight;

                    // --- Part A: Dijkstra 的核心 —— 路径松弛 ---
                    // 如果通过 currentNode 可以找到一条到达 neighborNode 的更短路径
                    if (distToLastNode[neighborNode] > distToLastNode[currentNode] + edgeWeight) {
                        // 更新到达 neighborNode 的最短距离
                        distToLastNode[neighborNode] = distToLastNode[currentNode] + edgeWeight;
                        // 将更新后的邻居节点信息加入优先队列
                        minHeap.offer(new NodeInfo(neighborNode, distToLastNode[neighborNode]));
                    }

                    // --- Part B: DP 的核心 —— 状态转移 ---
                    // 如果 neighborNode 比 currentNode 更“远离”终点 n (即到 n 的距离更大)
                    // 这意味着从 neighborNode -> currentNode 是一步有效的“下山”操作，满足限制性条件。
                    if (distToLastNode[neighborNode] > distToLastNode[currentNode]) {
                        // 那么，所有从 currentNode 到 n 的限制性路径，现在都可以从 neighborNode 通过一步走过来。
                        // 所以，从 neighborNode 出发的路径数，需要加上从 currentNode 出发的路径数。
                        // 注意每一步都取模，防止整数溢出。
                        pathCounts[neighborNode] = (pathCounts[neighborNode] + pathCounts[currentNode]) % MOD;
                    }
                }
            }

            // 4. 返回最终结果
            // 循环结束后，pathCounts[1] 中就存储了从节点 1 到 n 的所有限制性路径总数
            return pathCounts[1];
        }

        static class NodeInfo {
            int nodeId;
            int weight;

            NodeInfo(int nodeId, int weight) {
                this.nodeId = nodeId;
                this.weight = weight;
            }
        }
    }
}
