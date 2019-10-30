/**
 *  @author Yunxiang He
 *  @date Dec 11, 2017 5:18:21 PM
 */

package coding.temp;

import java.util.HashMap;

/*

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2

Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

*/

public class _0560_Subarray_Sum_Equals_K {
    // O(nn)
    public static int subarraySum1(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; i + j < nums.length; j++) {
                int sum = (map.get(j) == null ? 0 : map.get(j)) + nums[j + i];
                if (sum == k) {
                    count++;
                }
                map.put(j, sum);
            }
        }
        return count;
    }

    // O(n)
    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count = count + map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, -1, 1, -1, 1 };
        int k = 0;
        System.out.println(subarraySum2(nums, k));
    }
}
