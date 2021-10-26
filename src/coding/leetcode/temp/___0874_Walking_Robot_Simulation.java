/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-31 15:17
 */

package coding.leetcode.temp;

import java.util.HashSet;
import java.util.Set;

/*

A robot on an infinite grid starts at point (0, 0) and faces north.  
The robot can receive one of three possible types of commands:

-2: turn left 90 degrees
-1: turn right 90 degrees
1 <= x <= 9: move forward x units
Some of the grid squares are obstacles. 

The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])

If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)

Return the square of the maximum Euclidean distance that the robot will be from the origin.

 
Example 1:
Input: commands = [4,-1,3], obstacles = []
Output: 25
Explanation: robot will go to (3, 4)

Example 2:
Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
Output: 65
Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)

Note:
0 <= commands.length <= 10^4
0 <= obstacles.length <= 10^4
-30000 <= obstacle[i][0] <= 30000
-30000 <= obstacle[i][1] <= 30000
The answer is guaranteed to be less than 2 ^ 31.

*/

public class ___0874_Walking_Robot_Simulation {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        int res = 0;
        for (int[] obstacle : obstacles) {
            set.add(obstacle[0] + "," + obstacle[1]);
        }
        int x = 0;
        int y = 0;
        int z = 0;
        int[][] directions = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int command : commands) {
            if (command > 0) {
                int i = 1;
                while (i <= command) {
                    x += directions[z][0];
                    y += directions[z][1];
                    if (set.contains(x + "," + y)) {
                        x -= directions[z][0];
                        y -= directions[z][1];
                        break;
                    }
                    i++;
                }
                res = Math.max(res, x * x + y * y);
            } else if (command == -1) {
                z = (z + 1 + 4) % 4;
            } else {
                z = (z - 1 + 4) % 4;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Set<int[]> set = new HashSet<>();
        set.add(new int[] { 0, 0 });
        System.out.println(set.contains(new int[] { 0, 0 }));
    }
}
