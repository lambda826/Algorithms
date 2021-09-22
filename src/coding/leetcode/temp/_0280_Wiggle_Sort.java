/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

/*

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....


Example:
    Input: nums = [3,5,2,1,6,4]
    Output: One possible answer is [3,5,1,6,2,4]

*/

public class _0280_Wiggle_Sort {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void wiggleSort(int[] nums) {
        if (nums.length > 1) {
            boolean asc = true;
            for (int i = 1; i < nums.length; i++) {
                if (asc) {
                    if (nums[i] < nums[i - 1]) {
                        swap(nums, i - 1, i);
                    }
                } else if (nums[i] > nums[i - 1]) {
                    swap(nums, i - 1, i);
                }
                asc = !asc;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
