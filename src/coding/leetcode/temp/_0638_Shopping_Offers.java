/**
 *  @author Yunxiang He
 *  @date Jan 29, 2018 9:53:30 PM
 */

package coding.leetcode.temp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

In LeetCode Store, there are some kinds of items to sell.
Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we needs to buy for each item. 
The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you needs to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
Explanation: 
There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You needs to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.

Example 2:
Input: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
Output: 11
Explanation: 
The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You needs to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.

Note:
There are at most 6 kinds of items, 100 special offers.
For each item, you needs to buy at most 6 of them.
You are not allowed to buy more items than you want, even if that would lower the overall price.

 */

public class _0638_Shopping_Offers {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int specialSize = 0;
    private int itemNo = 0;
    private int minPrice = Integer.MAX_VALUE;
    private List<Integer> price = null;
    private List<List<Integer>> special = null;

    public int shoppingOffers_DFS(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // Constants
        itemNo = price.size();
        specialSize = special.size();
        this.price = price;
        this.special = special;

        // Search
        dfs(specialSize - 1, needs, 0);
        return minPrice;
    }

    private void dfs(int index, List<Integer> needs, int currentCost) {
        minPrice = Math.min(minPrice, computePrice(price, needs) + currentCost);
        for (int i = index; i >= 0; i--) {
            List<Integer> offer = special.get(i);
            List<Integer> newNeed = updateNeeds(needs, offer);
            if (newNeed != null) {
                dfs(i, newNeed, currentCost + offer.get(itemNo));
            }
        }
    }

    private List<Integer> updateNeeds(List<Integer> needs, List<Integer> offer) {
        List<Integer> need = new ArrayList<>(needs);
        for (int i = 0; i < need.size(); i++) {
            int amount = need.get(i) - offer.get(i);
            if (amount < 0) {
                return null;
            } else {
                need.set(i, amount);
            }
        }
        return need;
    }

    private int computePrice(List<Integer> price, List<Integer> needs) {
        int amount = 0;
        for (int i = 0; i < price.size(); i++) {
            amount += price.get(i) * needs.get(i);
        }
        return amount;
    }

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>(Arrays.asList(new Integer[] { 6, 2, 8, 6, 10, 5 }));
        List<List<Integer>> special = new ArrayList<>();
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 5, 1, 6, 2, 0, 2, 19 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 3, 3, 5, 3, 5, 2, 2 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 0, 4, 3, 2, 0, 14 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 5, 5, 4, 1, 6, 3, 23 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 3, 0, 5, 2, 1, 5, 35 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 1, 5, 4, 3, 1, 2, 36 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 6, 4, 2, 4, 1, 5 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 3, 3, 2, 6, 1, 0, 33 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 2, 5, 1, 2, 4, 6, 23 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 3, 6, 2, 6, 2, 6, 14 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 6, 0, 3, 3, 4, 17 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 0, 4, 5, 3, 5, 0, 15 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 1, 0, 6, 4, 0, 14 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 4, 4, 3, 3, 5, 8 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 4, 2, 4, 3, 6, 2, 30 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 3, 4, 0, 3, 1, 4, 3 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 4, 2, 6, 3, 3, 4, 12 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 6, 4, 2, 5, 1, 5, 16 })));
        special.add(new ArrayList<>(Arrays.asList(new Integer[] { 3, 1, 0, 0, 3, 2, 3 })));
        List<Integer> needs = new ArrayList<>(Arrays.asList(new Integer[] { 2, 4, 5, 3, 6, 3 }));
        _0638_Shopping_Offers test = new _0638_Shopping_Offers();
        test.shoppingOffers_DFS(price, special, needs);
        System.out.println(test.minPrice);
    }
}
