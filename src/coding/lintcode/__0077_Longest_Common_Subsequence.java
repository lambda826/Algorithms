/**
 *  @author Yunxiang He
 *  @date 04/11/2019
 */

package coding.lintcode;

/*

Given two strings, find the longest common subsequence (LCS).
Your code should return the length of LCS.


Example
Example 1:
    Input:  "ABCD" and "EDCA"
    Output:  1
    
    Explanation:
    LCS is 'A' or  'D' or 'C'

Example 2:
    Input: "ABCD" and "EACB"
    Output:  2
    
    Explanation: 
    LCS is "AC"

*/

public class __0077_Longest_Common_Subsequence {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // dp
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length() + 1;
        int n = B.length() + 1;
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
