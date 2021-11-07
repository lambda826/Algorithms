package coding.leetcode._17_slidingWindow;

/*

You are given an array points, an integer angle, and your location, where location = [pos(x), pos(y)] and points[i] = [xi, yi] both denote integral coordinates on the X-Y plane.

Initially, you are facing directly east from your position.
    You cannot move from your position, but you can rotate. In other words, pos(x) and pos(y) cannot be changed.
    Your field of view in degrees is represented by angle, determining how wide you can see from any given view direction.
        Let d be the amount in degrees that you rotate counterclockwise.
        Then, your field of view is the inclusive range of angles [d - angle/2, d + angle/2].

You can see some set of points if, for each point, the angle formed by the point, your position, and the immediate east direction from your position is in your field of view.
    There can be multiple points at one coordinate.
    There may be points at your location, and you can always see these points regardless of your rotation.
    Points do not obstruct your vision to other points.

Return the maximum number of points you can see.


Example 1:
    Input:
        points = [[2,1],[2,2],[3,3]],
        angle = 90,
        location = [1,1]
    Output:
        3
    Explanation:
        The shaded region represents your field of view.
        All points can be made visible in your field of view, including [3,3] even though [2,2] is in front and in the same line of sight.

Example 2:
    Input:
        points = [[2,1],[2,2],[3,4],[1,1]],
        angle = 90,
        location = [1,1]
    Output:
        4
    Explanation:
        All points can be made visible in your field of view, including the one at your location.

Example 3:
    Input:
        points = [[1,0],[2,1]],
        angle = 13,
        location = [1,1]
    Output:
        1
    Explanation:
        You can only see one of the two points, as shown above.


Constraints:
    1 <= points.length <= 10^5
    points[i].length == 2
    location.length == 2
    0 <= angle < 360
    0 <= pos(x), pos(y), x(i), y(i) <= 100

*/

import java.util.Arrays;
import java.util.List;

public class _1610_Maximum_Number_of_Visible_Points {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Compute the degrees of each angle of between the points and the original point;
    // 2. Sort the degrees;
    // 3. Slide window to compute whether the right candidate is still within the window, which is the given angle.
    //
    // Note:
    //      1. Utilizing Math.atan2(Y, X) lab to compute the degree;
    //      2. Math.atan2(0, 0) returns 0, which we need to use another variable to count;
    //      3. Since it's a circle, we could double the size of the degrees by adding 360 to each of the original ones.
    class Solution_SlideWindow {
        public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
            double[] degrees = new double[points.size() * 2];
            int index = 0;
            int originalPoint = 0;
            for (int i = 0; i < points.size(); ++i) {
                int x = points.get(i).get(0) - location.get(0);
                int y = points.get(i).get(1) - location.get(1);
                if (x == 0 && y == 0) {
                    ++originalPoint;
                } else {
                    double degree = Math.atan2(y, x) * 180 / Math.PI;
                    degrees[index] = degree;
                    ++index;
                }
            }
            Arrays.sort(degrees, 0, index);
            int max = 0;
            for (int left = 0, right = 0; left < index; ++left) {
                degrees[left + index] = degrees[left] + 360;
                while (degrees[right] - degrees[left] <= angle) {
                    ++right;
                }
                max = Math.max(max, right - left);
            }
            return max + originalPoint;
        }

    }
}