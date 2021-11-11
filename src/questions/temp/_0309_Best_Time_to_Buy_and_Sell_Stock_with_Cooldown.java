/**
 *  @author: Yunxiang He
 *  @date  : 2018-06-16 17:29
 */

package questions.temp;

/*

Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. 

You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
    After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)


Example:
    Input: [1,2,3,0,2]
    Output: 3 
    Explanation: transactions = [buy, sell, cooldown, buy, sell]

*/

public class _0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
    // sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
    public int maxProfit_DP(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int buy_i = -prices[0];
        int sell_i = 0;
        int sell_i_2 = 0;
        for (int price : prices) {
            buy_i = Math.max(buy_i, sell_i_2 - price);
            sell_i_2 = sell_i;
            sell_i = Math.max(sell_i, buy_i + price);
        }
        return sell_i;
    }

    public static void main(String[] args) {
        int[] prices = new int[] { 1, 2, 3, 0, 2 };
        new _0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown().maxProfit_DP(prices);
    }
}
