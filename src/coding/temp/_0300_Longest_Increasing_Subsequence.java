/**
 *  @author Yunxiang He
 *  @date 01/25/2018
 */

package coding.temp;

import java.util.Arrays;

/*

Given an unsorted array of integers, find the length of longest increasing subsequence.


Example:
    Input: [10,9,2,5,3,7,101,18]
    Output: 4 
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 


Note:
    There may be more than one LIS combination, it is only necessary for you to return the length.
    Your algorithm should run in O(n ^ 2) complexity.


Follow up: 
    Could you improve it to O(n log n) time complexity?

*/

public class _0300_Longest_Increasing_Subsequence {

    public static void main(String[] args) {
        int[] nums = { 3, 5, 2, 2, 5, 4, 19, 5, 6, 7, 12 };
        _0300_Longest_Increasing_Subsequence test = new _0300_Longest_Increasing_Subsequence();
        System.out.println(test.lengthOfLIS_BinarySearch(nums));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int lengthOfLIS_BinarySearch(int[] nums) {
        int len = 0;
        int[] arr = new int[nums.length];
        for (int num : nums) {
            int index = Arrays.binarySearch(arr, 0, len, num);
            if (index < 0) {
                arr[-index - 1] = num;
                if (-index - 1 == len) {
                    len++;
                }
            }
        }
        return len;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j >= 0; --j) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max + 1;
    }

}
