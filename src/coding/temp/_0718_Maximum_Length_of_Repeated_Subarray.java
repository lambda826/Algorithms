/**
 *  @author Yunxiang He
 *  @date Jan 25, 2018 3:29:47 AM
 */

package coding.temp;

/*

Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Example 1:
Input:
A: [1,2,3,2,1]
B: [3,2,1,4,7]
Output: 3
Explanation: 
The repeated subarray with maximum length is [3, 2, 1].

Note:
1 <= len(A), len(B) <= 1000
0 <= A[i], B[i] < 100

 */

public class _0718_Maximum_Length_of_Repeated_Subarray {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findLength_DP_TwoDimension(int[] A, int[] B) {
        if (A.length == 0 || B.length == 0) {
            return 0;
        }
        int maxL = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = 1; i < A.length + 1; i++) {
            for (int j = 1; j < B.length + 1; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxL = Math.max(dp[i][j], maxL);
                }
            }
        }
        return maxL;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int findLength_DP_OneDimension(int[] A, int[] B) {
        int[] dp = new int[B.length + 1];
        int maxL = 0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = B.length; j > 0; j--) {
                dp[j] = A[i - 1] == B[j - 1] ? dp[j - 1] + 1 : 0;
                maxL = Math.max(maxL, dp[j]);
            }
        }
        return maxL;
    }

    public static void main(String[] args) {
        _0718_Maximum_Length_of_Repeated_Subarray test = new _0718_Maximum_Length_of_Repeated_Subarray();
        int[] A = { 1, 2, 3, 2, 1 };
        int[] B = { 3, 2, 1, 4, 7 };
        test.findLength_DP_OneDimension(A, B);
    }
}
