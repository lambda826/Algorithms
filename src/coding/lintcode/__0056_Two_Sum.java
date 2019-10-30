/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-09
 */

package coding.lintcode;

import java.util.HashMap;
import java.util.Map;

/*

Given an array of integers, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are zero-based.


Example
    numbers=[2, 7, 11, 15], target=9
    return [0, 1]


Challenge
    Either of the following solutions are acceptable:
        O(n) Space, O(nlogn) Time
        O(n) Space, O(n) Time


Notice
    You may assume that each input would have exactly one solution
    
*/

public class __0056_Two_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                res[0] = map.get(numbers[i]);
                res[1] = i;
            } else {
                map.put(target - numbers[i], i);
            }
        }
        return res;
    }
}
