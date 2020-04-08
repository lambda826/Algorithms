package coding.leetcode;

/*

Say you have an array for which the i-th element is the price of a given stock on day i.
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


History:
    4/7/2020

*/

public class _0121_Best_Time_to_Buy_and_Sell_Stock {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP(int[] prices) {
        int sell = 0;
        int buy = Integer.MAX_VALUE;
        for (int price : prices) {
            sell = Math.max(sell, price - buy);
            buy = Math.min(buy, price);
        }
        return sell;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit(int[] prices) {
        int max = 0;
        if (prices.length != 0) {
            int currMin = prices[0];
            for (int price : prices) {
                currMin = Math.min(currMin, price);
                max = Math.max(max, price - currMin);
            }
        }
        return max;
    }
}
