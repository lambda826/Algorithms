package coding.leetcode._01_array;

import java.util.HashMap;
import java.util.Map;

/*

Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.


Example 1:
    Input:
        nums = [1,1,1],
        k = 2
    Output: 2

Example 2:
    Input:
        nums = [1,2,3],
        k = 3
    Output:
        2


Constraints:
    1 <= nums.length <= 2 * 10^4
    -1000 <= nums[i] <= 1000
    -10^7 <= k <= 10^7

*/

public class _0560_Subarray_Sum_Equals_K {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Iterate the array and sum up the numbers;
    // 2. Use a map to record the <sum, count>
    // 3. Use current sum to subtract K, we can get the count of previous sub-arrays
    //
    // For example:
    //      K = 5;
    //      A1 + A2 = 4;
    //      A1 + A2 + A3 + A4 = 4;
    //      A1 + A2 + A3 + A4 + A5 = 9;
    //      We have:
    //          (A1 + A2 + A3 + A4 + A5) - (A1 + A2) = 9 - 4 = 5; then we know A3 + A4 + A5 = 5;
    //          (A1 + A2 + A3 + A4 + A5) - (A1 + A2 + A3 + A4) = 9 - 4 = 5; then we know A5 = 5;
    class Solution_PrefixSum {

        public int subArraySum(int[] nums, int k) {
            int count = 0;
            int sum = 0;
            // <Sum, count>
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1);
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i];
                if (map.containsKey(sum - k)) {
                    count += map.get(sum - k);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_BruteForce {

        public int subArraySum(int[] nums, int k) {
            int count = 0;
            for (int i = 0; i < nums.length; ++i) {
                int sum = 0;
                for (int j = i; j < nums.length; ++j) {
                    sum += nums[j];
                    if (sum == k) {
                        ++count;
                    }
                }
            }
            return count;
        }
    }
}