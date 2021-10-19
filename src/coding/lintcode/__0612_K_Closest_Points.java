/**
 *  @author Yunxiang He
 *  @date 02/16/2019
 */

package coding.lintcode;

import java.util.Arrays;
import java.util.Comparator;

/*

Given some points and an origin point in two-dimensional space, find k points which are nearest to the origin.
Return these points sorted by distance, if they are same in distance, sorted by the x-axis, and if they are same in the x-axis, sorted by y-axis.


Example 1:
    Input: points = [[4,6],[4,7],[4,4],[2,5],[1,1]], origin = [0, 0], k = 3 
    Output: [[1,1],[2,5],[4,4]]

Example 2:
    Input: points = [[0,0],[0,9]], origin = [3, 1], k = 1
    Output: [[0,0]]

*/

public class __0612_K_Closest_Points {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Point[] kClosest(Point[] points, Point o, int k) {
        // write your code here
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int d1 = (getDis(p1, o));
                int d2 = (getDis(p2, o));
                if (d1 == d2) {
                    if (p1.x == p2.x) {
                        return p1.y - p2.y;
                    } else {
                        return p1.x - p2.x;
                    }
                } else {
                    return d1 - d2;
                }
            }
        });
        return Arrays.copyOf(points, k);
    }

    private int getDis(Point o, Point p) {
        int x = p.x - o.x;
        int y = p.y - o.y;
        return x * x + y * y;
    }
}
