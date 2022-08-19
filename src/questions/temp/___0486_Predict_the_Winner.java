/**
 *  @author Yunxiang He
 *  @date Dec 27, 2017 4:46:25 PM
 */

package questions.temp;

import java.util.Arrays;

/*

Given an array of scores that are non-negative integers. 
Player 1 picks one of the numbers from either end of the array followed by the player 2 and then player 1 and so on. 
Each time a player picks a number, that number will not be available for the next player. 
This continues until all the scores have been chosen. 
The player with the maximum score wins.

Given an array of scores, predict whether player 1 is the winner. 
You can assume each player plays to maximize his score.

Example 1:
Input: [1, 5, 2]
Output: False
Explanation: 
Initially, player 1 can choose between 1 and 2. 
If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. 
If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
Hence, player 1 will never be the winner and you need to return False.

Example 2:
Input: [1, 5, 233, 7]
Output: True
Explanation: 
Player 1 first chooses 1. 
Then player 2 have to choose between 5 and 7. 
No matter which number player 2 choose, player 1 can choose 233.
Finally, player 1 has more score (234) than player 2 (12), 
so you need to return True representing player1 can win.

Note:
1 <= length of the array <= 20.
Any scores in the given array are non-negative integers and will not exceed 10,000,000.
If the scores of both players are equal, then player 1 is still the winner.

*/

public class ___0486_Predict_the_Winner {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // If the length of the array is even, then the first player will always win
    // e.g. there are an array of 6 elements
    // E1, O1, E2, O2, E3, O3
    // Sum_E = E1 + E2 + E3
    // Sum_O = O1 + O2 + O3
    // Sum_E >= Sum_O or Sum_E <= Sum_O
    // The first can always choose to pick the bigger one
    public boolean PredictTheWinner_DP_TwoD(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                // dp = gain - max(remaining)
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Improve the space complexity
    // Because we only need the half of the table
    // And every element relies on the element which is left and down to it
    public boolean PredictTheWinner_DP_OneD(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }
        int[] dp = Arrays.copyOf(nums, n);
        for (int len = 1; len < n; len++) {
            for (int start = 0; start + len < n; start++) {
                int end = start + len;
                dp[start] = Math.max(nums[start] - dp[start + 1], nums[end] - dp[start]);
            }
        }
        return dp[0] >= 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean PredictTheWinner_DFS(int[] nums) {
        int n = nums.length;
        if ((n & 1) == 0) {
            return true;
        }
        return dfs(nums, 0, n - 1) >= 0;
    }

    private int dfs(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        } else {
            return Math.max(nums[start] - dfs(nums, start + 1, end), nums[end] - dfs(nums, start, end - 1));
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean PredictTheWinner_MiniMax(int[] nums) {
        return max(nums, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, nums.length - 1, 0) >= 0;
    }

    private int max(int[] nums, int alfa, int beta, int left, int right, int utility) {
        if (left > right) {
            if (utility >= 0) {
                return 1;
            } else {
                return -1;
            }
        }
        int v = Integer.MIN_VALUE;
        v = Math.max(Math.max(v, min(nums, alfa, beta, left + 1, right, utility + nums[left])), min(nums, alfa, beta, left, right - 1, utility + nums[right]));
        if (v > beta) {
            return v;
        }
        alfa = Math.max(alfa, v);
        return v;
    }

    private int min(int[] nums, int alfa, int beta, int left, int right, int utility) {
        if (left > right) {
            if (utility >= 0) {
                return 1;
            } else {
                return -1;
            }
        }
        int v = Integer.MAX_VALUE;
        v = Math.min(Math.min(v, max(nums, alfa, beta, left + 1, right, utility - nums[left])), max(nums, alfa, beta, left, right - 1, utility - nums[right]));
        if (v < alfa) {
            return v;
        }
        beta = Math.min(beta, v);
        return v;
    }

    public static void main(String[] args) {
        System.out.println(new ___0486_Predict_the_Winner().PredictTheWinner_DP_OneD(new int[] { 1, 5, 2 }));
    }
}
