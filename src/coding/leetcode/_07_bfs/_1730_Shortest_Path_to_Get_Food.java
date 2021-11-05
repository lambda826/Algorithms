package coding.leetcode._07_bfs;

import java.util.ArrayDeque;
import java.util.Deque;

/*

You are starving and you want to eat food as quickly as possible. You want to find the shortest path to arrive at any food cell.

You are given an m x n character matrix, grid, of these different types of cells:
    '*' is your location. There is exactly one '*' cell.
    '#' is a food cell. There may be multiple food cells.
    'O' is free space, and you can travel through these cells.
    'X' is an obstacle, and you cannot travel through these cells.
    You can travel to any adjacent cell north, east, south, or west of your current location if there is not an obstacle.
Return the length of the shortest path for you to reach any food cell. If there is no path for you to reach food, return -1.


Example 1:
    Input:
        grid =
            [["X","X","X","X","X","X"],
             ["X","*","O","O","O","X"],
             ["X","O","O","#","O","X"],
             ["X","X","X","X","X","X"]]
    Output:
        3
    Explanation:
        It takes 3 steps to reach the food.

Example 2:
    Input:
        grid =
            [["X","X","X","X","X"],
             ["X","*","X","O","X"],
             ["X","O","X","#","X"],
             ["X","X","X","X","X"]]
    Output:
        -1
    Explanation:
        It is not possible to reach the food.

Example 3:
    Input:
        grid =
            [["X","X","X","X","X","X","X","X"],
             ["X","*","O","X","O","#","O","X"],
             ["X","O","O","X","O","O","X","X"],
             ["X","O","O","O","O","#","O","X"],
             ["X","X","X","X","X","X","X","X"]]
    Output:
        6
    Explanation:
        There can be multiple food cells. It only takes 6 steps to reach the bottom food.

Example 4:
    Input:
        grid =
            [["O","*"],
             ["#","O"]]
    Output:
        2

Example 5:
    Input:
        grid =
            [["X","*"],
             ["#","X"]]
    Output:
        -1


Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 200
    grid[row][col] is '*', 'X', 'O', or '#'.
    The grid contains exactly one '*'.

*/

public class _1730_Shortest_Path_to_Get_Food {

    private static final int dir[] = { -1, 0, 1, 0, -1 };

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BFS {

        public int getFood(char[][] grid) {
            for (int i = 0; i < grid.length; ++i) {
                for (int j = 0; j < grid[0].length; ++j) {
                    if (grid[i][j] == '*') {
                        return BFS(grid, i, j);
                    }
                }
            }
            return -1;
        }

        private int BFS(char[][] grid, int i, int j) {
            int m = grid.length;
            int n = grid[0].length;
            Deque<Integer> deque = new ArrayDeque<>();
            deque.offer(i * n + j);
            int step = 0;
            while (!deque.isEmpty()) {
                ++step;
                int size = deque.size();
                while (size-- > 0) {
                    int k = deque.poll();
                    int x = k / n;
                    int y = k % n;
                    for (int d = 0; d < 4; ++d) {
                        int xx = x + dir[d];
                        int yy = y + dir[d + 1];
                        if (xx >= 0 && xx < m && yy >= 0 && yy < n) {
                            if (grid[xx][yy] == 'O') {
                                grid[xx][yy] = 'X';
                                deque.offer(xx * n + yy);
                            } else if (grid[xx][yy] == '#') {
                                return step;
                            }
                        }
                    }
                }

            }
            return -1;
        }
    }

}