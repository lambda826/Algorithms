/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-30 17:17
 */

package coding.temp;

/*

Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. 
And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

*/

public class _0643_Maximum_Average_Subarray_I {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public double findMaxAverage(int[] nums, int k) {
        double max = Double.MIN_VALUE;
        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        max = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            max = Math.max(max, sum);
        }
        return max / k;
    }

    public static void main(String[] args) {
        _0643_Maximum_Average_Subarray_I test = new _0643_Maximum_Average_Subarray_I();
        test.findMaxAverage(new int[] { 4, 0, 4, 3, 3 }, 5);
    }
}
