/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package questions.temp;

/*

Your are given an array of positive integers nums.
Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.


Example 1:
    Input: nums = [10, 5, 2, 6], k = 100
    Output: 8
    Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
    Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.


Note:
    0 < nums.length <= 50000.
    0 < nums[i] < 1000.
    0 <= k < 10^6.

*/

public class _0713_Subarray_Product_Less_Than_K {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        int prdt = 1;
        for (int i = 0, j = 0; i < nums.length; i++) {
            prdt *= nums[i];
            while (prdt >= k && j <= i) {
                prdt /= nums[j];
                j++;
            }
            count += (i - j + 1);
        }
        return count;
    }
}
