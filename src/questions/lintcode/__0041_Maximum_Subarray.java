/**
 * @author Yunxiang He
 * @date 04/12/2019
 */

package questions.lintcode;

/*

Given an array of integers, find a contiguous subarray which has the largest sum.


Example
    Example1:
    Given the array [−2,2,−3,4,−1,2,1,−5,3], the contiguous subarray [4,−1,2,1] has the largest sum = 6.
    
    Example2:
    Given the array [1,2,3,4], the contiguous subarray [1,2,3,4] has the largest sum = 10.


Challenge
    Can you do it in time complexity O(n)?


Notice
    The subarray should contain at least one number.

*/

public class __0041_Maximum_Subarray {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < n; ++i) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
