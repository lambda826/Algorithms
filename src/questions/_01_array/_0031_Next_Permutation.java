package questions._01_array;

/*

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
The replacement must be in place and use only constant extra memory.


Example 1:
    Input:
        nums = [1,2,3]
    Output:
        [1,3,2]

Example 2:
    Input:
        nums = [3,2,1]
    Output:
        [1,2,3]

Example 3:
    Input:
        nums = [1,1,5]
    Output:
        [1,5,1]

Example 4:
    Input:
        nums = [1]
    Output:
        [1]


Constraints:
    1 <= nums.length <= 100
    0 <= nums[i] <= 100

*/

public class _0031_Next_Permutation {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. One pass from right to left to find the first decreasing element;
    // 2. Swap the element with the elements right to it which is least greater than it;
    // 3. Reverse the right part.
    class Solution {

        public void nextPermutation(int[] nums) {
            int pivot = nums.length - 2;
            while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
                --pivot;
            }
            // If pivot is less than 0, it means the array is monotonic increasing, no need to swap
            if (pivot >= 0) {
                int p = nums.length - 1;
                while (nums[pivot] >= nums[p]) {
                    --p;
                }
                swap(nums, pivot, p);
            }
            reverse(nums, pivot + 1, nums.length - 1);
        }

        private void reverse(int[] nums, int p1, int p2) {
            while (p1 < p2) {
                swap(nums, p1, p2);
                ++p1;
                --p2;
            }
        }

        private void swap(int[] nums, int p1, int p2) {
            int temp = nums[p1];
            nums[p1] = nums[p2];
            nums[p2] = temp;
        }
    }

}