/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-18 02:57
 */

package questions.temp;

import java.util.Arrays;

/*

Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. 
You may complete at most k transactions.


Example 1:
    Input: [2,4,1], k = 2
    Output: 2
    Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.

Example 2:
    Input: [3,2,6,5,0,3], k = 2
    Output: 7
    Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
                 Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

Note:
    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

 */

public class __0188_Best_Time_to_Buy_and_Sell_Stock_IV {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP(int k, int[] prices) {
        if (prices.length < 2 || k < 1) {
            return 0;
        }

        // If # of transactions is larger than the length / 2
        // Then the maximum profit is the sum of total increasing
        if (k > prices.length / 2) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; ++i) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        } else {
            int[] buy = new int[k + 1];
            int[] sell = new int[k + 1];

            Arrays.fill(buy, -prices[0]);
            for (int price : prices) {
                for (int i = 1; i <= k; i++) {
                    buy[i] = Math.max(buy[i], sell[i - 1] - price);
                    sell[i] = Math.max(sell[i], buy[i] + price);
                }
            }
            return sell[k];
        }

    }
}
