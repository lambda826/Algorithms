/**
 *  @author Yunxiang He
 *  @date 04/11/2019
 */

package coding.lintcode;

/*

Given two strings, find the longest common substring.
Return the length of it.


Example
Example 1:
    Input:  
        "ABCD" and "CBCE"
    Output:  
        2
    Explanation:
        Longest common substring is "BC"

Example 2:
    Input: 
        "ABCD" and "EACB"
    Output:  
        1
    Explanation: 
        Longest common substring is 'A' or 'C' or 'B'


Challenge
    O(n x m) time and memory.

Notice
    The characters in substring should occur continuously in original string. This is different with subsequence.

*/

public class __0079_Longest_Common_Substring {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // dp
    public int longestCommonSubstring(String A, String B) {
        int m = A.length() + 1;
        int n = B.length() + 1;
        int max = 0;
        int[][] dp = new int[m][n];
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
