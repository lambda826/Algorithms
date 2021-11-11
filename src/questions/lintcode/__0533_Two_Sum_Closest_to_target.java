/**
 *  @author Yunxiang He
 *  @date 02/28/2019
 */

package questions.lintcode;

import java.util.Arrays;

/*

Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.

Return the difference between the sum of the two integers and the target.


Example
Example1
    Input:  nums = [-1, 2, 1, -4] and target = 4
    Output: 1
    Explanation:
    The minimum difference is 1. (4 - (2 + 1) = 1).

Example2
    Input:  nums = [-1, -1, -1, -4] and target = 4
    Output: 6
    Explanation:
    The minimum difference is 6. (4 - (- 1 - 1) = 6).


Challenge
    Do it in O(nlogn) time complexity.

*/

public class __0533_Two_Sum_Closest_to_target {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int twoSumClosest(int[] nums, int target) {
        Arrays.parallelSort(nums);
        int l = 0;
        int r = nums.length - 1;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while (l < r) {
            sum = nums[l] + nums[r];
            if (sum == target) {
                return 0;
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
            min = Math.min(min, (int) Math.abs(sum - target));
        }
        return min;
    }
}
