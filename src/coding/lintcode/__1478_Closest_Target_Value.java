/**
 *  @author Yunxiang He
 *  @date 02/28/2019
 */

package coding.lintcode;

import java.util.Arrays;

/*

Given an array, find two numbers in the array and their sum is closest to the target value but does not exceed the target value, return their sum.


Example
Example1
    Input: target = 15 and array = [1,3,5,11,7]
    Output: 14
    Explanation: 
    11+3=14

Example2
    Input: target = 16 and array = [1,3,5,11,7]
    Output: 16
    Explanation: 
    11+5=16


Notice
if there is no result meet the requirement, return -1.

*/

public class __1478_Closest_Target_Value {

    public static void main(String[] args) {
        __1478_Closest_Target_Value test = new __1478_Closest_Target_Value();
        test.closestTargetValue(-10, new int[] { -12, -5, 14, 5, 0, -16 });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int closestTargetValue(int target, int[] array) {
        Arrays.parallelSort(array);
        int l = 0;
        int r = array.length - 1;
        int max = -1;
        while (l < r) {
            int sum = array[l] + array[r];
            if (sum == target) {
                return sum;
            } else if (sum > target) {
                --r;
            } else {
                max = Math.max(max, sum);
                ++l;
            }
        }
        return max == Integer.MIN_VALUE ? -1 : max;
    }
}
