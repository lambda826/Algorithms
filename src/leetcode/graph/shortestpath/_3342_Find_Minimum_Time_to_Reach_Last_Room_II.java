package leetcode.graph.shortestpath;

import java.util.*;

/*
There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room.
You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.


Example 1:
    Input: moveTime = [[0,4],[4,4]]
    Output: 7
    Explanation:
        The minimum time required is 7 seconds.
        At time t == 4, move from room (0, 0) to room (1, 0) in one second.
        At time t == 5, move from room (1, 0) to room (1, 1) in two seconds.

Example 2:
    Input: moveTime = [[0,0,0,0],[0,0,0,0]]
    Output: 6
    Explanation:
        The minimum time required is 6 seconds.
        At time t == 0, move from room (0, 0) to room (1, 0) in one second.
        At time t == 1, move from room (1, 0) to room (1, 1) in two seconds.
        At time t == 3, move from room (1, 1) to room (1, 2) in one second.
        At time t == 4, move from room (1, 2) to room (1, 3) in two seconds.

Example 3:
    Input: moveTime = [[0,1],[1,2]]
    Output: 4


Constraints:
    2 <= n == moveTime.length <= 750
    2 <= m == moveTime[i].length <= 750
    0 <= moveTime[i][j] <= 10^9
*/
public class _3342_Find_Minimum_Time_to_Reach_Last_Room_II {

    class Solution {
        public int minTimeToReach(int[][] moveTime) {
            int m = moveTime.length;
            int n = moveTime[0].length;
            int[] dir = { 0, 1, 0, -1, 0 };
            int[][] dist = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            // 优先队列（最小堆），Dijkstra 算法的核心。
            // 队列中的元素是坐标 [r, c]。
            // 排序规则是按照到达该坐标的已知最小时间 dist[r][c] 从小到大排序。
            // 这确保了我们总是优先处理当前路径成本最低的节点。
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> dist[a[0]][a[1]]));

            // 设置起点 (0,0) 的到达时间为 0
            dist[0][0] = 0;
            queue.offer(new int[] { 0, 0 });

            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                // 优化：因为 Dijkstra 保证第一次到达终点时就是最短路径，所以一旦处理到终点，就可以直接结束循环。
                if (r == m - 1 && c == n - 1) {
                    break;
                }
                // 根据题目规则，移动成本 step 不是固定的，取决于当前节点的坐标
                int step = ((r + c) % 2 == 0) ? 1 : 2;

                // 遍历当前节点的四个邻居
                for (int i = 0; i < 4; ++i) {
                    int rr = r + dir[i];     // 邻居的行坐标
                    int cc = c + dir[i + 1]; // 邻居的列坐标
                    // 检查邻居坐标是否在网格范围内
                    if (rr >= 0 && rr < m && cc >= 0 && cc < n) {
                        // 核心计算逻辑：计算到达邻居的新时间。
                        // 出发时间点 = max(到达当前节点的时间, 邻居的准入时间)
                        // 到达邻居的时间 = 出发时间点 + 移动成本(step)
                        int newTime = Math.max(dist[r][c], moveTime[rr][cc]) + step;
                        // 松弛操作 (Relaxation)
                        // 如果通过当前节点到达邻居的时间 (newTime) 比之前记录的时间 (dist[rr][cc]) 更短
                        if (newTime < dist[rr][cc]) {
                            dist[rr][cc] = newTime;
                            queue.offer(new int[] { rr, cc });
                        }
                    }
                }
            }
            return dist[m - 1][n - 1];
        }
    }

    class Solution2 {
        public int minTimeToReach(int[][] moveTime) {
            int m = moveTime.length;
            int n = moveTime[0].length;
            int[] dir = { 0, 1, 0, -1, 0 };
            int[][] dist = new int[m][n];
            for (int i = 0; i < m; ++i) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
            }
            Queue<int[]> queue = new ArrayDeque<>();
            dist[0][0] = 0;
            queue.offer(new int[] { 0, 0 });
            int step = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size-- > 0) {
                    int[] curr = queue.poll();
                    int r = curr[0];
                    int c = curr[1];
                    for (int i = 0; i < 4; ++i) {
                        int rr = r + dir[i];
                        int cc = c + dir[i + 1];
                        if (rr >= 0 && rr < m && cc >= 0 && cc < n) {
                            int newTime = Math.max(dist[r][c], moveTime[rr][cc]) + step;
                            if (newTime < dist[rr][cc]) {
                                dist[rr][cc] = newTime;
                                queue.offer(new int[] { rr, cc });
                            }
                        }
                    }
                }
                step = step == 1 ? 2 : 1;
            }
            return dist[m - 1][n - 1];
        }
    }

}
