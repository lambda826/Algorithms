/**
 * @author Yunxiang He
 */

package coding.leetcode.temp;

import java.util.Arrays;

/*

You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.


Example 1:
	Input: coins = [1, 2, 5], amount = 11
	Output: 3 
	Explanation: 11 = 5 + 5 + 1

Example 2:
	Input: coins = [2], amount = 3
	Output: -1


Note:
	You may assume that you have an infinite number of each kind of coin.

*/

public class _0322_Coin_Change {

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int _amount = 1; _amount <= amount; ++_amount) {
            for (int coin : coins) {
                if (_amount >= coin) {
                    dp[_amount] = Math.min(dp[_amount], dp[(_amount - coin)] + 1);
                }
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

}
