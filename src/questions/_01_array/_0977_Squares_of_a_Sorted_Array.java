package questions._01_array;

/*

Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.


Example 1:
    Input:
        nums = [-4,-1,0,3,10]
    Output:
        [0,1,9,16,100]
    Explanation:
        After squaring, the array becomes [16,1,0,9,100].
        After sorting, it becomes [0,1,9,16,100].

Example 2:
    Input:
        nums = [-7,-3,2,3,11]
    Output:
        [4,9,9,49,121]


Constraints:
    1 <= nums.length <= 10^4
    -10^4 <= nums[i] <= 10^4
    nums is sorted in non-decreasing order.

*/

public class _0977_Squares_of_a_Sorted_Array {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int lo = 0;
        int hi = nums.length - 1;
        for (int i = hi; i >= 0; --i) {
            if (nums[lo] < 0 && -nums[lo] > nums[hi]) {
                res[i] = nums[lo] * nums[lo];
                ++lo;
            } else {
                res[i] = nums[hi] * nums[hi];
                --hi;
            }
        }
        return res;
    }

}