/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

/*

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
    Input: [1,2,0]
    Output: 3

Example 2:
    Input: [3,4,-1,1]
    Output: 2

Example 3:
    Input: [7,8,9,11,12]
    Output: 1


Note:
    Your algorithm should run in O(n) time and uses constant extra space.

*/

public class _0041_First_Missing_Positive {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int firstMissingPositive(int[] nums) {
        int left = 0;
        while (left < nums.length) {
            if (nums[left] > 0 && nums[left] < nums.length && nums[left] != left + 1 && nums[left] != nums[nums[left] - 1]) {
                swap(nums, left, nums[left] - 1);
                continue;
            }
            left++;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        _0041_First_Missing_Positive test = new _0041_First_Missing_Positive();
        System.out.println(test.firstMissingPositive(new int[] { 2, 1 }));
    }
}
