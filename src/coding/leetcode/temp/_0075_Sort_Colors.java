/**
 *  @author Yunxiang He
 *  @date 06/22/2018
 */

package coding.leetcode.temp;

/*

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.


Example:
    Input: [2,0,2,1,1,0]
    Output: [0,0,1,1,2,2]


Follow up:
    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
    Could you come up with a one-pass algorithm using only constant space?

*/

public class _0075_Sort_Colors {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void sortColors(int[] nums) {
        int zero = 0;
        int two = nums.length - 1;
        int i = 0;
        while (i <= two) {
            if (nums[i] == 0) {
                swap(nums, zero, i);
                zero++;
                i++;
            } else if (nums[i] == 2) {
                swap(nums, two, i);
                two--;
            } else {
                i++;
            }

        }
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        new _0075_Sort_Colors().sortColors(new int[] { 2, 0, 2, 1, 1, 0 });
    }
}
