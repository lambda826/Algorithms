/**
 *  @author Yunxiang He
 *  @date 2018-07-20 15:51
 */

package coding.leetcode.temp;

import java.util.HashMap;
import java.util.Map;

/*

Given n points in the plane that are all pairwise distinct, 
a "boomerang" is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).

Find the number of boomerangs. 
You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).

Example:
Input:
[[0,0],[1,0],[2,0]]
Output:
2
Explanation:
The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]

*/

public class _0447_Number_of_Boomerangs {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    int x = points[i][0] - points[j][0];
                    int y = points[i][1] - points[j][1];
                    int dis = x * x + y * y;
                    map.put(dis, map.getOrDefault(dis, 0) + 1);
                }
            }
            for (int c : map.values()) {
                if (c > 1) {
                    count += c * (c - 1);
                }
            }
            map.clear();
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new _0447_Number_of_Boomerangs().numberOfBoomerangs(new int[][] { { 0, 0 }, { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } }));
    }
}
