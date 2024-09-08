/**
 * @author: Yunxiang He
 * @date : 2018-10-04
 */

package questions.lintcode;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/*


Given a 2D array representing the coordinates on the map, there are only values 0, 1, 2 on the map. 
value 0 means that it can pass, value 1 means not passable, value 2 means target place. 
Starting from the coordinates [0,0],You can only go up, down, left and right. 
Find the shortest path that can reach the destination, and return the length of the path.


Example
    Given:
    
    [
     [0, 0, 0],
     [0, 0, 1],
     [0, 0, 2]
    ]
    Return: 4

Notice
    1.The map must exist and is not empty, there is only one target

*/

public class __1563_Shortest_path_to_the_destination {

    public static void main(String[] args) {
        int[][] targetMap = { { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, { 0, 0, 0, 0, 2, 0, 0, 0, 0, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } };
        System.out.println(new __1563_Shortest_path_to_the_destination().shortestPath(targetMap));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int shortestPath(List<List<Integer>> targetMap) {
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[] { 0, 0 });
        targetMap.get(0).set(0, 0);
        int step = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; ++i) {
                int[] curr = que.poll();
                if (targetMap.get(curr[0]).get(curr[1]) == 9) {
                    return step;
                }
                targetMap.get(curr[0]).set(curr[1], 0);
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x >= 0 && y >= 0 && x < targetMap.size() && y < targetMap.get(0).size() && targetMap.get(x).get(y) != 0) {
                        que.offer(new int[] { x, y });
                    }
                }
            }
            ++step;
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int shortestPath(int[][] targetMap) {
        int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[] { 0, 0 });
        targetMap[0][0] = 1;
        int step = 0;
        while (!que.isEmpty()) {
            int size = que.size();
            for (int i = 0; i < size; ++i) {
                int[] curr = que.poll();
                if (targetMap[curr[0]][curr[1]] == 2) {
                    return step;
                }
                targetMap[curr[0]][curr[1]] = 0;
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x >= 0 && y >= 0 && x < targetMap.length && y < targetMap[0].length && targetMap[x][y] != 0) {
                        que.offer(new int[] { x, y });
                    }
                }
            }
            ++step;
        }
        return -1;
    }

}
