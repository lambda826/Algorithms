package coding.leetcode._11_graph.search.matrix;


/*
You are given a m x n 2D grid initialized with these three possible values.
    -1 - A wall or an obstacle.
    0 - A gate.
    INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.


Example 1:
Input:
    rooms = [[2147483647,-1,0,2147483647],
            [2147483647,2147483647,2147483647,-1],
            [2147483647,-1,2147483647,-1],
            [0,-1,2147483647,2147483647]]
Output:
    [[3,-1,0,1],
    [2,2,1,-1],
    [1,-1,2,-1],
    [0,-1,3,4]]

Example 2:
Input:
    rooms = [[-1]]
Output:
    [[-1]]

Example 3:
    Input:
        rooms = [[2147483647]]
    Output:
        [[2147483647]]

Example 4:
    Input:
        rooms = [[0]]
    Output:
        [[0]]

*/

import java.util.ArrayDeque;
import java.util.Queue;

public class _0286_Walls_and_Gates {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Lesson:
    // 1. Direction expression
    // 2. index transfer
    // 3. Modify board status first before enqueue
    public void wallsAndGates_BFS(int[][] rooms) {
        // Find all gates
        int row = rooms.length;
        int col = rooms[0].length;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * col + j);
                }
            }
        }

        // BFS every empty room start from the gates, common.mark the shortest distance
        int[] dir = {-1, 0, 1, 0, -1};
        int dis = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                int x = curr / col;
                int y = curr % col;
                for (int i = 0; i < 4; ++i) {
                    int nextX = x + dir[i];
                    int nextY = y + dir[i + 1];
                    if (nextX >= 0 && nextX < row && nextY >= 0 && nextY < col && rooms[nextX][nextY] > dis + 1) {
                        queue.offer(nextX * col + nextY);
                        rooms[nextX][nextY] = dis + 1;
                    }
                }
            }
            ++dis;
        }
    }
}
