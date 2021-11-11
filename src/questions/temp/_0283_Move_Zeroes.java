/**
 *  @author Yunxiang He
 *  @date 12/17/2018
 */

package questions.temp;

/*

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.


Example:
    Input: [0,1,0,3,12]
    Output: [1,3,12,0,0]


Note:
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.

*/

public class _0283_Move_Zeroes {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void moveZeroes(int[] nums) {
        int non0 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[non0++] = nums[i];
            }
        }
        while (non0 < nums.length) {
            nums[non0++] = 0;
        }
    }
}
