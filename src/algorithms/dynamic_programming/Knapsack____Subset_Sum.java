/**
 * @author: Yunxiang He
 * @date : 2018-06-13 03:46
 */

package algorithms.dynamic_programming;

import common.Item;

/*

We are given n items {1, ..., n}, and each has a given nonnegative weight wi (for i = 1, ..., n)
We are also given a bound W
We would like to select a subset S of the items so that Σi∈S wi ≤ W and, subject to this restriction, Σi∈S wi is as large as possible

 */

public class Knapsack____Subset_Sum {

    public static int maximumSubsetSum(int totalWeight, Item[] items) {
        int[][] dp = new int[items.length + 1][totalWeight + 1];

        for (int i = 1; i < items.length + 1; i++) {
            for (int w = 1; w < totalWeight + 1; w++) {
                if (items[i - 1].getWeight() < w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i - 1].getWeight()] + items[i - 1].getValue());
                }
            }
        }

        return dp[items.length][totalWeight];
    }

    public static void main(String[] args) {
        Item i1 = new Item(2, 2);
        Item i2 = new Item(2, 2);
        Item i3 = new Item(3, 3);
        Item[] items = new Item[] { i1, i2, i3 };
        int maxSum = maximumSubsetSum(6, items);
        System.out.println(maxSum);
    }
}
