package questions.leetcode;

/*

Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.


Example 1:
    Input: n = 3
    Output: 5
Example 2:
    Input: n = 1
    Output: 1


Constraints:
    1 <= n <= 19

*/

public class _0096_Unique_Binary_Search_Trees {

    class Solution {
        public int numTrees(int n) {
            int[] dp = new int[n + 1];
            dp[0] = 1;
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= i; ++j) {
                    dp[i] += dp[j - 1] * dp[i - j];
                }
            }
            return dp[n];
        }
    }

    class Solution2 {
        public int numTrees(int n) {
            return numTrees(1, n, new int[n + 1][n + 1]);
        }

        private int numTrees(int start, int end, int[][] dp) {
            if (start > end) {
                return 1;
            } else if (dp[start][end] != 0) {
                return dp[start][end];
            } else {
                int num = 0;
                for (int i = start; i <= end; ++i) {
                    int l = numTrees(start, i - 1, dp);
                    int r = numTrees(i + 1, end, dp);
                    num += l * r;
                }
                dp[start][end] = num;
                return num;
            }
        }
    }
}
