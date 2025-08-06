package leetcode.graph.shortestpath;

import java.util.ArrayDeque;
import java.util.Queue;

/*

There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds after which the room opens and can be moved to.
You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).
Two rooms are adjacent if they share a common wall, either horizontally or vertically.


Example 1:
    Input: moveTime = [[0,4],[4,4]]
    Output: 6
    Explanation:
        The minimum time required is 6 seconds.
        At time t == 4, move from room (0, 0) to room (1, 0) in one second.
        At time t == 5, move from room (1, 0) to room (1, 1) in one second.

Example 2:
    Input: moveTime = [[0,0,0],[0,0,0]]
    Output: 3
    Explanation:
        The minimum time required is 3 seconds.
        At time t == 0, move from room (0, 0) to room (1, 0) in one second.
        At time t == 1, move from room (1, 0) to room (1, 1) in one second.
        At time t == 2, move from room (1, 1) to room (1, 2) in one second.

Example 3:
    Input: moveTime = [[0,1],[1,2]]
    Output: 3


Constraints:
    2 <= n == moveTime.length <= 50
    2 <= m == moveTime[i].length <= 50
    0 <= moveTime[i][j] <= 10^9

*/
public class _3341_Find_Minimum_Time_to_Reach_Last_Room_I {

    class Solution {
        public int minTimeToReach(int[][] moveTime) {
            int m = moveTime.length;
            int n = moveTime[0].length;
            // 创建一个二维数组 minDist，用于存储到达每个房间 (i, j) 的最小时间。这相当于 Dijkstra 算法中的 dist[] 数组。
            int[][] minDist = new int[m][n];
            // 将所有房间的初始最小到达时间设置为一个非常大的值（代表无穷大）。
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    minDist[i][j] = Integer.MAX_VALUE;
                }
            }
            // 创建一个队列来存放待处理的房间坐标。
            Queue<int[]> queue = new ArrayDeque<>();
            // 定义方向数组，一个简化技巧，用于遍历四个方向：(0,1), (0,-1), (1,0), (-1,0)
            int[] dir = { 0, 1, 0, -1, 0 };
            // 起点 (0, 0) 的到达时间为 0
            minDist[0][0] = 0;
            // 将起点加入队列，开始搜索
            queue.offer(new int[] { 0, 0 });
            // 当队列中还有待处理的房间时，循环继续
            while (!queue.isEmpty()) {
                // 从队列头部取出一个房间进行处理
                int[] curr = queue.poll();
                int r = curr[0]; // 当前房间的行坐标
                int c = curr[1]; // 当前房间的列坐标
                // 遍历当前房间的四个相邻邻居
                for (int i = 0; i < 4; ++i) {
                    int x = r + dir[i];     // 邻居的行坐标
                    int y = c + dir[i + 1]; // 邻居的列坐标
                    // 检查邻居坐标是否在网格范围内
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        // 核心计算逻辑：计算到达邻居 (x, y) 的时间。
                        // 必须在 max(当前时间, 邻居的准入时间) 之后才能出发，移动再 +1。
                        int newTime = Math.max(minDist[r][c], moveTime[x][y]) + 1;

                        // 松弛操作 (Relaxation)
                        // 如果通过当前房间到达邻居的时间 newTime < 之前记录的到达邻居的时间 minDist[x][y]，
                        // 说明我们找到了一条更快的路径。
                        if (newTime < minDist[x][y]) {
                            // 更新到达邻居的最小时间
                            minDist[x][y] = newTime;
                            // 将更新过的邻居加入队列，以便后续从它出发继续探索
                            queue.offer(new int[] { x, y });
                        }
                    }
                }
            }
            // 所有可达路径都探索完毕后，返回到达右下角终点的最小时间
            return minDist[m - 1][n - 1];
        }
    }
}
