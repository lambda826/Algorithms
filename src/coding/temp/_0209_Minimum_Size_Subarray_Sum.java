/**
 *  @author Yunxiang He
 *  @date Jan 25, 2018 3:29:13 AM
 */

package coding.temp;

/*

Given an array of n positive integers and a positive integer s, 
find the minimal length of a contiguous subarray of which the sum ≥ s. 
If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

Credits:
Special thanks to @Freezen for adding this problem and creating all test cases.

 */

public class _0209_Minimum_Size_Subarray_Sum {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minSubArrayLen_TwoPointers(int s, int[] nums) {
        int left = 0;
        int right = 0;
        int sum = 0;
        // Initialize minLength to nums.length + 1 in order to check the condition of total sum is less than s
        int minLength = nums.length + 1;
        while (right < nums.length) {
            sum += nums[right++];
            while (sum >= s) {
                // Should update minLength first and then to update the sum
                minLength = Math.min(minLength, right - left);
                sum -= nums[left++];
            }
        }
        return minLength == nums.length + 1 ? 0 : minLength;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

    public static void main(String[] args) {
        _0209_Minimum_Size_Subarray_Sum test = new _0209_Minimum_Size_Subarray_Sum();
        int[] nums = { 2, 3, 1, 2, 4, 3 };
        int s = 8;
        System.out.println(test.minSubArrayLen_TwoPointers(s, nums));
        System.out.println(test.minSubArrayLen_BinarySerach(s, nums));
    }
}
