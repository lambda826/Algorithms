/**
 * @author Yunxiang He
 * @date 04/11/2019
 */

package questions.temp;

import java.util.Arrays;
import java.util.PriorityQueue;

/*

There is a ball in a maze with empty spaces and walls. 
    The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
    When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
    The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
    If the ball cannot stop at the destination, return -1.

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
    Input 2: 
        start coordinate (rowStart, colStart) = (0, 4)
    Input 3: 
        destination coordinate (rowDest, colDest) = (4, 4)
    Output: 
        12
    Explanation: 
        One shortest way is : left -> down -> left -> down -> right -> down -> right.
        The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.


Example 2:
    Input 1: a maze represented by a 2D array
        0 0 1 0 0
        0 0 0 0 0
        0 0 0 1 0
        1 1 0 1 1
        0 0 0 0 0
    Input 2:
        start coordinate (rowStart, colStart) = (0, 4)
    Input 3: 
        destination coordinate (rowDest, colDest) = (3, 2)
    Output: 
        -1
    Explanation: 
        There is no way for the ball to stop at the destination.


Note:
    There is only one ball and one destination in the maze.
    Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

*/

public class _0505_The_Maze_II {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // dijkstra
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int row = maze.length;
        int col = maze[0].length;
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        // initialize distance to be infinite
        int[][] distances = new int[row][col];
        for (int[] distance : distances) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        // shortest path
        // dijkstra
        distances[start[0]][start[1]] = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((d1, d2) -> (distances[d1[0]][d1[1]] - distances[d2[0]][d2[1]]));
        minHeap.offer(start);
        while (!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            int current_shortest = distances[node[0]][node[1]]; // the shortest distance to the current node
            for (int[] dir : dirs) {
                int x = node[0] + dir[0];
                int y = node[1] + dir[1];
                int step = 0;
                // hit the wall
                while (x >= 0 && y >= 0 && x < row && y < col && maze[x][y] == 0) {
                    x += dir[0];
                    y += dir[1];
                    ++step;
                }
                x -= dir[0];
                y -= dir[1];
                // hit the wall end
                if (current_shortest + step < distances[x][y]) {
                    distances[x][y] = current_shortest + step;
                    if (destination[0] == x && destination[1] == y) { // goal test
                        return distances[destination[0]][destination[1]];
                    }
                    minHeap.offer(new int[] { x, y });
                }
            }
        }
        return -1;
    }
}
