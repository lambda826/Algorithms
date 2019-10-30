/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:55:28 PM
 */

package coding.temp;

/*

Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.


Example 1:
    Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
    Output: 8
    Explanation: The maximum profit can be achieved by:
    Buying at prices[0] = 1
    Selling at prices[3] = 8
    Buying at prices[4] = 4
    Selling at prices[5] = 9
    The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.


Note:
    0 < prices.length <= 50000.
    0 < prices[i] < 50000.
    0 <= fee < 50000.

 */

public class _0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee {

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int maxProfit_DP(int[] prices, int fee) {
        // sell depends on buy
        // buy depends on sell
        int buy = -prices[0];
        int sell = 0;
        for (int price : prices) {
            buy = Math.max(buy, -price + sell);
            sell = Math.max(sell, price + buy - fee);
        }
        return sell;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 贪心选择的关键是找到一个最大后是不是能够卖掉 stock, 重新开始寻找买入机会。
    // 比如序列1 3 2 8，如果发现2小于3就完成交易买1卖3，此时由于 fee = 2, (3 - 1 - fee) + (8 - 2 - fee) < (8 - 1 - fee)，所以说明卖早了,
    // 令 max 是当前最大 price, 当 (max - price[i] >= fee) 时可以在 max 处卖出, 且不会存在卖早的情况，再从i开始重新寻找买入机会。
    public int maxProfit_Greedy(int[] prices, int fee) {
        int profit = 0;
        int curProfit = 0;
        int minP = prices[0];
        int maxP = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minP = Math.min(minP, prices[i]);
            maxP = Math.max(maxP, prices[i]);
            curProfit = Math.max(curProfit, prices[i] - minP - fee);
            if ((maxP - prices[i]) >= fee) {
                profit += curProfit;
                curProfit = 0;
                minP = prices[i];
                maxP = prices[i];
            }
        }
        // The last trade have to be made if there is some profit
        return profit + curProfit;
    }

    public static void main(String[] args) {
        _0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee test = new _0714_Best_Time_to_Buy_and_Sell_Stock_with_Transaction_Fee();
        int profit = test.maxProfit_DP(new int[] { 1, 3, 2, 8, 4, 9 }, 2);
        int profit2 = test.maxProfit_Greedy(new int[] { 1, 3, 2, 8, 4, 9 }, 2);
        System.out.println(profit);
        System.out.println(profit2);
    }
}
