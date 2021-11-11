/**
 *  @author: Yunxiang He
 *  @date  : 2018-10-04
 */

package questions.lintcode;

import java.util.Arrays;

/*

Given an array of integers, find how many pairs in the array such that their sum is less than or equal to a specific target number. 
Please return the number of pairs.


Example
    Given nums = [2, 7, 11, 15], target = 24.
    Return 5.
    2 + 7 < 24
    2 + 11 < 24
    2 + 15 < 24
    7 + 11 < 24
    7 + 15 < 25

*/

public class __0609_Two_Sum_Less_than_or_equal_to_target {

    public static void main(String[] args) {
        new __0609_Two_Sum_Less_than_or_equal_to_target().twoSum5(new int[] { 2, 7, 11, 15 }, 24);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int twoSum5(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int count = 0;
        while (left < right) {
            // If nums[left] + nums[right] <= target
            // Then nums[left] + nums[the element < right] <= target
            if (nums[left] + nums[right] <= target) {
                count += (right - left);
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

}
