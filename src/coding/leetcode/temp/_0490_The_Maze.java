/**
 *  @author Yunxiang He
 *  @date 04/11/2019
 */

package coding.leetcode.temp;

import java.util.LinkedList;
import java.util.Queue;

/*

There is a ball in a maze with empty spaces and walls. 
    The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
    When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 
    1 means the wall and 0 means the empty space. 
    You may assume that the borders of the maze are all walls. 
    The start and destination coordinates are represented by row and column indexes.


Example 1:
    Input 1: a maze represented by a 2D array
        0 0 1 0 0
        0 0 0 0 0
        0 0 0 1 0
        1 1 0 1 1
        0 0 0 0 0
    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (4, 4)
    Output: true
    Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:
    Input 1: a maze represented by a 2D array
        0 0 1 0 0
        0 0 0 0 0
        0 0 0 1 0
        1 1 0 1 1
        0 0 0 0 0
    Input 2: start coordinate (rowStart, colStart) = (0, 4)
    Input 3: destination coordinate (rowDest, colDest) = (3, 2)
    Output: false
    Explanation: There is no way for the ball to stop at the destination.

 

Note:
    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/

public class _0490_The_Maze {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        boolean[][] visited = new boolean[row][col];
        // BFS
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            for (int[] dir : dirs) {
                int x = node[0] + dir[0];
                int y = node[1] + dir[1];
                while (x >= 0 && y >= 0 && x < row && y < col && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                }
                x -= dir[0];
                y -= dir[1];
                if (!visited[x][y]) {
                    visited[x][y] = true;
                    if (destination[0] == x && destination[1] == y) {
                        return true;
                    }
                    queue.offer(new int[] { x, y });
                }
            }
        }
        return false;
    }
}
