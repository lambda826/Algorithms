/**
 *  @author Yunxiang He
 *  @date 12/17/2017
 */

package coding.temp;

/*

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].


Example:
    Input:  [1,2,3,4]
    Output: [24,12,8,6]


Note: 
    Please solve it without division and in O(n).


Follow up:
    Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

*/

public class _0238_Product_of_Array_Except_Self {

    public static void main(String[] args) {
        new _0238_Product_of_Array_Except_Self().productExceptSelf(new int[] { 0, 1, 2, 3 });
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int left = 1;
        for (int i = 0; i < nums.length; ++i) {
            res[i] = left;
            left *= nums[i];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; --i) {
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }
}
