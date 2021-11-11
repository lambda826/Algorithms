/**
 *  @author: Yunxiang He
 *  @date  : 2018-07-30 15:05
 */

package questions.temp;

import java.util.Arrays;
import java.util.Random;

/*

Given a list of non-overlapping axis-aligned rectangles rects, write a function pick which randomly and uniformily picks an integer point in the space covered by the rectangles.

Note:
An integer point is a point that has integer coordinates. 
A point on the perimeter of a rectangle is included in the space covered by the rectangles. 
ith rectangle = rects[i] = [x1,y1,x2,y2], where [x1, y1] are the integer coordinates of the bottom-left corner, and [x2, y2] are the integer coordinates of the top-right corner.
length and width of each rectangle does not exceed 2000.
1 <= rects.length <= 100
pick return a point as an array of integer coordinates [p_x, p_y]
pick is called at most 10^4 times.

Example 1:
Input: 
["Solution","pick","pick","pick"]
[[[[1,1,5,5]]],[],[],[]]
Output: 
[null,[4,1],[4,1],[3,3]]

Example 2:
Input: 
["Solution","pick","pick","pick","pick","pick"]
[[[[-2,-2,-1,-1],[1,0,3,0]]],[],[],[],[],[]]
Output: 
[null,[-1,-2],[2,0],[-2,-1],[3,0],[-2,-2]]
Explanation of Input Syntax:
The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array of rectangles rects. pick has no arguments. Arguments are always wrapped with a list, even if there aren't any.

*/

public class _0497_Random_Point_in_Non_overlapping_Rectangles {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Pick a rectangle
    // Then generate x, and y
    int[] areas;
    int[][] rects;
    Random random;

    public _0497_Random_Point_in_Non_overlapping_Rectangles(int[][] rects) {
        this.rects = rects;
        random = new Random();
        areas = new int[rects.length];
        // Not compute the area but the number of points
        areas[0] = (rects[0][2] - rects[0][0] + 1) * (rects[0][3] - rects[0][1] + 1);
        for (int i = 1; i < rects.length; i++) {
            areas[i] = areas[i - 1] + (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
        }
    }

    public int[] pick() {
        int index = Arrays.binarySearch(areas, random.nextInt(areas[areas.length - 1]) + 1);
        int[] rect = index >= 0 ? rects[index] : rects[-index - 1];
        return new int[] { random.nextInt(rect[2] - rect[0] + 1) + rect[0], random.nextInt(rect[3] - rect[1] + 1) + rect[1] };
    }

    public static void main(String[] args) {
        int[][] rects = { { 1, 1, 5, 5 } };
        _0497_Random_Point_in_Non_overlapping_Rectangles test = new _0497_Random_Point_in_Non_overlapping_Rectangles(rects);
        System.out.println(Arrays.toString(test.pick()));
    }
}
