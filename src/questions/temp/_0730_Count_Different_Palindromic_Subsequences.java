/**
 * @author: Yunxiang He
 * @date : 2018-06-27
 */

package questions.temp;

/*

Given a string s, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
A subsequence of a string S is obtained by deleting 0 or more characters from S.
A sequence is palindromic if it is equal to the sequence reversed.
Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.


Example 1:
    Input: 
    S = 'bccb'
    Output: 6
    Explanation: 
    The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
    Note that 'bcb' is counted only once, even though it occurs twice.

Example 2:
    Input: 
    S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
    Output: 104860361
    Explanation: 
    There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.


Note:
    The length of S will be in the range [1, 1000].
    Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.

*/

public class _0730_Count_Different_Palindromic_Subsequences {

    public static void main(String[] args) {
        _0730_Count_Different_Palindromic_Subsequences test = new _0730_Count_Different_Palindromic_Subsequences();
        test.countPalindromicSubsequences("dbdcac");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int countPalindromicSubsequences(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 1; len < s.length(); ++len) {
            for (int i = 0, j = i + len; j < s.length(); ++i, ++j) {
                if (s.charAt(i) == s.charAt(j)) {
                    int left = i + 1;
                    int right = j - 1;
                    while (left <= right && s.charAt(left) != s.charAt(i)) {
                        left++;
                    }
                    while (left <= right && s.charAt(right) != s.charAt(i)) {
                        right--;
                    }
                    if (left > right) { // i,j 之间没用与i,j相同的字符
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (left == right) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    }
                } else { // If the end points doesn't equal, then deduct the overlapped part dp[i + 1][j - 1]
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
                dp[i][j] = (dp[i][j] < 0) ? dp[i][j] + 10 ^ 400007 : dp[i][j] % 10 ^ 400007;
            }
        }
        return dp[0][n - 1];
    }
}
