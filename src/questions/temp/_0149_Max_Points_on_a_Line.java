/**
 * @author Yunxiang He
 * @date 03/23/2019
 */

package questions.temp;

import common.Point;

/*

Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.


Example 1:
    Input: [[1,1],[2,2],[3,3]]
    Output: 3
    Explanation:
    ^
    |
    |        o
    |     o
    |  o  
    +------------->
    0  1  2  3  4

Example 2:
    Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
    Output: 4
    Explanation:
    ^
    |
    |  o
    |     o        o
    |        o
    |  o        o
    +------------------->
    0  1  2  3  4  5  6

*/

public class _0149_Max_Points_on_a_Line {

    public static void main(String[] args) {
        _0149_Max_Points_on_a_Line test = new _0149_Max_Points_on_a_Line();
        test.maxPoints(new Point[] { new Point(1, 1), new Point(1, 1), new Point(2, 2), new Point(2, 2), new Point(2, 2) });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxPoints(Point[] points) {
        if (points.length < 3) {
            return points.length;
        }
        int max = 2;
        for (int i = 1; i < points.length; i++) {
            int count = 0;
            // edge
            long x1 = points[i].x;
            long y1 = points[i].y;
            long x2 = points[i - 1].x;
            long y2 = points[i - 1].y;
            if (x1 == x2 && y1 == y2) { // overlapping points
                for (Point p : points) {
                    if (p.x == x1 && p.y == y1) {
                        count++;
                    }
                }
            } else { // non-overlapping points
                for (Point p : points) {
                    if ((p.x - x1) * (y1 - y2) == (p.y - y1) * (x1 - x2)) {
                        count++;
                    }
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
