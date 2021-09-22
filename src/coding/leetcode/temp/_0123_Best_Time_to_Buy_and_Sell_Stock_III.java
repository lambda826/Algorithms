/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-17
 */

package coding.leetcode.temp;

/*

Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. 
You may complete at most two transactions.


Example 1:
    Input: [3,3,5,0,0,3,1,4]
    Output: 6
    Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3 - 0 = 3.
                 Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4 - 1 = 3.

Example 2:
    Input: [1,2,3,4,5]
    Output: 4
    Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5 - 1 = 4.
                 Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. 
                 You must sell before buying again.

Example 3:
    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.


Note: 
    You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
    
*/

public class _0123_Best_Time_to_Buy_and_Sell_Stock_III {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }

        // First loop to find the max profit for the first transaction
        int maxProfit = 0;
        int[] leftToRight = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < leftToRight.length; i++) {
            leftToRight[i] = Math.max(leftToRight[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        maxProfit = leftToRight[leftToRight.length - 1];

        // Second loop to find the max proft for the second transaction (left + right)
        int max = prices[prices.length - 1];
        int _2ndMax = 0;
        for (int i = prices.length - 2; i > 0; i--) {
            _2ndMax = Math.max(_2ndMax, max - prices[i]);
            max = Math.max(max, prices[i]);
            maxProfit = Math.max(maxProfit, leftToRight[i - 1] + _2ndMax);
        }

        return maxProfit;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP2(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int sell1 = 0;
        int sell2 = 0;
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);
            sell1 = Math.max(sell1, price + buy1);
            buy2 = Math.max(buy2, sell1 - price);
            sell2 = Math.max(sell2, price + buy2);
        }
        return sell2;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP3(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int buy1 = -prices[0];
        int buy2 = -prices[0];
        int[] dp1 = new int[prices.length];
        int[] dp2 = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            dp1[i] = Math.max(dp1[i - 1], prices[i] + buy1);
            buy2 = Math.max(buy2, dp1[i - 1] - prices[i]);
            dp2[i] = Math.max(dp2[i - 1], prices[i] + buy2);
        }

        return dp2[prices.length - 1];
    }
}
