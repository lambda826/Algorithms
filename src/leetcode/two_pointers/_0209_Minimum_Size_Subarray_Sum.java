package leetcode.two_pointers;

/*

Given an array of positive integers nums and a positive integer target, return the minimal length of a
subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.


Example 1:
    Input: target = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: The subarray [4,3] has the minimal length under the problem constraint.

Example 2:
    Input: target = 4, nums = [1,4,4]
    Output: 1

Example 3:
    Input: target = 11, nums = [1,1,1,1,1,1,1,1]
    Output: 0


Constraints:
    1 <= target <= 10^9
    1 <= nums.length <= 10^5
    1 <= nums[i] <= 10^4

Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).

*/
public class _0209_Minimum_Size_Subarray_Sum {

    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minSubArrayLen_BinarySerach(int s, int[] nums) {
        int minLength = nums.length + 1;
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] += nums[i - 1] + sums[i - 1];
        }
        int lo = 0;
        int hi = nums.length;
        outter:
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            for (int i = mid; i < sums.length; i++) {
                if (sums[i] - sums[i - mid] >= s) {
                    hi = mid - 1;
                    minLength = mid;
                    continue outter;
                }
            }
            lo = mid + 1;
        }
        return minLength == nums.length + 1 ? 0 : minLength;
    }

    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int minLen = Integer.MAX_VALUE;
            int sum = 0;
            int left = 0;
            int right = 0;
            while (right < nums.length) {
                sum += nums[right++];
                while (sum >= target) {
                    minLen = Math.min(minLen, right - left);
                    sum -= nums[left++];
                }
            }
            return minLen == Integer.MAX_VALUE ? 0 : minLen;
        }
    }

}
