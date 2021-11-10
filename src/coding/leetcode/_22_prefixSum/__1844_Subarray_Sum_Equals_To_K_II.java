package coding.leetcode._22_prefixSum;

import java.util.HashMap;
import java.util.Map;

/*

Given an array of integers and an integer k, you need to find the minimum size of continuous no-empty subarrays whose sum equals to k, and return its length.
if there are no such subarray, return -1.
the integer nums[i] may lower than 0


Example1
    Input:
        nums = [1,1,1,2],
        k = 3
    Output:
        2

Example2
    Input:
        nums = [2,1,-1,4,2,-3],
        k = 3
    Output:
        2

*/
public class __1844_Subarray_Sum_Equals_To_K_II {

    public class Solution_PrefixSum {

        public int subarraySumEqualsKII(int[] nums, int k) {
            Map<Integer, Integer> sumPos = new HashMap<>();
            sumPos.put(0, -1);
            int min = Integer.MAX_VALUE;
            int sum = 0;
            for (int i = 0; i < nums.length; ++i) {
                sum += nums[i];
                sumPos.put(sum, i);
                if (sumPos.containsKey(sum - k)) {
                    min = Math.min(min, i - sumPos.get(sum - k));
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }
}