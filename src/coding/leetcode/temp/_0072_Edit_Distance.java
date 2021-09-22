/**
 *  @author Yunxiang He
 *  @date 02/21/2019
 */

package coding.leetcode.temp;

/*

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
You have the following 3 operations permitted on a word:
    Insert a character
    Delete a character
    Replace a character


Example 1:
    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation: 
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')

Example 2:
    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation: 
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'len1' with 'x')
    exention -> exection (replace 'len1' with 'c')
    exection -> execution (insert 'u')

*/

public class _0072_Edit_Distance {
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // if one of the strings is empty
        if (len1 * len2 == 0) {
            return len1 + len2;
        }
        // array to store the convertion history
        int[][] dp = new int[len1 + 1][len2 + 1];

        // init boundaries
        for (int i = 0; i < len1 + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2 + 1; j++) {
            dp[0][j] = j;
        }
        // DP compute 
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                int left = dp[i - 1][j] + 1;
                int down = dp[i][j - 1] + 1;
                int left_down = dp[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                dp[i][j] = Math.min(left, Math.min(down, left_down));

            }
        }
        return dp[len1][len2];
    }
}
