/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-27
 */

package coding.leetcode.temp;

/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.


Example:
    Given nums = [1, 3, 5]
    sumRange(0, 2) -> 9
    update(1, 2)
    sumRange(0, 2) -> 8


Note:
    The array is only modifiable by the update function.
    You may assume the number of calls to update and sumRange function is distributed evenly.

*/

public class _0307_Range_Sum_Query___Mutable {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] dp;
    private int[] nums;

    public _0307_Range_Sum_Query___Mutable(int[] nums) {
        this.nums = nums;
        dp = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i + 1] = nums[i] + dp[i];
        }
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        for (int index = i + 1; index < dp.length; index++) {
            dp[index] += diff;
        }
    }

    public int sumRange(int i, int j) {
        return dp[j + 1] - dp[i];
    }
}
