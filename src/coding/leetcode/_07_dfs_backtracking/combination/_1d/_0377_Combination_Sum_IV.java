package coding.leetcode._07_dfs_backtracking.combination._1d;

/*

Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The answer is guaranteed to fit in a 32-bit integer.


Example 1:
    Input:
        nums = [1,2,3],
        target = 4
    Output:
        7
    Explanation:
        The possible combination ways are:
            (1, 1, 1, 1)
            (1, 1, 2)
            (1, 2, 1)
            (1, 3)
            (2, 1, 1)
            (2, 2)
            (3, 1)
        Note that different sequences are counted as different combinations.

Example 2:
    Input:
        nums = [9],
        target = 3
    Output:
        0


Constraints:
    1 <= nums.length <= 200
    1 <= nums[i] <= 1000
    All the elements of nums are unique.
    1 <= target <= 1000


Follow up:
    What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?

*/

import java.util.Arrays;

public class _0377_Combination_Sum_IV {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Backtracking_Memo {

        public int combinationSum4(int[] nums, int target) {
            int[] memo = new int[target + 1];
            Arrays.fill(memo, -1);
            return helper(nums, target, memo);
        }

        private int helper(int[] nums, int remain, int[] memo) {
            if (remain == 0) {
                return 1;
            } else if (remain < 0) {
                return 0;
            } else {
                if (memo[remain] != -1) {
                    return memo[remain];
                }
                int sum = 0;
                for (int n : nums) {
                    sum += helper(nums, remain - n, memo);
                }
                memo[remain] = sum;
                return memo[remain];
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DP {

        public int combinationSum4(int[] nums, int target) {
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for (int i = 1; i <= target; ++i) {
                for (int j = 0; j < nums.length; ++j) {
                    dp[i] += i - nums[j] < 0 ? 0 : dp[i - nums[j]];
                }
            }
            return dp[target];
        }
    }
}