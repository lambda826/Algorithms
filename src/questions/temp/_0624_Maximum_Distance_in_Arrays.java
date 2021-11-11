/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-29 03:06
 */

package questions.temp;

import java.util.List;

/*

Given m arrays, and each array is sorted in ascending order. 
Now you can pick up two integers from two different arrays (each array picks one) and calculate the distance. 
We define the distance between two integers a and b to be their absolute difference |a-b|. Your task is to find the maximum distance.

Example 1:
Input: 
[[1,2,3],
 [4,5],
 [1,2,3]]
Output: 4
Explanation: 
One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.

Note:
Each given array will have at least 1 number. There will be at least two non-empty arrays.
The total number of the integers in all the m arrays will be in the range of [2, 10^4].
The integers in the m arrays will be in the range of [-10^4, 10^4].

*/

public class _0624_Maximum_Distance_in_Arrays {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Tips: min and max can appear in the same list
    public int maxDistance(List<List<Integer>> arrays) {
        List<Integer> first = arrays.get(0);
        int min = first.get(0);
        int max = first.get(first.size() - 1);
        int res = Integer.MIN_VALUE;
        ;
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> list = arrays.get(i);
            res = Math.max(res, max - list.get(0));
            res = Math.max(res, list.get(list.size() - 1) - min);
            min = Math.min(min, list.get(0));
            max = Math.max(max, list.get(list.size() - 1));
        }
        return res;
    }
}
