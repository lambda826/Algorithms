/**
 *  @author Yunxiang He
 *  @date 12/14/2017
 */

package coding.temp;

/*

Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), 
design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.


Example 1:
    Input: [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
                 Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:
    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.

*/

public class _0121_Best_Time_to_Buy_and_Sel_Stock {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP(int[] prices) {
        int sell = 0;
        if (prices.length > 1) {
            int buy = -prices[0];
            for (int price : prices) {
                sell = Math.max(sell, price + buy);
                buy = Math.max(buy, -price);
            }
        }
        return sell;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit(int[] prices) {
        int max = 0;
        if (prices.length > 1) {
            int min = prices[0];
            for (int price : prices) {
                min = Math.min(min, price);
                max = Math.max(max, price - min);
            }
        }
        return max;
    }
}
