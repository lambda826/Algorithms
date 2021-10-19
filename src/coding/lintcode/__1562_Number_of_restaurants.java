/**
 *  @author Yunxiang He
 *  @date 10/04/2018
 */

package coding.lintcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Give a List of data representing the coordinates[x,y] of each restaurant and the customer is at the origin[0,0]. 
Find the n restaurants closest to the customer firstly. 
Then you need to pick n restaurants which appear firstly in the List and the distance between the restaurant and the customer can't more than the longest distance in the n closest restaurants. 
Return their coordinates in the original order.


Example
    Given : n = 2 , List = [[0,0],[1,1],[2,2]]
    Return : [[0,0],[1,1]]
    Given : n = 3,List = [[0,1],[1,2],[2,1],[1,0]]
    Return :[[0,1],[1,2],[2,1]]


Notice
    1.Coordinates in range [-1000,1000]
    2.n>0
    3.No same coordinates

*/

public class __1562_Number_of_restaurants {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Calculate all distances
    // Sort the distances to get the nth maximum distance
    // Go through the original list to count the n lists
    public List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (restaurant == null || restaurant.size() == 0 || n > restaurant.size()) {
            return res;
        }
        int[] dis = new int[restaurant.size()];
        for (int i = 0; i < restaurant.size(); i++) {
            dis[i] = getDis(restaurant.get(i).get(0), restaurant.get(i).get(1));
        }
        Arrays.parallelSort(dis);
        int max = dis[n - 1];
        for (int i = 0; i < restaurant.size(); i++) {
            if (getDis(restaurant.get(i).get(0), restaurant.get(i).get(1)) <= max) {
                res.add(restaurant.get(i));
            }
        }
        return res;
    }

    private int getDis(int x, int y) {
        return x * x + y * y;
    }
}
