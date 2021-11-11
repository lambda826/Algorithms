package questions._09_dfs_backtracking.combination._1d;

/*

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.


Example 1:
    Input:
        amount = 5,
        coins = [1,2,5]
    Output:
        4
    Explanation:
        there are four ways to make up the amount:
        5=5
        5=2+2+1
        5=2+1+1+1
        5=1+1+1+1+1

Example 2:
    Input:
        amount = 3,
            coins = [2]
    Output:
        0
    Explanation:
        the amount of 3 cannot be made up just with coins of 2.

Example 3:
    Input:
        amount = 10,
        coins = [10]
    Output:
        1


Constraints:
    1 <= coins.length <= 300
    1 <= coins[i] <= 5000
    All the values of coins are unique.
    0 <= amount <= 5000

*/

public class _0518_Coin_Change_2 {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_Backtracking_Memo {

        public int change(int amount, int[] coins) {
            return helper(amount, coins, 0, new Integer[coins.length + 1][amount + 1]);
        }

        private int helper(int amount, int[] coins, int index, Integer[][] memo) {
            if (amount == 0) {
                return 1;
            }
            if (memo[index][amount] != null) {
                return memo[index][amount];
            }
            int sum = 0;
            for (int i = index; i < coins.length; ++i) {
                if (amount - coins[i] >= 0) {
                    sum += helper(amount - coins[i], coins, i, memo);
                }
            }
            memo[index][amount] = sum;
            return memo[index][amount];
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class Solution_DP {

        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            // Need to iterate on coin in order to deduplicate combinations
            for (int coin : coins) {
                for (int currAmount = 1; currAmount <= amount; ++currAmount) {
                    if (coin <= currAmount) {
                        dp[currAmount] += dp[currAmount - coin];
                    }
                }
            }
            return dp[amount];
        }
    }
}
