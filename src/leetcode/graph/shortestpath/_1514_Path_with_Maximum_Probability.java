package leetcode.graph.shortestpath;

/*
You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list
where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.


Example 1:
    Input:
        n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
    Output:
        0.25000
    Explanation:
        There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.

Example 2:
    Input:
        n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
    Output:
        0.30000

Example 3:
    Input:
        n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
    Output:
        0.00000
    Explanation:
        There is no path between 0 and 2.


Constraints:
    2 <= n <= 10^4
    0 <= start, end < n
    start != end
    0 <= a, b < n
    a != b
    0 <= succProb.length == edges.length <= 2*10^4
    0 <= succProb[i] <= 1
    There is at most one edge between every two nodes.

*/
public class _1514_Path_with_Maximum_Probability {

    /**
     * <h3>Approach</h3>
     * Dijkstra 的概率版：把“距离加法最小化”换成“概率乘法最大化”。使用最大堆按“到达某节点的当前累计概率”降序扩展。
     *
     * <h3>Algorithm</h3>
     * <ol>
     *   <li>建邻接表 {@code g}，无向边拆成两条有向边。</li>
     *   <li>数组 {@code prob[u]} 记录从 {@code start} 到 {@code u} 的最大累计概率，初始化 {@code prob[start]=1.0}。</li>
     *   <li>最大堆保存 {@code State(node, cumulativeProb)}，按 {@code cumulativeProb} 降序。</li>
     *   <li>循环：
     *     <ul>
     *       <li>弹出堆顶 {@code cur}；若 {@code cur.prob < prob[cur.node]} 为过期条目（stale），跳过。</li>
     *       <li>若 {@code cur.node == end}，直接返回 {@code cur.prob}（首次到达即最优）。</li>
     *       <li>对每条邻边 {@code (cur.node -> v, p)}：尝试 {@code newProb = cur.prob * p}，若更优则更新并入堆。</li>
     *     </ul>
     *   </li>
     * </ol>
     *
     * <h3>Correctness / Key Invariant</h3>
     * 堆始终以“累计概率”作为关键字降序；结合过期条目检查，保证首次弹出的某节点对应的概率即为全局最优。
     *
     * <h3>Complexity</h3>
     * 时间：{@code O((n + m) log n)}；空间：{@code O(n + m)}，其中 {@code m = edges.length}。
     *
     * <h3>Edge Cases &amp; Pitfalls</h3>
     * <ul>
     *   <li>遍历建图必须使用 {@code edges.length}。</li>
     *   <li>不要让堆比较器依赖可变全局数组（如 {@code prob[]}）；应把比较用权重随状态一并入堆。</li>
     *   <li>无需 {@code visited}；用 stale-entry check 更稳健。</li>
     *   <li>浮点比较使用 {@code Double.compare}；概率乘积范围在 {@code [0,1]} 内，精度充足。</li>
     * </ul>
     *
     * <h3>Implementation Notes</h3>
     * <ul>
     *   <li>使用 Java {@code record} 表达小数据载体；控制语句一律加大括号。</li>
     *   <li>省略包/导入，必要处使用全限定名以便 LeetCode 直接提交。</li>
     * </ul>
     *
     * <h3>Alternatives/Variants</h3>
     * 可取对数将乘法转加法（求最短路）；或用 Bellman-Ford（更慢，适合检查思路）。
     */
    static class Solution {

        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            java.util.List<Adj>[] g = new java.util.ArrayList[n];
            for (int i = 0; i < n; i++) {
                g[i] = new java.util.ArrayList<>();
            }
            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0], v = edges[i][1];
                double p = succProb[i];
                g[u].add(new Adj(v, p));
                g[v].add(new Adj(u, p));
            }

            double[] prob = new double[n];
            prob[start] = 1.0d;

            java.util.PriorityQueue<State> pq =
                    new java.util.PriorityQueue<>(java.util.Comparator.<State>comparingDouble(s -> s.prob).reversed());
            pq.offer(new State(start, 1.0d));

            while (!pq.isEmpty()) {
                State cur = pq.poll();

                // 过期条目检查（stale-entry check）
                if (cur.prob < prob[cur.node]) {
                    continue;
                }
                if (cur.node == end) {
                    return cur.prob; // 第一次弹出 end 即为最优
                }
                for (Adj adj : g[cur.node]) {
                    double newProb = cur.prob * adj.p;
                    if (newProb > prob[adj.to]) {
                        prob[adj.to] = newProb;
                        pq.offer(new State(adj.to, newProb));
                    }
                }
            }
            return 0.0d; // 不可达
        }

        private record Adj(int to, double p) { }

        private record State(int node, double prob) { }
    }
}