/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

/*

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
The replacement must be in-place and use only constant extra memory.


Here are some examples. 
    Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1

*/

public class _0031_Next_Permutation {

    public static void main(String[] args) {
        _0031_Next_Permutation test = new _0031_Next_Permutation();
        test.nextPermutation(new int[] { 2, 3, 1 });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // One pass from right to left to find the first decreasing element
    // Swap the element with the elements right to it which is just greater than it
    // Reverse the right part
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public void reverse(int[] nums, int l, int r) {
        while (l < r) {
            swap(nums, l, r);
            ++l;
            --r;
        }
    }

}
