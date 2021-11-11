/**
 *  @author Yunxiang He
 *  @date Dec 20, 2017 3:41:52 PM
 */

package questions.temp;

import java.util.Arrays;

/*

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

*/

public class _0016_3Sum_Closest {

    /*
     * This problem is similar to 15.3Sum;
     * The difference is that to record the minimum absolute distance from the target in every iteration
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = sum - target;
                if (diff == 0) {
                    return sum;
                }
                if (diff < 0) {
                    left++;
                } else {
                    right--;
                }
                if (closest > Math.abs(diff)) {
                    closest = Math.min(closest, Math.abs(sum - target));
                    res = sum;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        new _0016_3Sum_Closest().threeSumClosest(new int[] { 1, 1, 1, 0 }, 100);
    }
}
