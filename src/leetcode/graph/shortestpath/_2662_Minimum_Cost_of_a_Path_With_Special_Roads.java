package leetcode.graph.shortestpath;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*

You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space.
You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).

The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.

There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi]
indicates that the ith special road goes in one direction from (x1i, y1i) to (x2i, y2i) with a cost equal to costi.
You can use each special road any number of times.

Return the minimum cost required to go from (startX, startY) to (targetX, targetY).

Example 1:
    Input:  start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
    Output: 5
    Explanation:
        (1,1) -> (1,2) cost 1
        (1,2) -> (3,3) via specialRoads[0] cost 2
        (3,3) -> (3,4) cost 1
        (3,4) -> (4,5) via specialRoads[1] cost 1
        Total = 5

Example 2:
    Input:  start = [3,2], target = [5,7],
          specialRoads = [[5,7,3,2,1],[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
    Output: 7
    Explanation:
        Best is the direct Manhattan path; note roads are directed.

Example 3:
    Input:  start = [1,1], target = [10,4],
          specialRoads = [[4,2,1,1,3],[1,2,7,4,4],[10,3,6,1,2],[6,1,1,2,3]]
    Output: 8


Constraints:
    start.length == target.length == 2
    1 <= startX <= targetX <= 1e5
    1 <= startY <= targetY <= 1e5
    1 <= specialRoads.length <= 200
    specialRoads[i].length == 5
    startX <= x1i, x2i <= targetX
    startY <= y1i, y2i <= targetY
    1 <= costi <= 1e5

*/
public class _2662_Minimum_Cost_of_a_Path_With_Special_Roads {

    /**
     * LeetCode 2662 — Minimum Cost of a Path With Special Roads
     *
     * <p><strong>Approach (label-correcting over special-road endpoints)</strong>
     * <br>
     * Maintain a label {@code cost[dest]} = best known cost to reach a special road's destination node {@code dest}
     * starting from {@code start}, where a step can be:
     * <ul>
     *   <li>Walking (Manhattan) from one point to another, or</li>
     *   <li>Taking a special road (only if it is not worse than walking).</li>
     * </ul>
     * Initialization sets {@code cost[dest]} to the direct Manhattan cost {@code dist(start, dest)} for every special road
     * destination. For each special edge {@code startNode -> destNode (w)}, we try to improve {@code cost[destNode]} by
     * {@code dist(start, startNode) + w}. Any improvement enqueues that edge to propagate further improvements.
     * <br><br>
     * In the processing loop, whenever an edge is dequeued, we interpret its {@code edge.to} as the current
     * "source" node and try to move (by walking) to every special road's start {@code e.from}, then take that special road
     * to reach its destination {@code e.to}. If we improve {@code cost[e.to]}, we enqueue {@code e}.
     * <br><br>
     * Finally, the answer is the minimum of:
     * <ul>
     *   <li>Direct Manhattan from {@code start} to {@code target}, and</li>
     *   <li>{@code cost[dest] + dist(dest, target)} over all labeled destinations {@code dest}.</li>
     * </ul>
     *
     * <p><strong>Optimizations</strong>
     * <ul>
     *   <li><em>Edge filtering</em>: drop a special road if {@code w > Manhattan(from, to)} since walking is strictly better.</li>
     *   <li><em>Upper bound pruning</em>: keep a global best answer {@code best}. When dequeuing an edge with source
     *       {@code u}, if {@code cost[u] + dist(u, target) >= best}, skip expansion.</li>
     *   <li><em>Null-safe labels</em>: treat missing labels as {@code +inf} to avoid NPEs if code changes later.</li>
     * </ul>
     *
     * <p><strong>Complexity</strong>
     * <br>
     * Let {@code K} be the number of special roads. The outer loop may re-enqueue edges upon improvements; in practice
     * with pruning and filtering this is fast. Worst-case relaxations are {@code O(K^2)}. Space is {@code O(K)} for the
     * edge set and labels.
     */
    static class Solution {

        public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
            Point startPoint = new Point(start[0], start[1]);
            Point targetPoint = new Point(target[0], target[1]);

            // Best known answer so far (start to target directly)
            int bestCost = manhattan(startPoint, targetPoint);

            // costMap[to] = best known cost to reach special road's destination "to"
            Map<Point, Integer> costMap = new HashMap<>();

            // Queue for BFS-like label correction
            Queue<SpecialEdge> workQueue = new ArrayDeque<>();

            // All usable special roads (not worse than walking)
            Set<SpecialEdge> specialEdges = new HashSet<>();

            // Initialize costs and seed the queue
            for (int[] road : specialRoads) {
                Point from = new Point(road[0], road[1]);
                Point to = new Point(road[2], road[3]);
                int roadCost = road[4];

                // Skip if walking is strictly better
                if (roadCost > manhattan(from, to)) {
                    continue;
                }

                // Initialize destination cost to direct walk from start
                costMap.putIfAbsent(to, manhattan(startPoint, to));

                SpecialEdge edge = new SpecialEdge(from, to, roadCost);
                specialEdges.add(edge);

                // Try using this special road directly from start
                int newCost = manhattan(startPoint, from) + roadCost;
                int oldCost = costMap.get(to);
                if (newCost < oldCost) {
                    costMap.put(to, newCost);
                    workQueue.offer(edge);
                }
            }

            // Process queue
            while (!workQueue.isEmpty()) {
                SpecialEdge currentEdge = workQueue.poll();
                Point currentPoint = currentEdge.to;
                int currentCost = costMap.get(currentPoint);
                // Try chaining through each special road
                for (SpecialEdge edge : specialEdges) {
                    int chainedCost = currentCost + manhattan(currentPoint, edge.from) + edge.cost;
                    int prevCost = costMap.get(edge.to);
                    if (chainedCost < prevCost) {
                        costMap.put(edge.to, chainedCost);
                        workQueue.offer(edge);
                        bestCost = Math.min(bestCost, chainedCost + manhattan(edge.to, targetPoint));
                    }
                }
            }

            // Final comparison: maybe finishing from some special-road destination is cheaper
            for (Map.Entry<Point, Integer> entry : costMap.entrySet()) {
                bestCost = Math.min(bestCost, entry.getValue() + manhattan(entry.getKey(), targetPoint));
            }

            return bestCost;
        }

        // Manhattan distance helper
        private int manhattan(Point a, Point b) {
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        }

        private record Point(int x, int y) { }

        private record SpecialEdge(Point from, Point to, int cost) { }
    }

    /**
     * LeetCode 2662 — Minimum Cost of a Path With Special Roads
     *
     * <p><strong>整体思路（压缩点 + Dijkstra，队列里直接存 Point）</strong>
     * <br>
     * 在无限平面上，任意两点都可以“走路”，代价是曼哈顿距离；另外有若干单向的“特殊道路”可能更便宜。
     * 我们不在整张无限图上跑最短路，而是“压缩”出一张小图，仅保留以下<strong>关键点</strong>作为节点：
     * start、target、以及每条特殊路的起点/终点。然后在这张小图上做最短路：
     * <ul>
     *   <li>走路边：从某个点到另一个点的代价为曼哈顿距离；</li>
     *   <li>特殊边：保留每条特殊路（若费用劣于走路就替换为走路费，以保证“非劣”）。</li>
     * </ul>
     *
     * <p>为了降低常数开销，走路松弛<strong>只</strong>针对“所有特殊路起点 + 目标点”（不必对所有节点做 O(n) 的走路松弛）。
     * 任意最优路径只可能在“起点 / 某条特殊路的起点 / 终点”这些关键处改变动作（走 or 上特殊路），因此该优化是安全的。
     *
     * <p><strong>本实现的数据结构特点</strong>
     * <ul>
     *   <li>使用 {@code record Point(int x, int y)} 表示平面点，直接作为 HashMap/HashSet 的 key；</li>
     *   <li>邻接表使用 {@code Map<Point, List<Edge>>}，其中 {@code Edge(Point to, int cost)}；</li>
     *   <li>Dijkstra 的优先队列中直接存储 {@code State(dist, point)}，无需 id 映射，代码更直观。</li>
     * </ul>
     *
     * <p><strong>复杂度</strong>
     * <br>
     * 设特殊路数量为 k，则节点数 n ≤ 2k + 2（每条路 2 个端点 + 起终点）。
     * 每次出堆：
     * <ul>
     *   <li>对“所有特殊路起点 + 目标点”执行走路松弛，约 O(k) 次；</li>
     *   <li>对当前点的所有特殊出边执行特殊路松弛，合计 O(m) 次（m 为特殊边数）。</li>
     * </ul>
     * 因此总时间约为 O(n·k·log n + m·log n)，空间 O(n + m)。在题目规模（k ≤ 200）下十分充裕。
     */
    static class Solution2 {

        private static int manhattan(Point a, Point b) {
            return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
        }

        public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
            // 1) 收集关键点：start, target, 以及所有特殊路端点
            Point startPoint = new Point(start[0], start[1]);
            Point targetPoint = new Point(target[0], target[1]);

            // 邻接表：仅存特殊边（走路边不显式存，按需松弛）
            Map<Point, List<Edge>> specialAdj = new HashMap<>();

            // 所有“特殊路的起点”集合（用于走路松弛的目标集合）
            Set<Point> specialStarts = new HashSet<>();

            // 去劣化：特殊路费用设为 min(原费用, 两端曼哈顿距离)，保证“非劣于走路”
            for (int[] r : specialRoads) {
                Point from = new Point(r[0], r[1]);
                Point to = new Point(r[2], r[3]);
                int walk = manhattan(from, to);
                int cost = Math.min(r[4], walk);
                specialAdj.computeIfAbsent(from, k -> new ArrayList<>()).add(new Edge(to, cost));
                specialStarts.add(from);
            }

            // 2) Dijkstra：dist 记录从 start 到各点的最短距离；visited 标记已确定最短的点
            Map<Point, Integer> dist = new HashMap<>();
            Set<Point> visited = new HashSet<>();
            PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingLong(State::dist));

            dist.put(startPoint, 0);
            pq.offer(new State(startPoint, 0));

            while (!pq.isEmpty()) {
                State cur = pq.poll();
                Point u = cur.to;
                int d = cur.dist;

                if (visited.contains(u)) {
                    continue;
                }
                visited.add(u);

                if (u.equals(targetPoint)) {
                    break; // 首次弹出 target 即最短
                }

                // (A) 走路松弛：到“目标点”
                relaxWalk(u, targetPoint, d, dist, pq);

                // (B) 走路松弛：到“所有特殊路起点”
                for (Point startNode : specialStarts) {
                    if (!visited.contains(startNode)) {
                        relaxWalk(u, startNode, d, dist, pq);
                    }
                }

                // (C) 特殊路松弛：u 的所有特殊出边
                List<Edge> out = specialAdj.get(u);
                if (out != null) {
                    for (Edge e : out) {
                        Point v = e.to;
                        int nd = d + e.cost;
                        if (nd < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                            dist.put(v, nd);
                            pq.offer(new State(v, nd));
                        }
                    }
                }
            }

            // 若 Dijkstra 过程中未显式更新 target，也可直接返回“走路”的距离上界
            return Math.min(dist.getOrDefault(targetPoint, Integer.MAX_VALUE), manhattan(startPoint, targetPoint));
        }

        private void relaxWalk(Point u, Point v, int du, Map<Point, Integer> dist, PriorityQueue<State> pq) {
            int w = manhattan(u, v);
            int nd = du + w;
            if (nd < dist.getOrDefault(v, Integer.MAX_VALUE)) {
                dist.put(v, nd);
                pq.offer(new State(v, nd));
            }
        }

        private record Point(int x, int y) { }

        private record Edge(Point to, int cost) { }

        private record State(Point to, int dist) { }
    }

    /**
     * LeetCode 2662 — Minimum Cost of a Path With Special Roads
     *
     * <p><strong>思路（以“特殊路的终点”为状态做最短路 / 最佳优先）</strong>
     * <br>
     * 把每条特殊路 i 的“终点 (x2_i, y2_i)”视为一个可达状态，维护：
     * <pre>
     * bestCostToEnd[i] = 从 start 出发，走到这条路的起点再走该路，到达“路 i 的终点”的最小代价
     * </pre>
     * 初始化时对每条路 i 计算：
     * <pre>
     * bestCostToEnd[i] = dist(start, (x1_i, y1_i)) + min(cost_i, dist((x1_i,y1_i),(x2_i,y2_i)))
     * </pre>
     * 并用
     * <pre>
     * bestAnswer = min( dist(start, target),
     *                   bestCostToEnd[i] + dist((x2_i,y2_i), target) over all i )
     * </pre>
     * 作为全局上界。
     *
     * <p>随后在堆中不断取出“当前到达某个路终点的最小代价”并尝试“接下一条路 j”：
     * <pre>
     * next = bestCostToEnd[i] + dist(end_i, start_j) + min(cost_j, dist(start_j, end_j))
     * </pre>
     * 若能改进 bestCostToEnd[j]，则更新并入堆；同时用
     * <pre>
     * next + dist(end_j, target)
     * </pre>
     * 改善全局上界 bestAnswer。若堆顶代价已经 ≥ bestAnswer，则可停止（所有边权非负）。
     *
     * <p><strong>正确性</strong>：任意最优路径都可分解为若干段“步行到某条路起点 + 走该路”的链，最后再步行到 target。
     * 我们枚举的是“链中每条路的终点”，并覆盖了所有可能的链式组合；同时用步行到 target 的代价维护全局上界，不会漏解。
     *
     * <p><strong>复杂度</strong>：设特殊路数量为 K。初始化 O(K)，主循环每次出堆最多尝试到 K-1 条路，整体 O(K^2 log K)；空间 O(K)。
     *
     * <p><strong>工程细节</strong>：
     * <ul>
     *   <li>使用 long 存代价，避免累加溢出。</li>
     *   <li>比较器使用 {@code Comparator.comparingLong}，避免 {@code a-b} 溢出。</li>
     *   <li>对每条路做“去劣化”（用 {@code min(cost, manhattan)}），避免无意义的劣边进入堆。</li>
     * </ul>
     */
    static class Solution3 {

        public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
            final int n = specialRoads.length;

            // 全局上界：直接步行 start -> target 的曼哈顿距离
            long bestAnswer = manhattan(start[0], start[1], target[0], target[1]);

            // bestCostToEnd[i] = 从 start 到达“第 i 条特殊路的终点”的当前最小总代价
            long[] bestCostToEnd = new long[n];
            Arrays.fill(bestCostToEnd, Long.MAX_VALUE / 4);

            // 最小堆：元素为 {当前总代价, 路索引}
            PriorityQueue<long[]> minHeap =
                    new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

            // 1) 初始化：尝试“直接走到每条路的起点 + 走该条路”
            for (int i = 0; i < n; i++) {
                int x1 = specialRoads[i][0], y1 = specialRoads[i][1];
                int x2 = specialRoads[i][2], y2 = specialRoads[i][3];
                int rawCost = specialRoads[i][4];

                // 去劣化：特殊路费用不应劣于两端的步行
                int roadCost = Math.min(rawCost, manhattan(x1, y1, x2, y2));

                long costToThisEnd = (long) manhattan(start[0], start[1], x1, y1) + roadCost;

                if (costToThisEnd < bestCostToEnd[i]) {
                    bestCostToEnd[i] = costToThisEnd;
                    minHeap.offer(new long[] { costToThisEnd, i });

                    // 更新全局上界（从该路终点直接走到 target）
                    long finishCost = costToThisEnd + manhattan(x2, y2, target[0], target[1]);
                    if (finishCost < bestAnswer) bestAnswer = finishCost;
                }
            }

            // 2) 主循环：每次从堆顶取出“已到达某条路终点”的最小代价，尝试接下一条路
            while (!minHeap.isEmpty()) {
                long[] state = minHeap.poll();
                long currCost = state[0];
                int roadIdx = (int) state[1];

                // 剪枝 1：堆顶代价已不小于当前全局最优解，后续不可能更好
                if (currCost >= bestAnswer) break;

                // 过期检查：若不是该路当前的最优代价，跳过
                if (currCost != bestCostToEnd[roadIdx]) continue;

                int endX = specialRoads[roadIdx][2];
                int endY = specialRoads[roadIdx][3];

                // 尝试接任意下一条路 j
                for (int j = 0; j < n; j++) {
                    if (j == roadIdx) continue; // 连着用同一条路通常无益（非负权）

                    int sx = specialRoads[j][0], sy = specialRoads[j][1];
                    int tx = specialRoads[j][2], ty = specialRoads[j][3];
                    int rawCost = specialRoads[j][4];
                    int roadCost = Math.min(rawCost, manhattan(sx, sy, tx, ty)); // 去劣化

                    // 从当前终点 (endX,endY) 走到下一条路的起点 (sx,sy)，再走该路
                    long nextCost = currCost + manhattan(endX, endY, sx, sy) + roadCost;

                    // 只有“有希望改进答案”且“确实改进该路终点代价”才入堆
                    if (nextCost < bestAnswer && nextCost < bestCostToEnd[j]) {
                        bestCostToEnd[j] = nextCost;
                        minHeap.offer(new long[] { nextCost, j });

                        // 用“到达路 j 终点 + 直走到 target”的总代价更新全局上界
                        long finishCost = nextCost + manhattan(tx, ty, target[0], target[1]);
                        if (finishCost < bestAnswer) bestAnswer = finishCost;
                    }
                }
            }
            return (int) bestAnswer;
        }

        /**
         * 曼哈顿距离：|x1-x2| + |y1-y2|
         */
        private int manhattan(int x1, int y1, int x2, int y2) {
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        }

        /**
         * 曼哈顿距离，数组版本
         */
        @SuppressWarnings("unused")
        private int manhattan(int[] a, int[] b) {
            return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
        }
    }

}
