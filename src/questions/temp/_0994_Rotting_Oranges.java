package questions.temp;

/*

In a given grid, each cell can have one of three values:
    the value 0 representing an empty cell;
    the value 1 representing a fresh orange;
    the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.


Example 1:
    Input: [[2,1,1],[1,1,0],[0,1,1]]
    Output: 4

Example 2:
    Input: [[2,1,1],[0,1,1],[1,0,1]]
    Output: -1
    Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
    Input: [[0,2]]
    Output: 0
    Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.


Note:
    1 <= grid.length <= 10
    1 <= grid[0].length <= 10
    grid[i][j] is only 0, 1, or 2.

*/

import java.util.ArrayDeque;
import java.util.Queue;

public class _0994_Rotting_Oranges {

    /**
     * 1. Add initial rotten oranges into the queue
     * 2. BFS level traverse
     */
    public int orangesRotting_BFS(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int fresh = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < r; ++i) {
            for (int j = 0; j < c; ++j) {
                if (grid[i][j] == 2) {
                    queue.offer(i * c + j);
                } else if (grid[i][j] == 1) {
                    ++fresh;
                }
            }
        }
        int min = 0;
        int[][] nei = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        if (!queue.isEmpty()) {
            --min; // Trick here
            while (!queue.isEmpty()) {
                ++min;
                int size = queue.size();
                while (size-- > 0) {
                    int n = queue.poll();
                    for (int[] ne : nei) {
                        int i = n / c + ne[0];
                        int j = n % c + ne[1];
                        if (i >= 0 && j >= 0 && i < r && j < c && grid[i][j] == 1) {
                            grid[i][j] = 2;
                            queue.offer(i * c + j);
                            --fresh;
                        }
                    }
                }
            }
        }
        return fresh == 0 ? min : -1;
    }
}
