package questions._12_dynamicProgramming.twoDimension.optimized;

/*

Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.


Example 1:
    Input:
        nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
    Output:
        3
    Explanation:
        The repeated subarray with maximum length is [3,2,1].

Example 2:
    Input:
        nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
    Output:
        5


Constraints:
    1 <= nums1.length, nums2.length <= 1000
    0 <= nums1[i], nums2[i] <= 100

 */

public class _0718_Maximum_Length_of_Repeated_Subarray {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 1. Define recursive function
    //      In this problem, the dp[i][j] doesn't indicate the optimal solution,
    //      Instead, use a global variable to store optimal solution in every iteration
    // 2. DP[i][j] indicates max length of the subarrays end up with nums1[i], nums2[j].
    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        int max = 0;
        for (int i = 1; i < nums1.length + 1; ++i) {
            for (int j = 1; j < nums2.length + 1; ++j) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findLength_Optimized(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int max = 0;
        for (int i = 0; i < nums1.length; ++i) {
            for (int j = nums2.length; j > 0; --j) {
                dp[j] = nums1[i] == nums2[j - 1] ? dp[j - 1] + 1 : 0;
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }
}