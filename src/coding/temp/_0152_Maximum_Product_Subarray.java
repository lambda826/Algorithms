/**
 *  @author Yunxiang He
 *  @date 01/10/2018
 */

package coding.temp;

/*

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.


Example 1:
    Input: [2,3,-2,4]
    Output: 6
    Explanation: [2,3] has the largest product 6.

Example 2:
    Input: [-2,0,-1]
    Output: 0
    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

*/

public class _0152_Maximum_Product_Subarray {

    public static void main(String[] args) {
        _0152_Maximum_Product_Subarray test = new _0152_Maximum_Product_Subarray();
        int[] nums = { 2, -5, -4, 0, 3 };
        System.out.println(test.maxProduct(nums));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int _max = max;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            _max = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(_max * nums[i], min * nums[i]), nums[i]);
            res = Math.max(res, max);
        }
        return res;
    }
}
