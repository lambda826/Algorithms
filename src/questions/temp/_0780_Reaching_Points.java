/**
 * @author: Yunxiang He
 * @date : 2018-03-28 17:24
 */

package questions.temp;

/*

A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). 
Otherwise, return False.


Examples:
    Input: sx = 1, sy = 1, tx = 3, ty = 5
    Output: True
    Explanation:
    One series of moves that transforms the starting point to the target is:
    (1, 1) -> (1, 2)
    (1, 2) -> (3, 2)
    (3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True


Note:
    sx, sy, tx, ty will all be integers in the range [1, 10^9].

*/

public class _0780_Reaching_Points {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // If tx_n > ty_n, then (tx_n, ty_n) must come from (tx_n-1 + ty_n-1, ty_n)
    // If tx_n < ty_n, then (tx_n, ty_n) must come from (tx_n, ty_n-1 + tx_n-1)
    public boolean reachingPoints_Math(int sx, int sy, int tx, int ty) {
        if (sx > tx || sy > ty) {
            return false;
        } else if (sx == tx && (ty - sy) % sx == 0) {
            return true;
        } else if (sy == ty && (tx - sx) % sy == 0) {
            return true;
        } else {
            return reachingPoints_Math(sx, sy, tx % ty, ty % tx);
        }
    }

    public static void main(String[] args) {
        _0780_Reaching_Points test = new _0780_Reaching_Points();
        System.out.println(test.reachingPoints_Math(1, 1, 3, 5));
    }
}
