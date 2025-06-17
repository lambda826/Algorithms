package questions._05_binarySearch;

import java.util.Arrays;

/*

You are given a 0-indexed integer array nums and an integer p. Find p pairs of indices of nums such that the maximum difference amongst all the pairs is minimized.
Also, ensure no index appears more than once amongst the p pairs.

Note that for a pair of elements at the index i and j, the difference of this pair is |nums[i] - nums[j]|, where |x| represents the absolute value of x.

Return the minimum maximum difference among all p pairs. We define the maximum of an empty set to be zero.


Example 1:
    Input: nums = [10,1,2,7,1,3], p = 2
    Output: 1
    Explanation: The first pair is formed from the indices 1 and 4, and the second pair is formed from the indices 2 and 5.
    The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1. Therefore, we return 1.

Example 2:
    Input: nums = [4,2,1,2], p = 1
    Output: 0
    Explanation: Let the indices 1 and 3 form a pair. The difference of that pair is |2 - 2| = 0, which is the minimum we can attain.


Constraints:
    1 <= nums.length <= 10^5
    0 <= nums[i] <= 10^9
    0 <= p <= (nums.length)/2

*/
public class _2616_Minimize_the_Maximum_Difference_of_Pairs {

    class Solution {
        /**
         * 找到一个最小化的最大差值，使得可以从数组中找到 p 个数对。
         *
         * @param nums 整数数组
         * @param p    需要形成的数对数量
         * @return 最小化的最大差值
         */
        public int minimizeMax(int[] nums, int p) {
            if (p == 0) {
                return 0;
            }
            Arrays.sort(nums);
            int n = nums.length;

            // ======================= 优化点 =======================
            // 预先计算并存储所有相邻元素的差值，避免在二分查找中重复计算。
            // 这是一种“空间换时间”的策略。
            int[] diffs = new int[n - 1];
            int right = 0;
            for (int i = 0; i < n - 1; i++) {
                diffs[i] = nums[i + 1] - nums[i];
                right = Math.max(right, diffs[i]);
            }
            // ======================================================

            // 对“答案”（即最大差值）进行二分查找
            int left = 0;

            while (left < right) {
                int mid = left + (right - left) / 2;
                // 检查：以 mid 作为最大差值，是否能凑够 p 对？
                // 这里的贪心检查现在使用预先计算好的 diffs 数组。
                if (countValidPairs(diffs, mid) >= p) {
                    // 如果能凑够 p 对，mid 是一个可行的解，尝试寻找更小的差值。
                    right = mid;
                } else {
                    // 如果凑不够，mid 太小了，需要放宽差值限制。
                    left = mid + 1;
                }
            }
            return left;
        }

        /**
         * 贪心函数：使用预计算的差值数组，计算在给定的差值阈值下，最多能形成多少个数对。
         *
         * @param diffs     预计算好的相邻元素差值数组
         * @param threshold 允许的最大差值
         * @return 最多可以形成的有效数对的数量
         */
        private int countValidPairs(int[] diffs, int threshold) {
            int index = 0;
            int count = 0;
            // 遍历差值数组
            while (index < diffs.length) {
                // 贪心核心：检查第 index 个差值（即 nums[index+1] - nums[index]）是否满足条件
                if (diffs[index] <= threshold) {
                    // 如果满足，成功配对。
                    // 这对配对用掉了 nums[index] 和 nums[index+1]。
                    // 这意味着我们必须跳过下一个差值 diffs[index+1] (即 nums[index+2] - nums[index+1])，
                    // 因为 nums[index+1] 已经被使用了。
                    count++;
                    // 所以索引前进2步，直接去考察 diffs[index+2]。
                    index += 2;
                } else {
                    // 如果不满足，无法配对，索引只前进1步。
                    index++;
                }
            }
            return count;
        }
    }

    // Time limit exceeded
    class Solution_DP {
        public int minimizeMax(int[] nums, int p) {
            if (p == 0) {
                return 0;
            }
            Arrays.sort(nums);
            int[][] dp = new int[2][nums.length + 1];
            for (int pp = 1; pp <= p; ++pp) {
                dp[pp & 1][pp * 2 - 1] = Integer.MAX_VALUE;
                for (int n = pp * 2 - 1; n < nums.length; ++n) {
                    dp[pp & 1][n + 1] = Math.min(dp[pp & 1][n], Math.max(dp[(pp - 1) & 1][n - 1], nums[n] - nums[n - 1]));
                }
            }
            return dp[p & 1][nums.length];
        }
    }
}
