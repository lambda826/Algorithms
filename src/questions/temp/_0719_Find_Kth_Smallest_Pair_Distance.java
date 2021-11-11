/**
 *  @author Yunxiang He
 *  @date 12/29/2017
 */

package questions.temp;

import java.util.Arrays;

/*

Given an integer array, return the k-th smallest distance among all the pairs. 
The distance of a pair (A, B) is defined as the absolute difference between A and B.

Example 1:
    Input:
        nums = [1,3,1]
        k = 1
    Output: 
        0 
    Explanation:
        Here are all the pairs:
        (1,3) -> 2
        (1,1) -> 0
        (3,1) -> 2
        Then the 1st smallest distance pair is (1,1), and its distance is 0.


Note:
    2 <= len(nums) <= 10^4.
    0 <= nums[i] < 10^400.
    1 <= k <= len(nums) * (len(nums) - 1) / 2.

 */

public class _0719_Find_Kth_Smallest_Pair_Distance {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // binary search + sliding window
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int count = 0;
            // sliding window, fix right end
            // slide right end by one step each time
            // slide left end to satisfy the condition
            for (int l = 0, r = 1; r < nums.length; ++r) {
                while (nums[r] - nums[l] > mid) {
                    ++l;
                }
                count += r - l;
            }
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // bucket
    public int smallestDistancePair2(int[] nums, int k) {
        Arrays.sort(nums);
        int N = nums[nums.length - 1];
        int[] buckets = new int[N + 1];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i + 1; j < nums.length; ++j) {
                buckets[nums[j] - nums[i]]++;
            }
        }
        int count = 0;
        int i = 0;
        while (count < k) {
            count += buckets[i];
            ++i;
        }
        return i - 1;
    }

}
