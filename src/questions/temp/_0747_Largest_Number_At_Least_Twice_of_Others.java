/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:14 PM
 */

package questions.temp;

/*

In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:
Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  
The index of value 6 is 1, so we return 1.

Example 2:
Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.
 
Note:
nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].

*/

public class _0747_Largest_Number_At_Least_Twice_of_Others {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int _1max = nums[0] > nums[1] ? 0 : 1;
        int _2max = nums[0] < nums[1] ? 0 : 1;
        for (int i = 2; i < nums.length; i++) {
            if (nums[_1max] < nums[i]) {
                _2max = _1max;
                _1max = i;
            } else if (nums[_2max] < nums[i]) {
                _2max = i;
            }
        }
        return nums[_1max] >= 2 * nums[_2max] ? _1max : -1;
    }
}
