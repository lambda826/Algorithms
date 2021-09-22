/**
 *  @author Yunxiang He
 *  @date Dec 16, 2017 4:00:02 AM
 */

package coding.leetcode.temp;

import java.util.Arrays;

/*

Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which makes sum of min(ai, bi) for all i from 1 to n as large as possible.

Example 1:
Input: [1,4,3,2]
Output: 4
Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).

Note:
n is a positive integer, which is in the range of [1, 10000].
All the integers in the array will be in the range of [-10000, 10000].

*/

public class __0561_Array_Partition_I {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // sorted: A, B, C, D
    // Max: min(A, B) + min(C, D) = A + C
    // If min(A, C) + min(B, D) or min(A, D) + min(B, C) = A + B < A + C
    public int arrayPairSum_BucketSort(int[] nums) {
        int sum = 0;
        int[] bucket = new int[20001];
        for (int num : nums) {
            bucket[num + 10000]++;
        }
        boolean isMin = true;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] != 0) {
                if (isMin) {
                    sum += i - 10000;
                }
                isMin = !isMin;
                bucket[i]--;
            }
        }
        return sum;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            sum += nums[i];
        }
        return sum;
    }
}
