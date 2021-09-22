/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-25 20:06
 */

package coding.leetcode.temp;

/*

Given an array of integers nums, write a method that returns the "pivot" index of this array.

We define the pivot index as the index where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.

If no such index exists, we should return -1.
If there are multiple pivot indexes, you should return the left-most pivot index.

Example 1:
Input: 
nums = [1, 7, 3, 6, 5, 6]
Output: 3
Explanation: 
The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
Also, 3 is the first index where this occurs.

Example 2:
Input: 
nums = [1, 2, 3]
Output: -1
Explanation: 
There is no index that satisfies the conditions in the problem statement.

The length of nums will be in the range [0, 10000].
Each element nums[i] will be an integer in the range [-1000, 1000].

*/

public class _0724_Find_Pivot_Index {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Cannot scan from left and most to the meet point, because the convergence is uncertain
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            leftSum += nums[i];
            if (leftSum == sum - leftSum + nums[i]) {
                return i;
            }
        }
        return -1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int pivotIndex2(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == nums[nums.length - 1] - (i - 1 < 0 ? 0 : nums[i - 1])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new _0724_Find_Pivot_Index().pivotIndex(new int[] { -1, -1, -1, -1, 1, 0 }));
    }
}
