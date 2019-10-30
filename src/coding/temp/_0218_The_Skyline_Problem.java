/**
 *  @author Yunxiang He
 *  @date 01/29/2018
 */

package coding.temp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/*

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. 
Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. 
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.


For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. 
    A key point is the left building[1]point of a horizontal line segment. 
Note that the last key point, where the rightmost building building[1]s, is merely used to mark the termination of the skyline, and always has zero height. 
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].


Notes:
    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascbuilding[1]ing order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline. 
    For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

 */

public class _0218_The_Skyline_Problem {

    public static void main(String[] args) {
        int[][] buildings = { { 2, 9, 10 }, { 3, 7, 15 }, { 5, 12, 12 }, { 15, 20, 10 }, { 19, 24, 8 } };
        _0218_The_Skyline_Problem test = new _0218_The_Skyline_Problem();
        test.getSkyline(buildings);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Create height coordinates. We use negative value to denote a start point, and positive value to denote an end point.
    // 2. Sort the heights. 3 corner cases: same start, same end, and end == start.
    // 3. Create a max heap with initial value 0.
    // 4. Go through the heights.
    //   a). If it is a start point, put it into the heap. If the top of heap changes, this point with the new heap value (itself) should be in the final result.
    //   b). If it is an end point,  remove it from the heap. If the top of heap changes, this point with the new heap top value should be in the final result.
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
        List<int[]> points = new ArrayList<>();
        for (int[] building : buildings) {
            points.add(new int[] { building[0], -building[2] }); // entering point
            points.add(new int[] { building[1], building[2] }); // leaving point
        }
        Collections.sort(points, (p1, p2) -> (p1[0] - p2[0] != 0 ? p1[0] - p2[0] : p1[1] - p2[1])); // left -> right then low -> high
        TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> (b - a));
        heights.put(0, 1);
        int prevHeight = 0;
        for (int[] point : points) {
            if (point[1] < 0) { // entering
                heights.put(-point[1], heights.getOrDefault(-point[1], 0) + 1);
            } else { // leaving
                if (heights.get(point[1]) == 1) {
                    heights.remove(point[1]);
                } else {
                    heights.put(point[1], heights.get(point[1]) - 1);
                }
            }
            if (heights.firstKey() != prevHeight) {
                result.add(new int[] { point[0], heights.firstKey() });
                prevHeight = heights.firstKey();
            }
        }
        return result;
    }
}
