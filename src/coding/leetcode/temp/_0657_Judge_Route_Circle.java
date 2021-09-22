package coding.leetcode.temp;

/*

There is a robot starting at position (0, 0), the origin, on a 2D plane.
Given a sequence of its moves, judge if this robot ends up at (0, 0) after it completes its moves.

The move sequence is represented by a string, and the character moves[i] represents its ith move.
Valid moves are R (right), L (left), U (up), and D (down).
If the robot returns to the origin after it finishes all of its moves, return true.
Otherwise, return false.


Example 1:
    Input: "UD"
    Output: true
    Explanation:
    The robot moves up once, and then down once.
    All moves have the same magnitude, so it ended up at the origin where it started.
    Therefore, we return true.

Example 2:
    Input: "LL"
    Output: false
    Explanation:
    The robot moves left twice.
    It ends up two "moves" to the left of the origin.
    We return false because it is not at the origin at the end of its moves.


Note:
    The way that the robot is "facing" is irrelevant.
    "R" will always make the robot move to the right once, "L" will always make it move left, etc.
    Also, assume that the magnitude of the robot's movement is the same for each move.

*/

public class _0657_Judge_Route_Circle {

    /**
     * 1. Define the original as (0, 0)
     * 2. Go through the string, move correspondingly
     * 3. Check whether the final position is (0, 0)
     */
    public boolean judgeCircle(String moves) {
        int h = 0;
        int v = 0;
        for (char move : moves.toCharArray()) {
            if (move == 'U') {
                ++v;
            } else if (move == 'D') {
                --v;
            } else if (move == 'L') {
                --h;
            } else {
                ++h;
            }
        }
        return h == 0 && v == 0;
    }

}