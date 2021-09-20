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
    1 <= nums.length <= 2 * 10000
    -1000 <= nums[i] <= 1000
    -10000000 <= k <= 10000000

*/

public class _0560_Subarray_Sum_Equals_K {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Given three points in terms of sequence: A1, A2, A3, and sum[A2, A3] = k;
    // Then we have sum[A2, A3] = sum[A1, A3] - sum[A1, A2];
    public class Solution {

        public int subArraySum(int[] nums, int k) {
            int count = 0;
            int sum = 0;
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
    public class Solution_BruteForce {

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