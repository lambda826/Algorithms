/**
 * @author: Yunxiang He
 * @date : 2018-10-09
 */

package questions.lintcode;

/*

Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
Do not allocate extra space for another array, you must do this in place with constant memory.


Example
    Given input array A = [1,1,2],
    Your function should return length = 2, and A is now [1,2].

*/

public class __0100_Remove_Duplicates_from_Sorted_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int removeDuplicates(int[] nums) {
        int index = 0;
        if (nums.length > 0) {
            index = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[index] = nums[i];
                    index++;
                }
            }
        }
        return index;
    }
}
